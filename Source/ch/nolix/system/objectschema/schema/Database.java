package ch.nolix.system.objectschema.schema;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.databaseobject.databaseobjecttool.DatabaseObjectValidator;
import ch.nolix.system.objectschema.rawschemalinker.RawSchemaLinkerAdapter;
import ch.nolix.system.objectschema.schematool.DatabaseTool;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;

public final class Database extends SchemaObject implements IDatabase {

  private static final DatabaseTool DATABASE_TOOL = new DatabaseTool();

  private static final DatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

  private static final DatabaseMutationExecutor MUTATION_EXECUTOR = new DatabaseMutationExecutor();

  private final String name;

  private boolean loadedTablesFromDatabase;

  private final RawSchemaLinkerAdapter rawSchemaLinkerAdapter;

  private LinkedList<ITable> tables = LinkedList.createEmpty();

  public Database(final String name, final ISchemaAdapter schemaAdapter) {

    DATABASE_TOOL.assertCanSetGivenNameToDatabase(name);

    this.name = name;
    rawSchemaLinkerAdapter = new RawSchemaLinkerAdapter(schemaAdapter);

    internalSetLoaded();
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

    return rawSchemaLinkerAdapter.getTableCount();
  }

  @Override
  public boolean isConnectedWithRealDatabase() {
    return (rawSchemaLinkerAdapter != null);
  }

  @Override
  protected void noteClose() {

    //Does not call getStoredTables method to avoid that the tables need to be
    //loaded from the database.
    for (final var t : tables) {
      ((Table) t).internalClose();
    }
  }

  void addTableAttribute(final ITable table) {
    tables.addAtEnd(table);
  }

  RawSchemaLinkerAdapter internalGetRefRawSchemaAdapter() {

    DATABASE_OBJECT_VALIDATOR.assertIsConnectedWithRealDatabase(this);

    return rawSchemaLinkerAdapter;
  }

  void removeTableAttribute(final Table table) {
    tables.removeStrictlyFirstOccurrenceOf(table);
  }

  private boolean hasLoadedTablesFromDatabase() {
    return loadedTablesFromDatabase;
  }

  private void loadTablesFromDatabase() {

    tables = LinkedList.fromIterable(internalGetRefRawSchemaAdapter().loadFlatTables().to(Table::fromFlatDto));
    for (final var t : tables) {
      final var table = (Table) t;
      table.internalSetLoaded();
      table.setParentDatabase(this);
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
