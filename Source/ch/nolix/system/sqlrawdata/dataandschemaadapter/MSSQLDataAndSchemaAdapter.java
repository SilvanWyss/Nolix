//package declaration
package ch.nolix.system.sqlrawdata.dataandschemaadapter;

//own imports
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.system.rawdata.dataandschemaadapter.BaseDataAndSchemaAdapter;
import ch.nolix.system.sqlrawdata.dataadapter.MSSQLDataAdapter;
import ch.nolix.system.sqlrawschema.schemaadapter.MSSQLSchemaAdapter;

//class
public final class MSSQLDataAndSchemaAdapter extends BaseDataAndSchemaAdapter {
	
	//static method
	public static MSSQLDataAndSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
		final String databaseName,
		final SQLConnectionPool pSQLConnectionPool
	) {
		return
		new MSSQLDataAndSchemaAdapter(
			MSSQLDataAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, pSQLConnectionPool),
			MSSQLSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, pSQLConnectionPool)
		);
	}
		
	//constructor
	private MSSQLDataAndSchemaAdapter(
		final MSSQLDataAdapter pMSSQLDataAdapter,
		final MSSQLSchemaAdapter pMSSQLSchemaAdapter
	) {
		super(pMSSQLDataAdapter, pMSSQLSchemaAdapter);
	}
}
