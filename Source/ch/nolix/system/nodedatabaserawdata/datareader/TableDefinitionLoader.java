//package declaration
package ch.nolix.system.nodedatabaserawdata.datareader;

import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

//class
public final class TableDefinitionLoader {
	
	//static attribute
	private static final TableDefinitionMapper tableDefinitionMapper = new TableDefinitionMapper();
	
	//method
	public IContainer<ITableInfo> loadTableDefinitionsFromDatabaseNode(final IMutableNode<?> databaseNode) {
		return 
		databaseNode
		.getRefChildNodesWithHeader(SubNodeHeaderCatalogue.TABLE)
		.to(tableDefinitionMapper::createTableDefinitionFromTableNode);
	}
}
