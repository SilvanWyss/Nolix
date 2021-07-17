//package declaration
package ch.nolix.system.databaseschema.sqlschemaadapter;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.system.databaseschema.schemaadapter.DatabaseSchemaAdapter;
import ch.nolix.techapi.databaseschemaapi.realschemaapi.IRealSchemaAdapter;

//class
public abstract class SQLDatabaseSchemaAdapter extends DatabaseSchemaAdapter {
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//constructor
	public SQLDatabaseSchemaAdapter(final String databaseName, final SQLConnection pSQLConnection) {
		
		super(databaseName);
		
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
	}
	
	//method
	@Override
	protected final IRealSchemaAdapter createRealSchemaAdapter() {
		return new SQLRealSchemaAdapter(mSQLConnection);
	}
}
