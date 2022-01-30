//package declaration
package ch.nolix.system.noderawobjectschema.databaseschemainspector;

import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.noderawobjectschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.systemapi.objectschemaapi.schemaapi.DatabaseSchemaState;

//class
public final class DatabaseSchemaInspector {
	
	//method
	public DatabaseSchemaState getDatabaseSchemaState(final BaseNode databaseNode) {
				
		if (databaseIsInitialized(databaseNode)) {
			return DatabaseSchemaState.INITIALIZED;
		}
		
		if (databaseIsUnitialized(databaseNode)) {
			return DatabaseSchemaState.UNINITIALIZED;
		}
		
		return DatabaseSchemaState.INVALID;
	}
	
	//method
	private boolean databaseIsInitialized(final BaseNode databaseNode) {
		return 
		databaseNode.hasHeader(SubNodeHeaderCatalogue.DATABASE)
		&& databaseNode.containsAttributeWithHeader(SubNodeHeaderCatalogue.DATABASE_PROPERTIES);
	}
	
	//method
	private boolean databaseIsUnitialized(final BaseNode databaseNode) {
		return (!databaseNode.hasHeader() && !databaseNode.containsAttributes());
	}
}
