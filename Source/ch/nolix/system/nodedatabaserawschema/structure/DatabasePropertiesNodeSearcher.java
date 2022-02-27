//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

//own imports
import ch.nolix.core.document.node.BaseNode;

//class
public final class DatabasePropertiesNodeSearcher {
	
	//method
	public String getSchemaTimestampFromDatabasePropertiesNode(final BaseNode databasePropertiesNode) {
		
		final var schemaTimeStampNode = getRefSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);
		
		return schemaTimeStampNode.getOneAttributeHeader();
	}
	
	//method
	public BaseNode getRefSchemaTimestampNodeFromDatabasePropertiesNode(final BaseNode databasePropertiesNode) {
		return databasePropertiesNode.getRefFirstAttribute(SubNodeHeaderCatalogue.SCHEMA_TIMESTAMP);
	}
}
