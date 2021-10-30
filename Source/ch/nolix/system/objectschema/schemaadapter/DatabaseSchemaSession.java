//package declaration
package ch.nolix.system.objectschema.schemaadapter;

import ch.nolix.system.objectschema.schema.Database;
import ch.nolix.techapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;

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
		return schemaAdapter.hasChanges();
	}
	
	//method
	public void saveChanges() {
		schemaAdapter.saveChanges();
		close();
	}
}
