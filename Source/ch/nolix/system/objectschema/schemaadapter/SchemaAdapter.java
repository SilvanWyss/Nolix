//package declaration
package ch.nolix.system.objectschema.schemaadapter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.schema.Database;
import ch.nolix.system.objectschema.schematool.DatabaseTool;
import ch.nolix.systemapi.objectschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IDatabaseTool;

//class
public abstract class SchemaAdapter implements ISchemaAdapter {

  //constant
  private static final IDatabaseTool DATABASE_HELPER = new DatabaseTool();

  //attribute
  private final CloseController closeController = CloseController.forElement(this);

  //attribute
  private IDatabase database;

  //attribute
  private final ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter rawSchemaAdapter;

  //attribute
  private int saveCount;

  //constructor
  protected SchemaAdapter(
    final String databaseName,
    final ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter rawSchemaAdapter) {

    GlobalValidator
      .assertThat(rawSchemaAdapter)
      .thatIsNamed(ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter.class)
      .isNotNull();

    this.rawSchemaAdapter = rawSchemaAdapter;

    getStoredCloseController().createCloseDependencyTo(rawSchemaAdapter);

    resetUsingDatabaseName(databaseName);
  }

  //method
  @Override
  public ISchemaAdapter addTable(final ITable table) {

    database.addTable(table);

    return this;
  }

  //method
  @Override
  public final CloseController getStoredCloseController() {
    return closeController;
  }

  //method
  @Override
  public final ITable getStoredTableByName(final String name) {
    return DATABASE_HELPER.getStoredTableWithGivenName(database, name);
  }

  //method
  @Override
  public final IContainer<ITable> getStoredTables() {
    return database.getStoredTables();
  }

  //method
  @Override
  public final int getSaveCount() {
    return saveCount;
  }

  //method
  @Override
  public int getTableCount() {
    return database.getTableCount();
  }

  //method
  @Override
  public final boolean hasChanges() {
    return rawSchemaAdapter.hasChanges();
  }

  //method
  @Override
  public final void noteClose() {
  }

  //method
  @Override
  public final void reset() {
    resetUsingDatabaseName(database.getName());
  }

  //method
  @Override
  public final void saveChanges() {
    try {

      DATABASE_HELPER.assertAllBackReferencesAreValid(database);
      rawSchemaAdapter.saveChanges();

      saveCount++;
    } finally {
      reset();
    }
  }

  //method
  private void resetUsingDatabaseName(final String databaseName) {

    database = new Database(databaseName);
    database.setRawSchemaAdapter(rawSchemaAdapter);

    rawSchemaAdapter.reset();
  }
}
