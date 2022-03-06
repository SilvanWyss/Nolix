//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.element.time.base.Time;

//class
public final class DatabasePropertiesNodeSearcher {
	
	//method
	public Time getSchemaTimestampFromDatabasePropertiesNode(final BaseNode databasePropertiesNode) {
		
		final var schemaTimeStampNode = getRefSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);
		
		return Time.fromSpecification(schemaTimeStampNode);
	}
	
	//method
	public BaseNode getRefSchemaTimestampNodeFromDatabasePropertiesNode(final BaseNode databasePropertiesNode) {
		return databasePropertiesNode.getRefFirstAttribute(SubNodeHeaderCatalogue.SCHEMA_TIMESTAMP);
	}
}
