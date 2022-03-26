//package declaration
package ch.nolix.system.nodedatabaserawdata.datareader;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.nodedatabaserawschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

//class
public final class TableDefinitionLoader {
	
	//static attribute
	private static final TableDefinitionMapper tableDefinitionMapper = new TableDefinitionMapper();
	
	//method
	public LinkedList<ITableInfo> loadTableDefinitionsFromDatabaseNode(final BaseNode databaseNode) {
		return 
		databaseNode
		.getRefAttributes(SubNodeHeaderCatalogue.TABLE)
		.to(tableDefinitionMapper::createTableDefinitionFromTableNode);
	}
}
