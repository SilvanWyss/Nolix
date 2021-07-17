//package declaration
package ch.nolix.system.databaseschema.schemaadapter;

//own imports
import ch.nolix.system.databaseschema.schema.Database;
import ch.nolix.techapi.databaseschemaapi.realschemaapi.IRealSchemaAdapter;

//class
final class DatabaseSchemaSession implements AutoCloseable {
	
	//attributes
	private final Database database;
	private final IRealSchemaAdapter realSchemaAdapter;
	
	//constructor
	public DatabaseSchemaSession(final String databaseName, final IRealSchemaAdapter realSchemaAdapter) {
		
		database = new Database(databaseName);
		database.setRealSchemaAdapter(realSchemaAdapter);
		
		this.realSchemaAdapter = realSchemaAdapter;
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
		return realSchemaAdapter.hasChanges();
	}
	
	//method
	public void saveChanges() {
		realSchemaAdapter.saveChanges();
		close();
	}
}
