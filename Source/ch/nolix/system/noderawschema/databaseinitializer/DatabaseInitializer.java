//package declaration
package ch.nolix.system.noderawschema.databaseinitializer;

//own imports
import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.databaseschemainspector.DatabaseSchemaInspector;

//class
public final class DatabaseInitializer {

  //constant
  private static final DatabaseSchemaInspector DATABASE_SCHEMA_INSPECTOR = new DatabaseSchemaInspector();

  //constant
  private static final InternalDatabaseInitializer INTERNAL_DATABASE_INITIALIZER = new InternalDatabaseInitializer();

  //method
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
