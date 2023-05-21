//package declaration
package ch.nolix.system.sqldatabaserawdata.dataandschemaadapter;

//own imports
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.system.rawdatabase.databaseandschemaadapter.BaseDataAndSchemaAdapter;
import ch.nolix.system.sqldatabaserawdata.databaseadapter.MSSQLDatabaseAdapter;
import ch.nolix.system.sqldatabaserawschema.schemaadapter.MSSQLSchemaAdapter;

//class
public final class MSSQLDatabaseAndSchemaAdapter extends BaseDataAndSchemaAdapter {
	
	//static method
	public static MSSQLDatabaseAndSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
		final String databaseName,
		final SQLConnectionPool pSQLConnectionPool
	) {
		return
		new MSSQLDatabaseAndSchemaAdapter(
			MSSQLDatabaseAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, pSQLConnectionPool),
			MSSQLSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, pSQLConnectionPool)
		);
	}
		
	//constructor
	private MSSQLDatabaseAndSchemaAdapter(
		final MSSQLDatabaseAdapter pMSSQLDatabaseAdapter,
		final MSSQLSchemaAdapter pMSSQLSchemaAdapter
	) {
		super(pMSSQLDatabaseAdapter, pMSSQLSchemaAdapter);
	}
}