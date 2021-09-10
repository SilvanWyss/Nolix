//package declaration
package ch.nolix.system.sqlintermediateschema.schemaadapter;

//own imports
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.system.sqlintermediateschema.databaseinitializer.DatabaseInitializer;
import ch.nolix.system.sqlintermediateschema.schemareader.SchemaReader;
import ch.nolix.system.sqlintermediateschema.schemawriter.SchemaWriter;
import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public abstract class SchemaAdapter implements ISchemaAdapter {
	
	//static attribute
	private static final DatabaseInitializer databaseInitializer = new DatabaseInitializer();
	
	//attributes
	private final SchemaReader mSQLIntermediateSchemaReader;
	private final SchemaWriter mSQLIntermediateSchemaWriter;
	
	//constructor
	public SchemaAdapter(final SQLConnection pSQLConnection, final ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter schemaAdapter) {
		
		databaseInitializer.initializeDatabaseIfNotInitialized(schemaAdapter);
		
		mSQLIntermediateSchemaReader = new SchemaReader(pSQLConnection, schemaAdapter.getRefSchemaReader());
		mSQLIntermediateSchemaWriter = new SchemaWriter(pSQLConnection, schemaAdapter.getRefSchemaWriter());
	}
	
	//method
	@Override
	public final SchemaReader getRefSchemaReader() {
		return mSQLIntermediateSchemaReader;
	}
	
	//method
	@Override
	public final SchemaWriter getRefSchemaWriter() {
		return mSQLIntermediateSchemaWriter;
	}
}
