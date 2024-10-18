package ch.nolix.system.noderawschema.databaseinitializer;

import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.databaseschemainspector.DatabaseSchemaInspector;

public final class DatabaseInitializer {

  private static final DatabaseSchemaInspector DATABASE_SCHEMA_INSPECTOR = new DatabaseSchemaInspector();

  private static final InternalDatabaseInitializer INTERNAL_DATABASE_INITIALIZER = new InternalDatabaseInitializer();

  public void initializeDatabaseIfNotInitialized(IMutableNode<?> databaseNode) {
    switch (DATABASE_SCHEMA_INSPECTOR.getDatabaseSchemaState(databaseNode)) {
      case UNINITIALIZED:
        INTERNAL_DATABASE_INITIALIZER.initializeDatabase(databaseNode);
        break;
      case INITIALIZED:
        break;
      case INVALID:
        throw GeneralException.withErrorMessage("The database has a schema that does not suit.");
    }
  }
}
