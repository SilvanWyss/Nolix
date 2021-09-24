//package declaration
package ch.nolix.system.sqlintermediateschema.databaseschemainspector;

//own imports
import ch.nolix.system.sqlintermediateschema.structure.SystemDataTable;
import ch.nolix.techapi.objectschemaapi.schemaapi.DatabaseSchemaState;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class DatabaseSchemaInspector {
	
	//method
	public DatabaseSchemaState getDatabaseSchemaState(final ISchemaAdapter schemaAdapter) {
		
		if (schemaAdapter.tableExists(SystemDataTable.DATABASE_PROPERTY.getNameWithPrefix())) {
			return DatabaseSchemaState.INITIALIZED;
		}
		
		if (schemaAdapter.loadFlatTables().isEmpty()) {
			return DatabaseSchemaState.UNINITIALIZED;
		}
		
		return DatabaseSchemaState.INVALID;
	}
}
