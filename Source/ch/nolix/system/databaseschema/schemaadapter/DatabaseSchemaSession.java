//package declaration
package ch.nolix.system.databaseschema.schemaadapter;

//own imports
import ch.nolix.system.databaseschema.schema.Database;
import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.IIntermediateSchemaAdapter;

//class
final class DatabaseSchemaSession implements AutoCloseable {
	
	//attributes
	private final Database database;
	private final IIntermediateSchemaAdapter intermediateSchemaAdapter;
	
	//constructor
	public DatabaseSchemaSession(final String databaseName, final IIntermediateSchemaAdapter intermediateSchemaAdapter) {
		
		database = new Database(databaseName);
		database.setRealSchemaAdapter(intermediateSchemaAdapter);
		
		this.intermediateSchemaAdapter = intermediateSchemaAdapter;
	}
	
	//method
	@Override
	public void close() {
		database.close();
	}
	
	//method
	public Database getRefDatabase() {
		return database;
	}
	
	//method
	public boolean hasChanges() {
		return intermediateSchemaAdapter.getRefIntermediateSchemaWriter().hasChanges();
	}
	
	//method
	public void saveChanges() {
		intermediateSchemaAdapter.getRefIntermediateSchemaWriter().saveChanges();
		close();
	}
}
