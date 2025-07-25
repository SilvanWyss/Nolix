package ch.nolix.system.objectschema.model;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.databaseobject.modelvalidator.DatabaseObjectValidator;
import ch.nolix.system.objectschema.schematool.DatabaseTool;
import ch.nolix.systemapi.midschemaapi.adapterapi.ISchemaAdapter;
import ch.nolix.systemapi.objectschemaapi.modelapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public final class Database extends AbstractSchemaObject implements IDatabase {

  private static final DatabaseTool DATABASE_TOOL = new DatabaseTool();

  private static final DatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

  private static final DatabaseMutationExecutor MUTATION_EXECUTOR = new DatabaseMutationExecutor();

  private final String name;

  private boolean loadedTablesFromDatabase;

  private final ISchemaAdapter midSchemaAdapter;

  private LinkedList<ITable> tables = LinkedList.createEmpty();

  public Database(final String name, final ISchemaAdapter midSchemaAdapter) {

    DATABASE_TOOL.assertCanSetGivenNameToDatabase(name);

    Validator.assertThat(midSchemaAdapter).thatIsNamed("mid schema adapter").isNotNull();

    this.name = name;
    this.midSchemaAdapter = midSchemaAdapter;

    setLoaded();
  }

  @Override
  public Database addTable(final ITable table) {

    DATABASE_TOOL.assertCanAddGivenTable(this, table);
    MUTATION_EXECUTOR.addTableToDatabase(this, (Table) table);

    return this;
  }

  @Override
  public Database createTableWithName(final String name) {

    final var table = Table.withName(name);

    return addTable(table);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public IContainer<ITable> getStoredTables() {

    loadTablesFromDatabaseIfNeeded();

    return tables;
  }

  @Override
  public int getTableCount() {

    if (!isConnectedWithRealDatabase() || hasLoadedTablesFromDatabase()) {
      return tables.getCount();
    }

    return midSchemaAdapter.getTableCount();
  }

  @Override
  public boolean isConnectedWithRealDatabase() {
    return (midSchemaAdapter != null);
  }

  @Override
  protected void noteClose() {

    //Does not call getStoredTables method to avoid that the tables need to be
    //loaded from the database.
    for (final var t : tables) {
      ((Table) t).close();
    }
  }

  void addTableAttribute(final ITable table) {
    tables.addAtEnd(table);
  }

  ISchemaAdapter getStoredMidSchemaAdapter() {

    DATABASE_OBJECT_VALIDATOR.assertIsConnectedWithRealDatabase(this);

    return midSchemaAdapter;
  }

  void removeTableAttribute(final Table table) {
    tables.removeStrictlyFirstOccurrenceOf(table);
  }

  private boolean hasLoadedTablesFromDatabase() {
    return loadedTablesFromDatabase;
  }

  private void loadTablesFromDatabase() {

    final var loadedTables = TableLoader.loadTables(getStoredMidSchemaAdapter());

    for (final var t : loadedTables) {
      t.setParentDatabase(this);
      tables.addAtEnd(t);
    }

    loadedTablesFromDatabase = true;
  }

  private void loadTablesFromDatabaseIfNeeded() {
    if (needsToLoadTablesFromDatabase()) {
      loadTablesFromDatabase();
    }
  }

  private boolean needsToLoadTablesFromDatabase() {
    return (isLoaded() && !hasLoadedTablesFromDatabase());
  }
}
