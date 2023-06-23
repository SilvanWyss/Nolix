//package declaration
package ch.nolix.system.sqldatabasebasicschema.schemaadapter;

//own imports
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.system.sqldatabasebasicschema.mssqllanguage.MsSqlSchemaQueryCreator;
import ch.nolix.system.sqldatabasebasicschema.mssqllanguage.MsSqlSchemaStatementCreator;

//class
public final class MsSqlSchemaAdapter extends SchemaAdapter {
	
	//static method
	public static MsSqlSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
		final String databaseName,	
		final SqlConnectionPool pSQLConnectionPool
	) {
		return new MsSqlSchemaAdapter(databaseName, pSQLConnectionPool);
	}
	
	//constructor
	private MsSqlSchemaAdapter(final String databaseName, final SqlConnectionPool pSQLConnectionPool) {
		super(databaseName, pSQLConnectionPool, new MsSqlSchemaQueryCreator(), new MsSqlSchemaStatementCreator());
	}
}
