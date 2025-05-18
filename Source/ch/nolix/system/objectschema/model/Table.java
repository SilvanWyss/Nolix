package ch.nolix.system.objectschema.model;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programstructure.data.IdCreator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.system.objectschema.modelmutationvalidator.TableMutationValidator;
import ch.nolix.system.objectschema.modelvalidator.TableValidator;
import ch.nolix.systemapi.midschemaapi.adapterapi.ISchemaAdapter;
import ch.nolix.systemapi.midschemaapi.flatmodelapi.FlatTableDto;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;
import ch.nolix.systemapi.objectschemaapi.modelmutationvalidatorapi.ITableMutationValidator;
import ch.nolix.systemapi.objectschemaapi.modelvalidatorapi.ITableValidator;

public final class Table extends AbstractSchemaObject implements ITable {

  private static final ITableMutationValidator MUTATION_VALIDATOR = new TableMutationValidator();

  private static final ITableValidator TABLE_VALIDATOR = new TableValidator();

  private final String id;

  private String name;

  private Database parentDatabase;

  private ILinkedList<Column> columns = LinkedList.createEmpty();

  private Table(final String id, final String name) {

    Validator.assertThat(id).thatIsNamed(LowerCaseVariableCatalog.ID).isNotBlank();

    this.id = id;
    setName(name);
  }

  public static Table fromFlatDto(final FlatTableDto flatTableDto) {
    return new Table(flatTableDto.id(), flatTableDto.name());
  }

  public static Table withIdAndName(final String id, final String name) {
    return new Table(id, name);
  }

  public static Table withName(final String name) {

    final var id = IdCreator.createIdOf10HexadecimalCharacters();

    return new Table(id, name);
  }

  @Override
  public Table addColumn(final IColumn column) {

    MUTATION_VALIDATOR.assertCanAddColumnToTable(this, column);

    TableEditor.addColumnToTable(this, (Column) column);

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
  public Table addColumnWithNameAndContentModel(
    final String name,
    final IContentModel contentModel) {
    return addColumn(new Column(name, contentModel));
  }

  @Override
  public void delete() {

    MUTATION_VALIDATOR.assertCanDeleteTable(this);

    TableEditor.deleteTable(this);
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
  public IContainer<? extends IColumn> getStoredColumns() {
    return columns;
  }

  @Override
  public boolean isConnectedWithRealDatabase() {
    return (belongsToDatabase() && getStoredParentDatabase().isConnectedWithRealDatabase());
  }

  @Override
  public Table setName(final String name) {

    MUTATION_VALIDATOR.assertCanSetNameToTable(this, name);

    TableEditor.setNameToTable(this, name);

    return this;
  }

  @Override
  protected void noteClose() {

    //Does not call getStoredColumns method to avoid that the columns need to be
    //loaded from the database.
    for (final var c : columns) {
      c.internalClose();
    }
  }

  void addColumnAttribute(final Column column) {
    columns.addAtEnd(column);
  }

  ISchemaAdapter getStoredMidSchemaAdapter() {
    return getStoredParentDatabase().getStoredMidSchemaAdapter();
  }

  void removeColumnAttribute(final Column column) {
    columns.removeStrictlyFirstOccurrenceOf(column);
  }

  void setNameAttribute(final String name) {
    this.name = name;
  }

  void setParentDatabase(final Database parentDatabase) {

    Validator.assertThat(parentDatabase).thatIsNamed("parent database").isNotNull();
    TABLE_VALIDATOR.assertDoesNotBelongToDatabase(this);

    this.parentDatabase = parentDatabase;
  }

  private void assertBelongsToDatabase() {
    if (!belongsToDatabase()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(this, Database.class);
    }
  }
}
