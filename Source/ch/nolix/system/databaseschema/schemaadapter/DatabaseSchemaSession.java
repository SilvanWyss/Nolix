//package declaration
package ch.nolix.system.databaseschema.schemaadapter;

//own imports
import ch.nolix.system.databaseschema.schema.Database;
import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.ISchemaAdapter;

//class
final class DatabaseSchemaSession implements AutoCloseable {
	
	//attributes
	private final Database database;
	private final ISchemaAdapter schemaAdapter;
	
	//constructor
	public DatabaseSchemaSession(final String databaseName, final ISchemaAdapter schemaAdapter) {
		
		database = new Database(databaseName);
		database.setRealSchemaAdapter(schemaAdapter);
		
		this.schemaAdapter = schemaAdapter;
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
		return schemaAdapter.getRefIntermediateSchemaWriter().hasChanges();
	}
	
	//method
	public void saveChanges() {
		schemaAdapter.getRefIntermediateSchemaWriter().saveChanges();
		close();
	}
}
