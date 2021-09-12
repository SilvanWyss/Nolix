//package declaration
package ch.nolix.system.sqlintermediateschema.databaseschemainspector;

//own imports
import ch.nolix.system.sqlintermediateschema.structure.SystemDataTable;
import ch.nolix.techapi.databaseschemaapi.schemaapi.DatabaseSchemaState;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaReader;

//class
public final class DatabaseSchemaInspector {
	
	//method
	public DatabaseSchemaState getDatabaseSchemaState(final ISchemaAdapter schemaAdapter) {
		return getDatabaseSchemaState(schemaAdapter.getRefSchemaReader());
	}
	
	//method
	private DatabaseSchemaState getDatabaseSchemaState(final ISchemaReader schemaReader) {
		
		if (schemaReader.tableExists(SystemDataTable.DATABASE_PROPERTY.getNameWithPrefix())) {
			return DatabaseSchemaState.INITIALIZED;
		}
		
		if (schemaReader.loadFlatTables().isEmpty()) {
			return DatabaseSchemaState.UNINITIALIZED;
		}
		
		return DatabaseSchemaState.INVALID;
	}
}
