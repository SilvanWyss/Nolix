//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.databaseobject.databaseobjectvalidator.DatabaseObjectValidator;
import ch.nolix.system.objectschema.schematool.DatabaseTool;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class Database extends SchemaObject implements IDatabase {

  //constant
  private static final DatabaseTool DATABASE_TOOL = new DatabaseTool();

  //constant
  private static final DatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

  //constant
  private static final DatabaseMutationExecutor MUTATION_EXECUTOR = new DatabaseMutationExecutor();

  //attribute
  private final String name;

  //attribute
  private boolean loadedTablesFromDatabase;

  //optional attribute
  private RawSchemaAdapter rawSchemaAdapter;

  //multi-attribute
  private LinkedList<ITable> tables = new LinkedList<>();

  //constructor
  public Database(final String name) {

    DATABASE_TOOL.assertCanSetGivenNameToDatabase(name);

    this.name = name;
  }

  //method
  @Override
  public Database addTable(final ITable table) {

    DATABASE_TOOL.assertCanAddGivenTable(this, table);
    MUTATION_EXECUTOR.addTableToDatabase(this, (Table) table);

    return this;
  }

  //method
  @Override
  public Database createTableWithName(final String name) {
    return addTable(new Table(name));
  }

  //method
  @Override
  public String getName() {
    return name;
  }

  //method
  @Override
  public IContainer<ITable> getStoredTables() {

    loadTablesFromDatabaseIfNeeded();

    return tables;
  }

  //method
  @Override
  public int getTableCount() {

    if (!isLinkedWithRealDatabase() || hasLoadedTablesFromDatabase()) {
      return tables.getElementCount();
    }

    return rawSchemaAdapter.getTableCount();
  }

  //method
  @Override
  public boolean isLinkedWithRealDatabase() {
    return (rawSchemaAdapter != null);
  }

  //method
  @Override
  public void setRawSchemaAdapter(final ISchemaAdapter rawSchemaAdapter) {
    setRawSchemaAdapter(new RawSchemaAdapter(rawSchemaAdapter));
  }

  //method
  @Override
  protected void noteClose() {

    //Does not call getStoredTables method to avoid that the tables need to be
    //loaded from the database.
    for (final var t : tables) {
      ((Table) t).internalClose();
    }
  }

  //method
  void addTableAttribute(final ITable table) {
    tables.addAtEnd(table);
  }

  //method
  RawSchemaAdapter internalGetRefRawSchemaAdapter() {

    DATABASE_OBJECT_VALIDATOR.assertIsLinkedWithRealDatabase(this);

    return rawSchemaAdapter;
  }

  //method
  void removeTableAttribute(final Table table) {
    tables.removeStrictlyFirstOccurrenceOf(table);
  }

  //method
  private boolean hasLoadedTablesFromDatabase() {
    return loadedTablesFromDatabase;
  }

  //method
  private void loadTablesFromDatabase() {

    tables = LinkedList.fromIterable(internalGetRefRawSchemaAdapter().loadFlatTables().to(Table::fromFlatDto));
    for (final var t : tables) {
      final var table = (Table) t;
      table.internalSetLoaded();
      table.setParentDatabase(this);
    }

    loadedTablesFromDatabase = true;
  }

  //method
  private void loadTablesFromDatabaseIfNeeded() {
    if (needsToLoadTablesFromDatabase()) {
      loadTablesFromDatabase();
    }
  }

  //method
  private boolean needsToLoadTablesFromDatabase() {
    return (DATABASE_TOOL.isLoaded(this) && !hasLoadedTablesFromDatabase());
  }

  //method
  private void setRawSchemaAdapter(final RawSchemaAdapter rawSchemaAdapter) {

    GlobalValidator.assertThat(rawSchemaAdapter).thatIsNamed(RawSchemaAdapter.class).isNotNull();
    DATABASE_OBJECT_VALIDATOR.assertIsNotLinkedWithRealDatabase(this);

    internalSetLoaded();
    this.rawSchemaAdapter = rawSchemaAdapter;
  }
}
