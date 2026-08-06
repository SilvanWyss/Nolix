/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.adapter;

import ch.nolix.base.resourcecontrol.closecontroller.CloseController;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.system.objectschema.model.Database;
import ch.nolix.system.objectschema.modelsearcher.DatabaseSearcher;
import ch.nolix.system.objectschema.modelvalidator.DatabaseValidator;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.schemaadapter.ISchemaAdapter;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractSchemaAdapter implements ISchemaAdapter {
  private static final DatabaseSearcher DATABASE_SEARCHER = new DatabaseSearcher();

  private static final DatabaseValidator DATABASE_VALIDATOR = new DatabaseValidator();

  private final ICloseController closeController = CloseController.forElement(this);

  private IDatabase database;

  private final ch.nolix.systemapi.midschema.adapter.SchemaAdapter midSchemaAdapter;

  private int saveCount;

  protected AbstractSchemaAdapter(
    final String databaseName,
    final ch.nolix.systemapi.midschema.adapter.SchemaAdapter midSchemaAdapter) {
    Validator
      .assertThat(midSchemaAdapter)
      .thatIsNamed(ch.nolix.systemapi.midschema.adapter.SchemaAdapter.class)
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
  public final ExtendedIterable<? extends ITable> getStoredTables() {
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
    // Does nothing.
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
    database = Database.withNameAndMidSchemaAdapter(databaseName, midSchemaAdapter);
    midSchemaAdapter.reset();
  }
}
