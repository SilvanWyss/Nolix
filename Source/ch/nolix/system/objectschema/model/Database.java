package ch.nolix.system.objectschema.model;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
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

  private final ISchemaAdapter rawSchemaAdapter;

  private LinkedList<ITable> tables = LinkedList.createEmpty();

  public Database(final String name, final ISchemaAdapter rawSchemaAdapter) {

    DATABASE_TOOL.assertCanSetGivenNameToDatabase(name);

    Validator.assertThat(rawSchemaAdapter).thatIsNamed("raw schema adapter").isNotNull();

    this.name = name;
    this.rawSchemaAdapter = rawSchemaAdapter;

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

    return rawSchemaAdapter.getTableCount();
  }

  @Override
  public boolean isConnectedWithRealDatabase() {
    return (rawSchemaAdapter != null);
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

  ISchemaAdapter internalGetStoredRawSchemaAdapter() {

    DATABASE_OBJECT_VALIDATOR.assertIsConnectedWithRealDatabase(this);

    return rawSchemaAdapter;
  }

  void removeTableAttribute(final Table table) {
    tables.removeStrictlyFirstOccurrenceOf(table);
  }

  private boolean hasLoadedTablesFromDatabase() {
    return loadedTablesFromDatabase;
  }

  private void loadTablesFromDatabase() {

    tables = LinkedList.fromIterable(internalGetStoredRawSchemaAdapter().loadFlatTables().to(Table::fromFlatDto));
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
