//package declaration
package ch.nolix.system.sqldatabaserawschema.databaseinitializer;

//own imports
import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.system.sqldatabaserawschema.databaseschemainspector.DatabaseSchemaInspector;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class DatabaseInitializer {

  //constant
  private static final DatabaseSchemaInspector DATABASE_SCHEMA_INSPECTOR = new DatabaseSchemaInspector();

  //constant
  private static final InternalDatabaseInitializer INTERNAL_DATABASE_INITIALIZER = new InternalDatabaseInitializer();

  //method
  public void initializeDatabaseIfNotInitialized(
    final String databaseName,
    final ISchemaAdapter schemaAdapter,
    final SqlConnectionPool sqlConnectionPool) {
    switch (DATABASE_SCHEMA_INSPECTOR.getDatabaseSchemaState(schemaAdapter)) {
      case UNINITIALIZED:
        INTERNAL_DATABASE_INITIALIZER.initializeDatabase(databaseName, schemaAdapter, sqlConnectionPool);
        break;
      case INITIALIZED:
        break;
      case INVALID:
        throw GeneralException.withErrorMessage("The database has a schema that does not suit.");
    }
  }
}
