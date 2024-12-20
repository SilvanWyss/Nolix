package ch.nolix.system.objectschema.schema;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.data.GlobalIdCreator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.objectschema.flatschemadto.FlatTableDto;
import ch.nolix.system.objectschema.rawschemalinker.RawSchemaLinkerAdapter;
import ch.nolix.system.objectschema.schemadto.TableDto;
import ch.nolix.system.objectschema.schematool.TableTool;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;

public final class Table extends SchemaObject implements ITable {

  private static final TableMutationValidator MUTATION_VALIDATOR = new TableMutationValidator();

  private static final TableMutationExecutor MUTATION_EXECUTOR = new TableMutationExecutor();

  private static final TableTool TABLE_TOOL = new TableTool();

  private final String id;

  private String name;

  private boolean loadedColumnsFromDatabase;

  private Database parentDatabase;

  private LinkedList<IColumn> columns = LinkedList.createEmpty();

  public Table(final String name) {
    this(
      GlobalIdCreator.createIdOf10HexadecimalCharacters(),
      name);
  }

  public Table(final String id, final String name) {

    GlobalValidator.assertThat(id).thatIsNamed(LowerCaseVariableCatalogue.ID).isNotBlank();

    this.id = id;
    setName(name);
  }

  public static Table fromFlatDto(final IFlatTableDto flatTableDto) {
    return new Table(flatTableDto.getId(), flatTableDto.getName());
  }

  @Override
  public Table addColumn(final IColumn column) {

    MUTATION_VALIDATOR.assertCanAddColumnToTable(this, (Column) column);
    MUTATION_EXECUTOR.addColumnToTable(this, (Column) column);

    return this;
  }

  @Override
  public boolean belongsToDatabase() {
    return (parentDatabase != null);
  }

  @Override
  public Table createColumnWithNameAndParameterizedFieldType(
    final String name,
    final IParameterizedFieldType parameterizedFieldType) {
    return addColumn(new Column(name, parameterizedFieldType));
  }

  @Override
  public void delete() {
    MUTATION_VALIDATOR.assertCanDeleteTable(this);
    MUTATION_EXECUTOR.deleteTable(this);
  }

  @Override
  public IFlatTableDto getFlatDto() {
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
  public Database getParentDatabase() {

    assertBelongsToDatabase();

    return parentDatabase;
  }

  @Override
  public IContainer<IColumn> getStoredColumns() {

    loadColumnsFromDatabaseIfNeeded();

    return columns;
  }

  @Override
  public boolean isLinkedWithRealDatabase() {
    return (belongsToDatabase() && getParentDatabase().isLinkedWithRealDatabase());
  }

  @Override
  public Table setName(final String name) {

    MUTATION_VALIDATOR.assertCanSetNameToTable(this, name);
    MUTATION_EXECUTOR.setNameToTable(this, name);

    return this;
  }

  @Override
  public TableDto toDto() {
    return new TableDto(getId(), getName(), createColumnDtos());
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

  RawSchemaLinkerAdapter internalgetStoredRawSchemaAdapter() {
    return getParentDatabase().internalGetRefRawSchemaAdapter();
  }

  void removeColumnAttribute(final Column column) {
    columns.removeStrictlyFirstOccurrenceOf(column);
  }

  void setNameAttribute(final String name) {
    this.name = name;
  }

  void setParentDatabase(final Database parentDatabase) {

    GlobalValidator.assertThat(parentDatabase).thatIsNamed("parent database").isNotNull();
    TABLE_TOOL.assertDoesNotBelongToDatabase(this);

    this.parentDatabase = parentDatabase;
  }

  private void assertBelongsToDatabase() {
    if (!belongsToDatabase()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(this, Database.class);
    }
  }

  private IContainer<IColumnDto> createColumnDtos() {
    return getStoredColumns().to(IColumn::toDto);
  }

  private boolean hasLoadedColumnsFromDatabase() {
    return loadedColumnsFromDatabase;
  }

  private void loadColumnsFromDatabase() {

    loadedColumnsFromDatabase = true;

    final var tables = getParentDatabase().getStoredTables();

    columns = LinkedList.fromIterable(
      internalgetStoredRawSchemaAdapter().loadColumnsOfTable(this).to(c -> Column.fromDto(c, tables)));

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
