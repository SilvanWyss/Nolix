//package declaration
package ch.nolix.system.noderawobjectschema.databaseinitializer;

import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.noderawobjectschema.structure.SubNodeHeaderCatalogue;

//class
final class InternalDatabaseInitializer {
	
	//method
	public void initializeDatabase(final BaseNode databaseNode) {
		databaseNode
		.setHeader(SubNodeHeaderCatalogue.DATABASE)
		.addAttribute(createDatabasePropertiesNode());
	}
	
	//method
	private Node createDatabasePropertiesNode() {
		return Node.withHeaderAndAttribute(SubNodeHeaderCatalogue.DATABASE_PROPERTIES, createSchemaTimestampNode());
	}
	
	//method
	private Node createSchemaTimestampNode() {
		return
		Node.withHeaderAndAttribute(
			SubNodeHeaderCatalogue.SCHEMA_TIMESTAMP,
			Time.fromCurrentTime().getSpecification().getRefOneAttribute()
		);
	}
}
