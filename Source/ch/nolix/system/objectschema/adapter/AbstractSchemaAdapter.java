package ch.nolix.system.objectschema.adapter;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.resourcecontrol.closecontroller.CloseController;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.system.objectschema.model.Database;
import ch.nolix.system.objectschema.modelsearcher.DatabaseSearcher;
import ch.nolix.system.objectschema.modelvalidator.DatabaseValidator;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelsearcher.IDatabaseSearcher;
import ch.nolix.systemapi.objectschema.modelvalidator.IDatabaseValidator;
import ch.nolix.systemapi.objectschema.schemaadapter.ISchemaAdapter;

public abstract class AbstractSchemaAdapter implements ISchemaAdapter {
  private static final IDatabaseSearcher DATABASE_SEARCHER = new DatabaseSearcher();

  private static final IDatabaseValidator DATABASE_VALIDATOR = new DatabaseValidator();

  private final ICloseController closeController = CloseController.forElement(this);

  private IDatabase database;

  private final ch.nolix.systemapi.midschema.adapter.ISchemaAdapter midSchemaAdapter;

  private int saveCount;

  protected AbstractSchemaAdapter(
    final String databaseName,
    final ch.nolix.systemapi.midschema.adapter.ISchemaAdapter midSchemaAdapter) {
    Validator
      .assertThat(midSchemaAdapter)
      .thatIsNamed(ch.nolix.systemapi.midschema.adapter.ISchemaAdapter.class)
      .isNotNull();

    this.midSchemaAdapter = midSchemaAdapter;

    getStoredCloseController().createCloseDependencyTo(this.midSchemaAdapter);

    resetUsingDatabaseName(databaseName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISchemaAdapter addTable(final ITable table) {
    database.addTable(table);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean databaseIsEmpty() {
    return (getTableCount() == 0);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ITable getStoredTableByName(final String name) {
    return DATABASE_SEARCHER.getStoredTableByName(database, name);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<ITable> getStoredTables() {
    return database.getStoredTables();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int getSaveCount() {
    return saveCount;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int getTableCount() {
    return database.getTableCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasChanges() {
    return midSchemaAdapter.hasChanges();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void noteClose() {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void reset() {
    resetUsingDatabaseName(database.getName());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void saveChanges() {
    try {
      DATABASE_VALIDATOR.assertAllBackReferencesAreValid(database);
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
