//package declaration
package ch.nolix.system.objectdatabase.database;

import java.util.function.Supplier;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.system.objectdatabase.databasehelper.DatabaseHelper;
import ch.nolix.systemapi.objectdatabaseapi.dataadapterapi.IDataAdapter;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IDatabaseHelper;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;
import ch.nolix.systemapi.objectschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.rawdatabaseapi.databaseandschemaadapterapi.IDataAndSchemaAdapter;

//class
public abstract class DataAdapter implements IDataAdapter<DataAdapter> {

  //constant
  private static final IDatabaseHelper DATABASE_HELPER = new DatabaseHelper();

  //constant
  private static final SchemaInitializer SCHEMA_INITIALIZER = new SchemaInitializer();

  //constant
  private static final DatabaseSaver DATABASE_SAVER = new DatabaseSaver();

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
    return DATABASE_HELPER.hasChanges(database);
  }

  //method
  @Override
  public final <E extends IEntity> DataAdapter insert(final E entity) {

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
  public final void reset() {
    database.internalReset();
  }

  //method
  @Override
  public final void saveChanges() {
    try {
      justSaveChanges();
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
  private void justSaveChanges() {

    DATABASE_SAVER.saveChangesOfDatabaseThreadSafe(database);

    saveCount++;
  }
}
