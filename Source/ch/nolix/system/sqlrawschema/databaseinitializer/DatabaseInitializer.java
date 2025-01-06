package ch.nolix.system.sqlrawschema.databaseinitializer;

import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.system.sqlrawschema.databaseschemainspector.DatabaseInspector;
import ch.nolix.systemapi.sqlrawschemaapi.databaseinitializerapi.IDatabaseInspector;
import ch.nolix.systemapi.sqlschemaapi.adapterapi.ISchemaAdapter;
import ch.nolix.systemapi.sqlschemaapi.querycreatorapi.IQueryCreator;

public final class DatabaseInitializer {

  private static final IDatabaseInspector DATABASE_INSPECTOR = new DatabaseInspector();

  private static final InternalDatabaseInitializer INTERNAL_DATABASE_INITIALIZER = new InternalDatabaseInitializer();

  public void initializeDatabaseIfNotInitialized(
    final String databaseName,
    final ISchemaAdapter schemaAdapter,
    final SqlConnectionPool sqlConnectionPool,
    final IQueryCreator queryCreator) {
    try (final var sqlConnection = sqlConnectionPool.borrowResource()) {

      final var databaseState = DATABASE_INSPECTOR.getDatabasState(databaseName, sqlConnection, queryCreator);

      switch (databaseState) {
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
}
