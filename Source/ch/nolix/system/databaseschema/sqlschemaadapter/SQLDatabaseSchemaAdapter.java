//package declaration
package ch.nolix.system.databaseschema.sqlschemaadapter;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.MSSQLConnection;
import ch.nolix.system.databaseschema.schemaadapter.DatabaseSchemaAdapter;
import ch.nolix.system.sqlschema.mssqlschemaadapter.MSSQLSchemaAdapter;

//class
public abstract class SQLDatabaseSchemaAdapter extends DatabaseSchemaAdapter {
	
	//attribute
	private final MSSQLConnection mMSSQLConnection;
	
	//constructor
	public SQLDatabaseSchemaAdapter(final String databaseName, final MSSQLConnection pMSSQLConnection) {
		
		super(databaseName);
		
		Validator.assertThat(pMSSQLConnection).thatIsNamed(MSSQLConnection.class).isNotNull();
		
		mMSSQLConnection = pMSSQLConnection;
	}
	
	//method
	@Override
	protected final SQLIntermediateSchemaAdapter createIntermediateSchemaAdapter() {
		return new SQLIntermediateSchemaAdapter(mMSSQLConnection, new MSSQLSchemaAdapter(mMSSQLConnection));
	}
}
