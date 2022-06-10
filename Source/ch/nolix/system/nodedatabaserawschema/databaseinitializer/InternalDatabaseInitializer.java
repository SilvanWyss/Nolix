//package declaration
package ch.nolix.system.nodedatabaserawschema.databaseinitializer;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.system.nodedatabaserawschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.system.time.base.Time;

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
			Time.ofNow().getSpecification().getRefOneAttribute()
		);
	}
}
