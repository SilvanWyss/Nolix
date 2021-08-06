//package declaration
package ch.nolix.system.databaseschema.sqlschemaadapter;

//own imports
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.techapi.databaseschemaapi.intermediateschemaapi.IIntermediateSchemaAdapter;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class SQLIntermediateSchemaAdapter implements IIntermediateSchemaAdapter {
	
	//attributes
	private final SQLIntermediateSchemaReader mSQLIntermediateSchemaReader;
	private final SQLIntermediateSchemaWriter mSQLIntermediateSchemaWriter;
	
	//constructor
	public SQLIntermediateSchemaAdapter(final SQLConnection pSQLConnection, final ISchemaAdapter schemaAdapter) {
		mSQLIntermediateSchemaReader = new SQLIntermediateSchemaReader(pSQLConnection, schemaAdapter);
		mSQLIntermediateSchemaWriter = new SQLIntermediateSchemaWriter(pSQLConnection, schemaAdapter);
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
