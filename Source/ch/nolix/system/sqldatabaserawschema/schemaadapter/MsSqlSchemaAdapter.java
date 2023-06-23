//package declaration
package ch.nolix.system.sqldatabaserawschema.schemaadapter;

//own imports
import ch.nolix.core.sql.SqlConnectionPool;

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
		super(
			databaseName,
			pSQLConnectionPool,
			ch.nolix.system.sqldatabasebasicschema.schemaadapter.MsSqlSchemaAdapter
			.forDatabaseWithGivenNameUsingConnectionFromGivenPool(
				databaseName,
				pSQLConnectionPool
			)
		);
	}
}
