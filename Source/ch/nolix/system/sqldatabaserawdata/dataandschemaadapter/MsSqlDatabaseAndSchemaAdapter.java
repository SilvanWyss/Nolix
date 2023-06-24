//package declaration
package ch.nolix.system.sqldatabaserawdata.dataandschemaadapter;

//own imports
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.system.rawdatabase.databaseandschemaadapter.BaseDataAndSchemaAdapter;
import ch.nolix.system.sqldatabaserawdata.databaseadapter.MsSqlDatabaseAdapter;
import ch.nolix.system.sqldatabaserawschema.schemaadapter.MsSqlSchemaAdapter;

//class
public final class MsSqlDatabaseAndSchemaAdapter extends BaseDataAndSchemaAdapter {
	
	//static method
	public static MsSqlDatabaseAndSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
		final String databaseName,
		final SqlConnectionPool sqlConnectionPool
	) {
		return
		new MsSqlDatabaseAndSchemaAdapter(
			MsSqlDatabaseAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, sqlConnectionPool),
			MsSqlSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, sqlConnectionPool)
		);
	}
		
	//constructor
	private MsSqlDatabaseAndSchemaAdapter(
		final MsSqlDatabaseAdapter pMSSQLDatabaseAdapter,
		final MsSqlSchemaAdapter pMSSQLSchemaAdapter
	) {
		super(pMSSQLDatabaseAdapter, pMSSQLSchemaAdapter);
	}
}
