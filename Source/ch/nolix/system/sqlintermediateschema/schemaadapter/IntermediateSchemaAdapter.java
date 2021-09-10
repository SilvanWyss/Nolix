//package declaration
package ch.nolix.system.sqlintermediateschema.schemaadapter;

//own imports
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.system.sqlintermediateschema.databaseinitializer.DatabaseInitializer;
import ch.nolix.system.sqlintermediateschema.schemareader.IntermediateSchemaReader;
import ch.nolix.system.sqlintermediateschema.schemawriter.IntermediateSchemaWriter;
import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.IIntermediateSchemaAdapter;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public abstract class IntermediateSchemaAdapter implements IIntermediateSchemaAdapter {
	
	//static attribute
	private static final DatabaseInitializer databaseInitializer = new DatabaseInitializer();
	
	//attributes
	private final IntermediateSchemaReader mSQLIntermediateSchemaReader;
	private final IntermediateSchemaWriter mSQLIntermediateSchemaWriter;
	
	//constructor
	public IntermediateSchemaAdapter(final SQLConnection pSQLConnection, final ISchemaAdapter schemaAdapter) {
		
		databaseInitializer.initializeDatabaseIfNotInitialized(schemaAdapter);
		
		mSQLIntermediateSchemaReader = new IntermediateSchemaReader(pSQLConnection, schemaAdapter.getRefSchemaReader());
		mSQLIntermediateSchemaWriter = new IntermediateSchemaWriter(pSQLConnection, schemaAdapter.getRefSchemaWriter());
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
