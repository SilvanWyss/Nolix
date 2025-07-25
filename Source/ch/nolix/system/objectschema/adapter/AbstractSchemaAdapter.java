package ch.nolix.system.objectschema.adapter;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.resourcecontrol.resourceclosing.ICloseController;
import ch.nolix.system.objectschema.model.Database;
import ch.nolix.system.objectschema.schematool.DatabaseTool;
import ch.nolix.systemapi.objectschemaapi.modelapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IDatabaseTool;

public abstract class AbstractSchemaAdapter implements ISchemaAdapter {

  private static final IDatabaseTool DATABASE_TOOL = new DatabaseTool();

  private final ICloseController closeController = CloseController.forElement(this);

  private IDatabase database;

  private final ch.nolix.systemapi.midschemaapi.adapterapi.ISchemaAdapter midSchemaAdapter;

  private int saveCount;

  protected AbstractSchemaAdapter(
    final String databaseName,
    final ch.nolix.systemapi.midschemaapi.adapterapi.ISchemaAdapter midSchemaAdapter) {

    Validator
      .assertThat(midSchemaAdapter)
      .thatIsNamed(ch.nolix.systemapi.midschemaapi.adapterapi.ISchemaAdapter.class)
      .isNotNull();

    this.midSchemaAdapter = midSchemaAdapter;

    getStoredCloseController().createCloseDependencyTo(this.midSchemaAdapter);

    resetUsingDatabaseName(databaseName);
  }

  @Override
  public ISchemaAdapter addTable(final ITable table) {

    database.addTable(table);

    return this;
  }

  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public final ITable getStoredTableByName(final String name) {
    return DATABASE_TOOL.getStoredTableWithGivenName(database, name);
  }

  @Override
  public final IContainer<ITable> getStoredTables() {
    return database.getStoredTables();
  }

  @Override
  public final int getSaveCount() {
    return saveCount;
  }

  @Override
  public int getTableCount() {
    return database.getTableCount();
  }

  @Override
  public final boolean hasChanges() {
    return midSchemaAdapter.hasChanges();
  }

  @Override
  public final void noteClose() {
  }

  @Override
  public final void reset() {
    resetUsingDatabaseName(database.getName());
  }

  @Override
  public final void saveChanges() {
    try {

      DATABASE_TOOL.assertAllBackReferencesAreValid(database);
      midSchemaAdapter.saveChanges();

      saveCount++;
    } finally {
      reset();
    }
  }

  private void resetUsingDatabaseName(final String databaseName) {

    database = new Database(databaseName, midSchemaAdapter);

    midSchemaAdapter.reset();
  }
}
