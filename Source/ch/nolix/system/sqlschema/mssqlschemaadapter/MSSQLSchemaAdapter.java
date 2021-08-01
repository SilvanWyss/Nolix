//package declaration
package ch.nolix.system.sqlschema.mssqlschemaadapter;

//own imports
import ch.nolix.common.sql.MSSQLConnection;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class MSSQLSchemaAdapter implements ISchemaAdapter {
	
	//attributes
	private final MSSQLSchemaReader mMSSQLSchemaReader;
	private final MSSQLSchemaWriter mMSSQLSchemaWriter;
	
	//constructor
	public MSSQLSchemaAdapter(final MSSQLConnection pMSSQLConnection) {
		mMSSQLSchemaReader = new MSSQLSchemaReader(pMSSQLConnection);
		mMSSQLSchemaWriter = new MSSQLSchemaWriter(pMSSQLConnection);
	}
	
	//method
	@Override
	public MSSQLSchemaReader getRefSchemaReader() {
		return mMSSQLSchemaReader;
	}
	
	//method
	@Override
	public MSSQLSchemaWriter getRefSchemaWriter() {
		return mMSSQLSchemaWriter;
	}
}
