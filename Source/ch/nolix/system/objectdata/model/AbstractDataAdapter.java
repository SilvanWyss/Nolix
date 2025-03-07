package ch.nolix.system.objectdata.model;

import java.util.function.Supplier;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.system.objectdata.changesetsaver.ChangeSetSaver;
import ch.nolix.systemapi.objectdataapi.adapterapi.IDataAdapter;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntityTypeSet;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.rawdataapi.adapterapi.IDataAdapterAndSchemaReader;

public abstract class AbstractDataAdapter implements IDataAdapter {

  private static final SchemaInitializer SCHEMA_INITIALIZER = new SchemaInitializer();

  private static final ChangeSetSaver DATA_SAVER = new ChangeSetSaver();

  private final String databaseName;

  private final IEntityTypeSet entityTypeSet;

  private final IDataAdapterAndSchemaReader rawDataAndSchemaAdapter;

  private final ICloseController closeController = CloseController.forElement(this);

  private int saveCount;

  private Database database;

  protected AbstractDataAdapter(
    final String databaseName,
    final IEntityTypeSet entityTypeSet,
    final ISchemaAdapter schemaAdapter,
    final Supplier<IDataAdapterAndSchemaReader> rawDataAndSchemaReaderCreator) {

    Validator.assertThat(databaseName).thatIsNamed("database name").isNotBlank();

    SCHEMA_INITIALIZER.initializeDatabaseFromSchemaUsingSchemaAdapterIfDatabaseIsEmpty(
      entityTypeSet,
      schemaAdapter);

    schemaAdapter.close();

    this.rawDataAndSchemaAdapter = rawDataAndSchemaReaderCreator.get();
    this.databaseName = databaseName;
    this.entityTypeSet = entityTypeSet;
    this.database = Database.withSchemaAndRawDataAdapterAndSchemaReader(entityTypeSet, rawDataAndSchemaAdapter);

    createCloseDependencyTo(rawDataAndSchemaAdapter);
  }

  @Override
  public final void createCloseDependencyTo(final GroupCloseable element) {
    IDataAdapter.super.createCloseDependencyTo(element);
  }

  @Override
  public final String getDatabaseName() {
    return databaseName;
  }

  @Override
  public final int getSaveCount() {
    return saveCount;
  }

  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public final <E extends IEntity> ITable<E> getStoredTableByEntityType(
    final Class<E> entityType) {
    return database.getStoredTableByEntityType(entityType);
  }

  @Override
  public final boolean hasChanges() {
    return database.isEdited();
  }

  @Override
  public final <E extends IEntity> IDataAdapter insertEntity(
    final E entity,
    @SuppressWarnings("unchecked") final E... entities) {

    database.insertEntity(entity);

    for (final var e : entities) {
      database.insertEntity(e);
    }

    return this;
  }

  @Override
  public final void noteClose() {
    database.internalClose();
  }

  @Override
  public final synchronized void reset() {

    database.internalClose();

    database = Database.withSchemaAndRawDataAdapterAndSchemaReader(entityTypeSet, rawDataAndSchemaAdapter);
  }

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

    DATA_SAVER.saveChangesOfDatabaseSynchronously(database, database.internalGetStoredDataAndSchemaAdapter());

    saveCount++;
  }
}
