//package declaration
package ch.nolix.system.sqlrawschema.databaseinitializer;

//own imports
import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.system.sqlrawschema.databaseschemainspector.DatabaseSchemaInspector;
import ch.nolix.systemapi.sqlbasicschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class DatabaseInitializer {
	
	//static attributes
	private static final DatabaseSchemaInspector databaseSchemaInspector = new DatabaseSchemaInspector();
	private static final InternalDatabaseInitializer internalDatabaseInitializer = new InternalDatabaseInitializer();
	
	//method
	public void initializeDatabaseIfNotInitialized(
		final String databaseName,
		final ISchemaAdapter schemaAdapter,
		final SQLConnectionPool pSQLConnectionPool
	) {
		switch (databaseSchemaInspector.getDatabaseSchemaState(schemaAdapter)) {
			case UNINITIALIZED:
				internalDatabaseInitializer.initializeDatabase(databaseName, schemaAdapter, pSQLConnectionPool);
				break;
			case INITIALIZED:
				break;
			case INVALID:
				throw GeneralException.withErrorMessage("The database has a schema that does not suit.");
		}
	}
}
