package ch.nolix.system.objectschema.model;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.data.GlobalIdCreator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.system.objectschema.modelmutationvalidator.TableMutationValidator;
import ch.nolix.system.objectschema.modelvalidator.TableValidator;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;
import ch.nolix.systemapi.objectschemaapi.modelmutationvalidatorapi.ITableMutationValidator;
import ch.nolix.systemapi.objectschemaapi.modelvalidatorapi.ITableValidator;
import ch.nolix.systemapi.rawschemaapi.adapterapi.ISchemaAdapter;
import ch.nolix.systemapi.rawschemaapi.flatdto.FlatTableDto;

public final class Table extends AbstractSchemaObject implements ITable {

  private static final ITableMutationValidator MUTATION_VALIDATOR = new TableMutationValidator();

  private static final TableEditor TABLE_EDITOR = new TableEditor();

  private static final ITableValidator TABLE_VALIDATOR = new TableValidator();

  private final String id;

  private String name;

  private boolean loadedColumnsFromDatabase;

  private Database parentDatabase;

  private LinkedList<IColumn> columns = LinkedList.createEmpty();

  private Table(final String id, final String name) {

    GlobalValidator.assertThat(id).thatIsNamed(LowerCaseVariableCatalog.ID).isNotBlank();

    this.id = id;
    setName(name);
  }

  public static Table fromFlatDto(final FlatTableDto flatTableDto) {
    return new Table(flatTableDto.id(), flatTableDto.name());
  }

  public static Table withName(final String name) {

    final var id = GlobalIdCreator.createIdOf10HexadecimalCharacters();

    return new Table(id, name);
  }

  @Override
  public Table addColumn(final IColumn column) {

    MUTATION_VALIDATOR.assertCanAddColumnToTable(this, column);
    TABLE_EDITOR.addColumnToTable(this, (Column) column);

    return this;
  }

  @Override
  public ITable addColumns(final IContainer<IColumn> columns) {

    for (final var c : columns) {
      addColumn(c);
    }

    return this;
  }

  @Override
  public boolean belongsToDatabase() {
    return (parentDatabase != null);
  }

  @Override
  public Table createColumnWithNameAndParameterizedFieldType(
    final String name,
    final IContentModel contentModel) {
    return addColumn(new Column(name, contentModel));
  }

  @Override
  public void delete() {
    MUTATION_VALIDATOR.assertCanDeleteTable(this);
    TABLE_EDITOR.deleteTable(this);
  }

  @Override
  public FlatTableDto getFlatDto() {
    return new FlatTableDto(getId(), getName());
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Database getStoredParentDatabase() {

    assertBelongsToDatabase();

    return parentDatabase;
  }

  @Override
  public IContainer<IColumn> getStoredColumns() {

    loadColumnsFromDatabaseIfNeeded();

    return columns;
  }

  @Override
  public boolean isConnectedWithRealDatabase() {
    return (belongsToDatabase() && getStoredParentDatabase().isConnectedWithRealDatabase());
  }

  @Override
  public Table setName(final String name) {

    MUTATION_VALIDATOR.assertCanSetNameToTable(this, name);
    TABLE_EDITOR.setNameToTable(this, name);

    return this;
  }

  @Override
  protected void noteClose() {

    //Does not call getStoredColumns method to avoid that the columns need to be
    //loaded from the database.
    for (final var c : columns) {
      ((Column) c).internalClose();
    }
  }

  void addColumnAttribute(final IColumn column) {
    columns.addAtEnd(column);
  }

  ISchemaAdapter internalGetStoredRawSchemaAdapter() {
    return getStoredParentDatabase().internalGetStoredRawSchemaAdapter();
  }

  void removeColumnAttribute(final Column column) {
    columns.removeStrictlyFirstOccurrenceOf(column);
  }

  void setNameAttribute(final String name) {
    this.name = name;
  }

  void setParentDatabase(final Database parentDatabase) {

    GlobalValidator.assertThat(parentDatabase).thatIsNamed("parent database").isNotNull();
    TABLE_VALIDATOR.assertDoesNotBelongToDatabase(this);

    this.parentDatabase = parentDatabase;
  }

  private void assertBelongsToDatabase() {
    if (!belongsToDatabase()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(this, Database.class);
    }
  }

  private boolean hasLoadedColumnsFromDatabase() {
    return loadedColumnsFromDatabase;
  }

  private void loadColumnsFromDatabase() {

    loadedColumnsFromDatabase = true;

    final var tables = getStoredParentDatabase().getStoredTables();

    columns = LinkedList.fromIterable(
      internalGetStoredRawSchemaAdapter().loadColumnsByTableId(getId()).to(c -> Column.fromDto(c, tables)));

    for (final var c : columns) {
      final var column = (Column) c;
      column.internalSetLoaded();
      column.setParentTableAttribute(this);
    }
  }

  private void loadColumnsFromDatabaseIfNeeded() {
    if (needsToLoadColumnsFromDatabase()) {
      loadColumnsFromDatabase();
    }
  }

  private boolean needsToLoadColumnsFromDatabase() {
    return (isLoaded() && !hasLoadedColumnsFromDatabase());
  }
}
