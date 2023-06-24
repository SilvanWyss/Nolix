//package declaration
package ch.nolix.system.sqldatabaserawdata.databaseadapter;

//own imports
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.system.sqldatabaserawdata.sqlsyntax.SqlSyntaxProvider;
import ch.nolix.system.sqldatabaserawschema.schemaadapter.MsSqlSchemaAdapter;

//class
public final class MsSqlDatabaseAdapter extends DatabaseAdapter {
	
	//static method
	public static MsSqlDatabaseAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
		final String databaseName,
		final SqlConnectionPool sqlConnectionPool
	) {
		return new MsSqlDatabaseAdapter(databaseName, sqlConnectionPool);
	}
	
	//constructor
	private MsSqlDatabaseAdapter(final String databaseName, final SqlConnectionPool sqlConnectionPool) {
		super(
			databaseName,
			sqlConnectionPool,
			MsSqlSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, sqlConnectionPool),
			new SqlSyntaxProvider()
		);
	}
}
