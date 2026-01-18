package ch.nolix.system.objectdata.adapter;

import java.util.function.Supplier;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.resourcecontrol.closecontroller.CloseController;
import ch.nolix.coreapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.coreapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.system.objectdata.model.Database;
import ch.nolix.system.objectdata.model.SchemaInitializer;
import ch.nolix.system.objectdata.persistence.DatabasePersister;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.adapter.IDataAdapter;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IEntityTypeSet;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.perstistence.IDatabasePersister;
import ch.nolix.systemapi.objectschema.schemaadapter.ISchemaAdapter;

public abstract class AbstractDataAdapter implements IDataAdapter {
  private static final IDatabasePersister DATABASE_PERSISTER = new DatabasePersister();

  private final String databaseName;

  private final IEntityTypeSet entityTypeSet;

  private final ICloseController closeController = CloseController.forElement(this);

  private int saveCount;

  private Database database;

  private IDataAdapterAndSchemaReader midDataAdapterAndSchemaReader;

  protected AbstractDataAdapter(
    final String databaseName,
    final IEntityTypeSet entityTypeSet,
    final ISchemaAdapter schemaAdapter,
    final Supplier<IDataAdapterAndSchemaReader> midDataAdapterAndSchemaReader) {
    Validator.assertThat(databaseName).thatIsNamed("database name").isNotBlank();

    SchemaInitializer.initializeDatabaseIfDatabaseIsEmpty(entityTypeSet, schemaAdapter);
    schemaAdapter.close();

    this.midDataAdapterAndSchemaReader = midDataAdapterAndSchemaReader.get();
    this.databaseName = databaseName;
    this.entityTypeSet = entityTypeSet;

    this.database = //
    Database.withEntityTypeSetAndMidDataAdapterAndSchemaReader(
      entityTypeSet,
      this.midDataAdapterAndSchemaReader.createEmptyCopy());

    createCloseDependencyTo(this.midDataAdapterAndSchemaReader);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void createCloseDependencyTo(final GroupCloseable element) {
    IDataAdapter.super.createCloseDependencyTo(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getDatabaseName() {
    return databaseName;
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
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final <E extends IEntity> ITable<E> getStoredTableByEntityType(
    final Class<E> entityType) {
    return database.getStoredTableByEntityType(entityType);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasChanges() {
    return database.isEdited();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IDataAdapter insertEntity(final IEntity entity) {
    database.insertEntity(entity);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void noteClose() {
    database.close();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final synchronized void reset() {
    database.close();

    midDataAdapterAndSchemaReader = midDataAdapterAndSchemaReader.createEmptyCopy();

    database = Database.withEntityTypeSetAndMidDataAdapterAndSchemaReader(entityTypeSet, midDataAdapterAndSchemaReader);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final synchronized void saveChanges() {
    try {
      saveChangesAndIncrementSaveCount();
    } finally {
      reset();
    }
  }

  protected final IEntityTypeSet getSchema() {
    return entityTypeSet;
  }

  private synchronized void saveChangesAndIncrementSaveCount() {
    DATABASE_PERSISTER.persistDatabaseChangesTransactional(database, midDataAdapterAndSchemaReader);

    saveCount++;
  }
}
