//package declaration
package ch.nolix.system.nodeintermediateschema.structure;

//own imports
import ch.nolix.common.document.node.BaseNode;

//class
public final class DatabasePropertiesNodeSearcher {
	
	//method
	public BaseNode getTimestampNodeFromDatabasePropertiesNode(final BaseNode databasePropertiesNode) {
		return databasePropertiesNode.getRefFirstAttribute(SubNodeHeaderCatalogue.TIMESTAMP);
	}
}
