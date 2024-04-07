//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
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

//class
public final class Table extends SchemaObject implements ITable {

  //constant
  private static final TableMutationValidator MUTATION_VALIDATOR = new TableMutationValidator();

  //constant
  private static final TableMutationExecutor MUTATION_EXECUTOR = new TableMutationExecutor();

  //constant
  private static final TableTool TABLE_TOOL = new TableTool();

  //attribute
  private final String id;

  //attribute
  private String name;

  //attribute
  private boolean loadedColumnsFromDatabase;

  //optional attribute
  private Database parentDatabase;

  //multi-attribute
  private LinkedList<IColumn> columns = new LinkedList<>();

  //constructor
  public Table(final String name) {
    this(
      GlobalIdCreator.createIdOf10HexadecimalCharacters(),
      name);
  }

  //constructor
  public Table(final String id, final String name) {

    GlobalValidator.assertThat(id).thatIsNamed(LowerCaseVariableCatalogue.ID).isNotBlank();

    this.id = id;
    setName(name);
  }

  //static method
  public static Table fromFlatDto(final IFlatTableDto flatTableDto) {
    return new Table(flatTableDto.getId(), flatTableDto.getName());
  }

  //method
  @Override
  public Table addColumn(final IColumn column) {

    MUTATION_VALIDATOR.assertCanAddColumnToTable(this, (Column) column);
    MUTATION_EXECUTOR.addColumnToTable(this, (Column) column);

    return this;
  }

  //method
  @Override
  public boolean belongsToDatabase() {
    return (parentDatabase != null);
  }

  //method
  @Override
  public Table createColumnWithNameAndParameterizedFieldType(
    final String name,
    final IParameterizedFieldType parameterizedFieldType) {
    return addColumn(new Column(name, parameterizedFieldType));
  }

  //method
  @Override
  public void delete() {
    MUTATION_VALIDATOR.assertCanDeleteTable(this);
    MUTATION_EXECUTOR.deleteTable(this);
  }

  //method
  @Override
  public IFlatTableDto getFlatDto() {
    return new FlatTableDto(getId(), getName());
  }

  //method
  @Override
  public String getId() {
    return id;
  }

  //method
  @Override
  public String getName() {
    return name;
  }

  //method
  @Override
  public Database getParentDatabase() {

    assertBelongsToDatabase();

    return parentDatabase;
  }

  //method
  @Override
  public IContainer<IColumn> getStoredColumns() {

    loadColumnsFromDatabaseIfNeeded();

    return columns;
  }

  //method
  @Override
  public boolean isLinkedWithRealDatabase() {
    return (belongsToDatabase() && getParentDatabase().isLinkedWithRealDatabase());
  }

  //method
  @Override
  public Table setName(final String name) {

    MUTATION_VALIDATOR.assertCanSetNameToTable(this, name);
    MUTATION_EXECUTOR.setNameToTable(this, name);

    return this;
  }

  //method
  @Override
  public TableDto toDto() {
    return new TableDto(getId(), getName(), createColumnDtos());
  }

  //method
  @Override
  protected void noteClose() {

    //Does not call getStoredColumns method to avoid that the columns need to be
    //loaded from the database.
    for (final var c : columns) {
      ((Column) c).internalClose();
    }
  }

  //method
  void addColumnAttribute(final IColumn column) {
    columns.addAtEnd(column);
  }

  //method
  RawSchemaLinkerAdapter internalgetStoredRawSchemaAdapter() {
    return getParentDatabase().internalGetRefRawSchemaAdapter();
  }

  //method
  void removeColumnAttribute(final Column column) {
    columns.removeStrictlyFirstOccurrenceOf(column);
  }

  //method
  void setNameAttribute(final String name) {
    this.name = name;
  }

  //method
  void setParentDatabase(final Database parentDatabase) {

    GlobalValidator.assertThat(parentDatabase).thatIsNamed("parent database").isNotNull();
    TABLE_TOOL.assertDoesNotBelongToDatabase(this);

    this.parentDatabase = parentDatabase;
  }

  //method
  private void assertBelongsToDatabase() {
    if (!belongsToDatabase()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(this, Database.class);
    }
  }

  //method
  private IContainer<IColumnDto> createColumnDtos() {
    return getStoredColumns().to(IColumn::toDto);
  }

  //method
  private boolean hasLoadedColumnsFromDatabase() {
    return loadedColumnsFromDatabase;
  }

  //method
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

  //method
  private void loadColumnsFromDatabaseIfNeeded() {
    if (needsToLoadColumnsFromDatabase()) {
      loadColumnsFromDatabase();
    }
  }

  //method
  private boolean needsToLoadColumnsFromDatabase() {
    return (isLoaded() && !hasLoadedColumnsFromDatabase());
  }
}
