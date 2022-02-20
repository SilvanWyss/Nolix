//package declaration
package ch.nolix.system.objectschema.schemaadapter;

//own imports
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.systemapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class MSSQLSchemaAdapter extends SchemaAdapter {
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//constructor
	public MSSQLSchemaAdapter(String databaseName, final SQLConnection pSQLConnection) {
		
		super(databaseName);
		
		createCloseDependencyTo(pSQLConnection);
		
		mSQLConnection = pSQLConnection;
		
		mSQLConnection.execute("USE " + databaseName);
		
		initializeSession();
	}
	
	//method
	@Override
	protected ISchemaAdapter createRawSchemaAdapter() {
		return new ch.nolix.system.sqlrawobjectschema.schemaadapter.MSSQLSchemaAdapter(mSQLConnection);
	}
}
