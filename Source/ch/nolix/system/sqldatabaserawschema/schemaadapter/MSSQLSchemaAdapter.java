//package declaration
package ch.nolix.system.sqldatabaserawschema.schemaadapter;

//own imports
import ch.nolix.core.sql.SQLConnectionPool;

//class
public final class MSSQLSchemaAdapter extends SchemaAdapter {
	
	//static method
	public static MSSQLSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
		final String databaseName,
		final SQLConnectionPool pSQLConnectionPool
	) {
		return new MSSQLSchemaAdapter(databaseName, pSQLConnectionPool);
	}
	
	//constructor
	private MSSQLSchemaAdapter(final String databaseName, final SQLConnectionPool pSQLConnectionPool) {
		super(
			databaseName,
			pSQLConnectionPool,
			ch.nolix.system.sqldatabasebasicschema.schemaadapter.MSSQLSchemaAdapter
			.forDatabaseWithGivenNameUsingConnectionFromGivenPool(
				databaseName,
				pSQLConnectionPool
			)
		);
	}
}
