//package declaration
package ch.nolix.system.nodeintermediatedata.datareader;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.system.nodeintermediatedata.tabledefinition.TableDefinition;
import ch.nolix.system.noderawobjectschema.structure.SubNodeHeaderCatalogue;

//class
public final class TableDefinitionLoader {
	
	//static attribute
	private static final TableDefinitionMapper tableDefinitionMapper = new TableDefinitionMapper();
	
	//method
	public LinkedList<TableDefinition> loadTableDefinitionsFromDatabaseNode(final BaseNode databaseNode) {
		return 
		databaseNode
		.getRefAttributes(SubNodeHeaderCatalogue.TABLE)
		.to(tableDefinitionMapper::createTableDefinitionFromTableNode);
	}
}
