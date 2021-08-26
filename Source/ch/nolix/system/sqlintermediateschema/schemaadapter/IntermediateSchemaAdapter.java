//package declaration
package ch.nolix.system.sqlintermediateschema.schemaadapter;

//own imports
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.IIntermediateSchemaAdapter;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public abstract class IntermediateSchemaAdapter implements IIntermediateSchemaAdapter {
	
	//attributes
	private final IntermediateSchemaReader mSQLIntermediateSchemaReader;
	private final IntermediateSchemaWriter mSQLIntermediateSchemaWriter;
	
	//constructor
	public IntermediateSchemaAdapter(final SQLConnection pSQLConnection, final ISchemaAdapter schemaAdapter) {
		mSQLIntermediateSchemaReader = new IntermediateSchemaReader(pSQLConnection, schemaAdapter);
		mSQLIntermediateSchemaWriter = new IntermediateSchemaWriter(pSQLConnection, schemaAdapter);
	}
	
	//method
	@Override
	public final IntermediateSchemaReader getRefIntermediateSchemaReader() {
		return mSQLIntermediateSchemaReader;
	}
	
	//method
	@Override
	public final IntermediateSchemaWriter getRefIntermediateSchemaWriter() {
		return mSQLIntermediateSchemaWriter;
	}
}
