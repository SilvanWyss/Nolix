//package declaration
package ch.nolix.system.noderawobjectschema.structure;

import ch.nolix.core.document.node.BaseNode;

//class
public final class DatabasePropertiesNodeSearcher {
	
	//method
	public BaseNode getSchemaTimestampNodeFromDatabasePropertiesNode(final BaseNode databasePropertiesNode) {
		return databasePropertiesNode.getRefFirstAttribute(SubNodeHeaderCatalogue.SCHEMA_TIMESTAMP);
	}
}
