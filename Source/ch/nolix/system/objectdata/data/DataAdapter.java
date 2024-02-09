//package declaration
package ch.nolix.system.objectdata.data;

//Java imports
import java.util.function.Supplier;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.system.objectdata.datatool.DatabaseTool;
import ch.nolix.systemapi.objectdataapi.dataadapterapi.IDataAdapter;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IDatabaseTool;
import ch.nolix.systemapi.objectdataapi.schemaapi.ISchema;
import ch.nolix.systemapi.objectschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

//class
public abstract class DataAdapter implements IDataAdapter<DataAdapter> {

  //constant
  private static final IDatabaseTool DATABASE_TOOL = new DatabaseTool();

  //constant
  private static final SchemaInitializer SCHEMA_INITIALIZER = new SchemaInitializer();

  //constant
  private static final DataSaver DATA_SAVER = new DataSaver();

  //attribute
  private final String databaseName;

  //attribute
  private final ISchema schema;

  //attribute
  private final Database database;

  //attribute
  private int saveCount;

  //attribute
  private final CloseController closeController = CloseController.forElement(this);

  //constructor
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

  //method
  @Override
  public final CloseController getStoredCloseController() {
    return closeController;
  }

  //method
  @Override
  public final <E extends IEntity> ITable<E> getStoredTableByEntityType(
    final Class<E> entityType) {
    return database.getStoredTableByEntityType(entityType);
  }

  //method
  @Override
  public final int getSaveCount() {
    return saveCount;
  }

  //method
  @Override
  public final boolean hasChanges() {
    return DATABASE_TOOL.hasChanges(database);
  }

  //method
  @Override
  public final <E extends IEntity> DataAdapter insertEntity(final E entity) {

    database.insertEntity(entity);

    return this;
  }

  //method
  @Override
  public final void noteClose() {
    database.internalClose();
  }

  //method
  @Override
  public final synchronized void reset() {
    database.internalReset();
  }

  //method
  @Override
  public final synchronized void saveChanges() {
    try {
      saveChangesAndIncrementSaveCount();
    } finally {
      reset();
    }
  }

  //method
  protected final String getDatabaseName() {
    return databaseName;
  }

  //method
  protected final ISchema getSchema() {
    return schema;
  }

  //method
  private synchronized void saveChangesAndIncrementSaveCount() {

    DATA_SAVER.saveChangesOfDatabaseSynchronously(database);

    saveCount++;
  }
}
