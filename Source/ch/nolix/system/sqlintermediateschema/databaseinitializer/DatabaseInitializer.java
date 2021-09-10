//package declaration
package ch.nolix.system.sqlintermediateschema.databaseinitializer;

//own imports
import ch.nolix.common.errorcontrol.exception.GeneralException;
import ch.nolix.system.sqlintermediateschema.databaseschemainspector.DatabaseSchemaInspector;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class DatabaseInitializer {
	
	//static attributes
	private static final DatabaseSchemaInspector databaseSchemaInspector = new DatabaseSchemaInspector();
	private static final InternalDatabaseInitializer internalDatabaseInitializer = new InternalDatabaseInitializer();
	
	//method
	public void initializeDatabaseIfNotInitialized(ISchemaAdapter schemaAdapter) {
		switch (databaseSchemaInspector.getDatabaseSchemaState(schemaAdapter)) {
			case EMPTY:
				internalDatabaseInitializer.initializeDatabase(schemaAdapter);
				break;
			case INITIALIZED:
				break;
			case INVALID:
				throw new GeneralException("The database has a schema that does not suit.");
		}
	}
}
