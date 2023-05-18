//package declaration
package ch.nolix.system.sqlrawdata.databaseadapter;

//own imports
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.system.sqlrawdata.sqlsyntax.SQLSyntaxProvider;
import ch.nolix.system.sqlrawschema.schemaadapter.MSSQLSchemaAdapter;

//class
public final class MSSQLDatabaseAdapter extends DatabaseAdapter {
	
	//static method
	public static MSSQLDatabaseAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
		final String databaseName,
		final SQLConnectionPool pSQLConnectionPool
	) {
		return new MSSQLDatabaseAdapter(databaseName, pSQLConnectionPool);
	}
	
	//constructor
	private MSSQLDatabaseAdapter(final String databaseName, final SQLConnectionPool pSQLConnectionPool) {
		super(
			databaseName,
			pSQLConnectionPool,
			MSSQLSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, pSQLConnectionPool),
			new SQLSyntaxProvider()
		);
	}
}
