//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.time.moment.Time;

//class
public final class DatabasePropertiesNodeSearcher {
	
	//method
	public Time getSchemaTimestampFromDatabasePropertiesNode(final BaseNode databasePropertiesNode) {
		
		final var schemaTimeStampNode = getRefSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);
		
		return Time.fromSpecification(schemaTimeStampNode);
	}
	
	//method
	public BaseNode getRefSchemaTimestampNodeFromDatabasePropertiesNode(final BaseNode databasePropertiesNode) {
		return databasePropertiesNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.SCHEMA_TIMESTAMP);
	}
}
