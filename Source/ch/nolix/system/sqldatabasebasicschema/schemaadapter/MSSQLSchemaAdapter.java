//package declaration
package ch.nolix.system.sqldatabasebasicschema.schemaadapter;

//own imports
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.system.sqldatabasebasicschema.mssqllanguage.MsSqlSchemaQueryCreator;
import ch.nolix.system.sqldatabasebasicschema.mssqllanguage.MsSqlSchemaStatementCreator;

//class
public final class MSSQLSchemaAdapter extends SchemaAdapter {
	
	//static method
	public static MSSQLSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
		final String databaseName,	
		final SqlConnectionPool pSQLConnectionPool
	) {
		return new MSSQLSchemaAdapter(databaseName, pSQLConnectionPool);
	}
	
	//constructor
	private MSSQLSchemaAdapter(final String databaseName, final SqlConnectionPool pSQLConnectionPool) {
		super(databaseName, pSQLConnectionPool, new MsSqlSchemaQueryCreator(), new MsSqlSchemaStatementCreator());
	}
}
