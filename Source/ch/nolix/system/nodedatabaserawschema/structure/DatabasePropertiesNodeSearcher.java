//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.time.moment.Time;

//class
public final class DatabasePropertiesNodeSearcher {
	
	//method
	public Time getSchemaTimestampFromDatabasePropertiesNode(final IMutableNode<?> databasePropertiesNode) {
		
		final var schemaTimeStampNode = getOriSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);
		
		return Time.fromSpecification(schemaTimeStampNode);
	}
	
	//method
	public IMutableNode<?> getOriSchemaTimestampNodeFromDatabasePropertiesNode(
		final IMutableNode<?> databasePropertiesNode
	) {
		return databasePropertiesNode.getOriFirstChildNodeWithHeader(SubNodeHeaderCatalogue.SCHEMA_TIMESTAMP);
	}
}
