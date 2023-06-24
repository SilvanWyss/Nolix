//package declaration
package ch.nolix.system.sqldatabaserawschema.databaseinitializer;

//own imports
import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.system.sqldatabaserawschema.databaseschemainspector.DatabaseSchemaInspector;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class DatabaseInitializer {
	
	//static attributes
	private static final DatabaseSchemaInspector databaseSchemaInspector = new DatabaseSchemaInspector();
	private static final InternalDatabaseInitializer internalDatabaseInitializer = new InternalDatabaseInitializer();
	
	//method
	public void initializeDatabaseIfNotInitialized(
		final String databaseName,
		final ISchemaAdapter schemaAdapter,
		final SqlConnectionPool sqlConnectionPool
	) {
		switch (databaseSchemaInspector.getDatabaseSchemaState(schemaAdapter)) {
			case UNINITIALIZED:
				internalDatabaseInitializer.initializeDatabase(databaseName, schemaAdapter, sqlConnectionPool);
				break;
			case INITIALIZED:
				break;
			case INVALID:
				throw GeneralException.withErrorMessage("The database has a schema that does not suit.");
		}
	}
}
