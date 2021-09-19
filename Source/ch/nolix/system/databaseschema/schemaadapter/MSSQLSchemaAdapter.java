//package declaration
package ch.nolix.system.databaseschema.schemaadapter;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class MSSQLSchemaAdapter extends SchemaAdapter {
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//constructor
	public MSSQLSchemaAdapter(String databaseName, final SQLConnection pSQLConnection) {
		
		super(databaseName);
		
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
	}
	
	//method
	@Override
	protected ISchemaAdapter createIntermediateSchemaAdapter() {
		return new ch.nolix.system.sqlintermediateschema.schemaadapter.MSSQLSchemaAdapter(mSQLConnection);
	}	
}
