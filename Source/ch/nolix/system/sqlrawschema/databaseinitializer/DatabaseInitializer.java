package ch.nolix.system.sqlrawschema.databaseinitializer;

import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.system.sqlrawschema.databaseschemainspector.DatabaseSchemaInspector;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

public final class DatabaseInitializer {

  private static final DatabaseSchemaInspector DATABASE_SCHEMA_INSPECTOR = new DatabaseSchemaInspector();

  private static final InternalDatabaseInitializer INTERNAL_DATABASE_INITIALIZER = new InternalDatabaseInitializer();

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
