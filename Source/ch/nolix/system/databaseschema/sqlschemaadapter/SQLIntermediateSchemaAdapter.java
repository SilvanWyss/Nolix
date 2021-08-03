//package declaration
package ch.nolix.system.databaseschema.sqlschemaadapter;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.techapi.databaseschemaapi.intermediateschemaapi.IIntermediateSchemaAdapter;

//class
public final class SQLIntermediateSchemaAdapter implements IIntermediateSchemaAdapter {
	
	//attributes
	private final SQLConnection mSQLConnection;
	private final SQLIntermediateSchemaReader mSQLIntermediateSchemaReader;
	private final SQLIntermediateSchemaWriter mSQLIntermediateSchemaWriter;
	
	//constructor
	public SQLIntermediateSchemaAdapter(final SQLConnection pSQLConnection) {
		
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		mSQLIntermediateSchemaReader = new SQLIntermediateSchemaReader(mSQLConnection);
		mSQLIntermediateSchemaWriter = new SQLIntermediateSchemaWriter(mSQLConnection);
	}
	
	//method
	@Override
	public SQLIntermediateSchemaReader getRefIntermediateSchemaReader() {
		return mSQLIntermediateSchemaReader;
	}
	
	//method
	@Override
	public SQLIntermediateSchemaWriter getRefIntermediateSchemaWriter() {
		return mSQLIntermediateSchemaWriter;
	}
}
