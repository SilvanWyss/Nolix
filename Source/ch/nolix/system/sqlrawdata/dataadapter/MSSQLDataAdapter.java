//package declaration
package ch.nolix.system.sqlrawdata.dataadapter;

//own imports
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.system.sqlrawdata.mssql.SQLSyntaxProvider;
import ch.nolix.system.sqlrawschema.schemaadapter.MSSQLSchemaAdapter;

//class
public final class MSSQLDataAdapter extends DataAdapter {
	
	//static method
	public static MSSQLDataAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
		final String databaseName,
		final SQLConnectionPool pSQLConnectionPool
	) {
		return new MSSQLDataAdapter(databaseName, pSQLConnectionPool);
	}
	
	//constructor
	private MSSQLDataAdapter(final String databaseName, final SQLConnectionPool pSQLConnectionPool) {
		super(
			databaseName,
			pSQLConnectionPool,
			MSSQLSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, pSQLConnectionPool),
			new SQLSyntaxProvider()
		);
	}
}
