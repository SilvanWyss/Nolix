//package declaration
package ch.nolix.system.nodeintermediatedata.tabledefinition;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.system.nodeintermediateschema.structure.SubNodeHeaderCatalogue;

//class
public final class TableDefinitionLoader {
	
	//static attribute
	private static final TableDefinitionMapper tableDefinitionMapper = new TableDefinitionMapper();
	
	//method
	public LinkedList<TableDefinition> loadTableDefinitionsFromDatabase(final BaseNode database) {
		return 
		database
		.getRefAttributes(SubNodeHeaderCatalogue.TABLE)
		.to(tableDefinitionMapper::createTableDefinitionFromTableNode);
	}
}
