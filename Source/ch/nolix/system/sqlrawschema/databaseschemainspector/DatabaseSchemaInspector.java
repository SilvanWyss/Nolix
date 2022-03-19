//package declaration
package ch.nolix.system.sqlrawschema.databaseschemainspector;

//own imports
import ch.nolix.system.sqlrawschema.structure.SystemDataTable;
import ch.nolix.systemapi.objectschemaapi.schemaapi.DatabaseSchemaState;
import ch.nolix.systemapi.sqlbasicschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class DatabaseSchemaInspector {
	
	//method
	public DatabaseSchemaState getDatabaseSchemaState(final ISchemaAdapter schemaAdapter) {
		
		if (schemaAdapter.tableExists(SystemDataTable.DATABASE_PROPERTY.getFullName())) {
			return DatabaseSchemaState.INITIALIZED;
		}
		
		if (schemaAdapter.loadFlatTables().isEmpty()) {
			return DatabaseSchemaState.UNINITIALIZED;
		}
		
		return DatabaseSchemaState.INVALID;
	}
}
