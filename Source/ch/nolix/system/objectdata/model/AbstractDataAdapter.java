package ch.nolix.system.objectdata.model;

import java.util.function.Supplier;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.system.objectdata.changesetsaver.ChangeSetSaver;
import ch.nolix.systemapi.objectdataapi.adapterapi.IDataAdapter;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.schemamodelapi.ISchema;
import ch.nolix.systemapi.objectschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.rawdataapi.adapterapi.IDataAdapterAndSchemaReader;

public abstract class AbstractDataAdapter implements IDataAdapter {

  private static final SchemaInitializer SCHEMA_INITIALIZER = new SchemaInitializer();

  private static final ChangeSetSaver DATA_SAVER = new ChangeSetSaver();

  private final String databaseName;

  private final ISchema schema;

  private final Database database;

  private int saveCount;

  private final ICloseController closeController = CloseController.forElement(this);

  protected AbstractDataAdapter(
    final String databaseName,
    final ISchemaAdapter schemaAdapter,
    final ISchema schema,
    final Supplier<IDataAdapterAndSchemaReader> dataAndSchemaAdapterCreator) {

    GlobalValidator.assertThat(databaseName).thatIsNamed("database name").isNotBlank();
    GlobalValidator.assertThat(schema).thatIsNamed("schema").isNotNull();

    SCHEMA_INITIALIZER.initializeDatabaseFromSchemaUsingSchemaAdapterIfDatabaseIsEmpty(
      schema,
      schemaAdapter);
    schemaAdapter.close();

    this.schema = schema;
    this.databaseName = databaseName;
    final var dataAndSchemaAdapter = dataAndSchemaAdapterCreator.get();
    database = Database.withDataAndSchemaAdapterAndSchema(dataAndSchemaAdapter, schema);
    getStoredCloseController().createCloseDependencyTo(dataAndSchemaAdapter);
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
    database.internalReset();
  }

  @Override
  public final synchronized void saveChanges() {
    try {
      saveChangesAndIncrementSaveCount();
    } finally {
      reset();
    }
  }

  protected final ISchema getSchema() {
    return schema;
  }

  private synchronized void saveChangesAndIncrementSaveCount() {

    DATA_SAVER.saveChangesOfDatabaseSynchronously(database, database.internalGetStoredDataAndSchemaAdapter());

    saveCount++;
  }
}
