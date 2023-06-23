//package declaration
package ch.nolix.system.sqldatabaserawdata.dataandschemaadapter;

//own imports
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.system.rawdatabase.databaseandschemaadapter.BaseDataAndSchemaAdapter;
import ch.nolix.system.sqldatabaserawdata.databaseadapter.MSSQLDatabaseAdapter;
import ch.nolix.system.sqldatabaserawschema.schemaadapter.MSSQLSchemaAdapter;

//class
public final class MsSqlDatabaseAndSchemaAdapter extends BaseDataAndSchemaAdapter {
	
	//static method
	public static MsSqlDatabaseAndSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
		final String databaseName,
		final SqlConnectionPool pSQLConnectionPool
	) {
		return
		new MsSqlDatabaseAndSchemaAdapter(
			MSSQLDatabaseAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, pSQLConnectionPool),
			MSSQLSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, pSQLConnectionPool)
		);
	}
		
	//constructor
	private MsSqlDatabaseAndSchemaAdapter(
		final MSSQLDatabaseAdapter pMSSQLDatabaseAdapter,
		final MSSQLSchemaAdapter pMSSQLSchemaAdapter
	) {
		super(pMSSQLDatabaseAdapter, pMSSQLSchemaAdapter);
	}
}
