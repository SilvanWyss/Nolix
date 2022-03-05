//package declaration
package ch.nolix.system.objectschema.schemaadapter;

//own imports
import ch.nolix.core.sql.SQLConnectionPool;

//class
public final class MSSQLSchemaAdapter extends SchemaAdapter {
	
	//TODO: Create MSSQLSchemaAdapterBuilder.
	//static method
	public static MSSQLSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
		final String databaseName,
		final SQLConnectionPool pSQLConnectionPool
	) {
		return
		new MSSQLSchemaAdapter(
			databaseName,
			ch.nolix.system.sqlrawschema.schemaadapter.MSSQLSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(
				databaseName,
				pSQLConnectionPool
			)
		);
	}
	
	//constructor
	private MSSQLSchemaAdapter(
		String databaseName,
		final ch.nolix.system.sqlrawschema.schemaadapter.MSSQLSchemaAdapter pMSSQLSchemaAdapter
	) {
		super(databaseName, pMSSQLSchemaAdapter);
	}
}
