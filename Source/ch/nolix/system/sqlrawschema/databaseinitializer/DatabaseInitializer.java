//package declaration
package ch.nolix.system.sqlrawschema.databaseinitializer;

//own imports
import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.system.sqlrawschema.databaseschemainspector.DatabaseSchemaInspector;
import ch.nolix.systemapi.sqlbasicschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class DatabaseInitializer {
	
	//static attributes
	private static final DatabaseSchemaInspector databaseSchemaInspector = new DatabaseSchemaInspector();
	private static final InternalDatabaseInitializer internalDatabaseInitializer = new InternalDatabaseInitializer();
	
	//method
	public void initializeDatabaseIfNotInitialized(ISchemaAdapter schemaAdapter) {
		switch (databaseSchemaInspector.getDatabaseSchemaState(schemaAdapter)) {
			case UNINITIALIZED:
				internalDatabaseInitializer.initializeDatabase(schemaAdapter);
				break;
			case INITIALIZED:
				break;
			case INVALID:
				throw GeneralException.withErrorMessage("The database has a schema that does not suit.");
		}
	}
}
