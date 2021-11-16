//package declaration
package ch.nolix.system.objectschema.schemaadapter;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.techapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class MSSQLSchemaAdapter extends SchemaAdapter {
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//constructor
	public MSSQLSchemaAdapter(String databaseName, final SQLConnection pSQLConnection) {
		
		super(databaseName);
		
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		
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
