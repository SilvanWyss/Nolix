//package declaration
package ch.nolix.system.sqldatabaserawschema.schemaadapter;

//own imports
import ch.nolix.core.sql.SqlConnectionPool;

//class
public final class MSSQLSchemaAdapter extends SchemaAdapter {
	
	//static method
	public static MSSQLSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
		final String databaseName,
		final SqlConnectionPool pSQLConnectionPool
	) {
		return new MSSQLSchemaAdapter(databaseName, pSQLConnectionPool);
	}
	
	//constructor
	private MSSQLSchemaAdapter(final String databaseName, final SqlConnectionPool pSQLConnectionPool) {
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
