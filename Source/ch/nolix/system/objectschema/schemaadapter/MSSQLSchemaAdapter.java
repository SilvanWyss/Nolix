//package declaration
package ch.nolix.system.objectschema.schemaadapter;

//own imports
import ch.nolix.core.sql.SQLConnection;

//class
public final class MSSQLSchemaAdapter extends SchemaAdapter {
	
	//constructor
	public MSSQLSchemaAdapter(String databaseName, final SQLConnection pSQLConnection) {
		super(
			databaseName,
			new ch.nolix.system.sqlrawobjectschema.schemaadapter.MSSQLSchemaAdapter(databaseName, pSQLConnection)
		);
	}
}
