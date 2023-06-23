//package declaration
package ch.nolix.system.sqldatabaserawdata.databaseadapter;

//own imports
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.system.sqldatabaserawdata.sqlsyntax.SqlSyntaxProvider;
import ch.nolix.system.sqldatabaserawschema.schemaadapter.MSSQLSchemaAdapter;

//class
public final class MSSQLDatabaseAdapter extends DatabaseAdapter {
	
	//static method
	public static MSSQLDatabaseAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
		final String databaseName,
		final SqlConnectionPool pSQLConnectionPool
	) {
		return new MSSQLDatabaseAdapter(databaseName, pSQLConnectionPool);
	}
	
	//constructor
	private MSSQLDatabaseAdapter(final String databaseName, final SqlConnectionPool pSQLConnectionPool) {
		super(
			databaseName,
			pSQLConnectionPool,
			MSSQLSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, pSQLConnectionPool),
			new SqlSyntaxProvider()
		);
	}
}
