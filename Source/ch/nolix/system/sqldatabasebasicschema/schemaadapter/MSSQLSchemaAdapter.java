//package declaration
package ch.nolix.system.sqldatabasebasicschema.schemaadapter;

//own imports
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.system.sqldatabasebasicschema.mssqllanguage.MSSQLSchemaQueryCreator;
import ch.nolix.system.sqldatabasebasicschema.mssqllanguage.MSSQLSchemaStatementCreator;

//class
public final class MSSQLSchemaAdapter extends SchemaAdapter {
	
	//static method
	public static MSSQLSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
		final String databaseName,	
		final SQLConnectionPool pSQLConnectionPool
	) {
		return new MSSQLSchemaAdapter(databaseName, pSQLConnectionPool);
	}
	
	//constructor
	private MSSQLSchemaAdapter(final String databaseName, final SQLConnectionPool pSQLConnectionPool) {
		super(databaseName, pSQLConnectionPool, new MSSQLSchemaQueryCreator(), new MSSQLSchemaStatementCreator());
	}
}