//package declaration
package ch.nolix.system.nodedatabaserawschema.databaseinitializer;

//own imports
import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawschema.databaseschemainspector.DatabaseSchemaInspector;

//class
public final class DatabaseInitializer {
	
	//static attributes
	private static final DatabaseSchemaInspector databaseSchemaInspector = new DatabaseSchemaInspector();
	private static final InternalDatabaseInitializer internalDatabaseInitializer = new InternalDatabaseInitializer();
	
	//method
	public void initializeDatabaseIfNotInitialized(IMutableNode<?> databaseNode) {
		switch (databaseSchemaInspector.getDatabaseSchemaState(databaseNode)) {
			case UNINITIALIZED:
				internalDatabaseInitializer.initializeDatabase(databaseNode);
				break;
			case INITIALIZED:
				break;
			case INVALID:
				throw GeneralException.withErrorMessage("The database has a schema that does not suit.");
		}
	}
}
