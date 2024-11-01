package ch.nolix.system.objectdata.data;

import java.util.function.Supplier;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.system.objectdata.changesetsaver.ChangeSetSaver;
import ch.nolix.system.objectdata.datatool.DatabaseTool;
import ch.nolix.systemapi.objectdataapi.dataadapterapi.IDataAdapter;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IDatabaseTool;
import ch.nolix.systemapi.objectdataapi.schemaapi.ISchema;
import ch.nolix.systemapi.objectschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

public abstract class DataAdapter implements IDataAdapter {

  private static final IDatabaseTool DATABASE_TOOL = new DatabaseTool();

  private static final SchemaInitializer SCHEMA_INITIALIZER = new SchemaInitializer();

  private static final ChangeSetSaver DATA_SAVER = new ChangeSetSaver();

  private final String databaseName;

  private final ISchema schema;

  private final Database database;

  private int saveCount;

  private final CloseController closeController = CloseController.forElement(this);

  protected DataAdapter(
    final String databaseName,
    final ISchemaAdapter schemaAdapter,
    final ISchema schema,
    final Supplier<IDataAndSchemaAdapter> dataAndSchemaAdapterCreator) {

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
  public final CloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public final <E extends IEntity> ITable<E> getStoredTableByEntityType(
    final Class<E> entityType) {
    return database.getStoredTableByEntityType(entityType);
  }

  @Override
  public final int getSaveCount() {
    return saveCount;
  }

  @Override
  public final boolean hasChanges() {
    return DATABASE_TOOL.hasChanges(database);
  }

  @Override
  public final <E extends IEntity> DataAdapter insertEntity(
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

  protected final String getDatabaseName() {
    return databaseName;
  }

  protected final ISchema getSchema() {
    return schema;
  }

  private synchronized void saveChangesAndIncrementSaveCount() {

    DATA_SAVER.saveChangesOfDatabaseSynchronously(database, database.internalGetStoredDataAndSchemaAdapter());

    saveCount++;
  }
}
