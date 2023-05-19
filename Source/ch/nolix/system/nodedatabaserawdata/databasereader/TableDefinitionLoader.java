//package declaration
package ch.nolix.system.nodedatabaserawdata.databasereader;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

//class
public final class TableDefinitionLoader {
	
	//static attribute
	private static final TableDefinitionMapper tableDefinitionMapper = new TableDefinitionMapper();
	
	//method
	public IContainer<ITableInfo> loadTableDefinitionsFromDatabaseNode(final IMutableNode<?> databaseNode) {
		
		final var tableNodes = databaseNode.getOriChildNodesWithHeader(SubNodeHeaderCatalogue.TABLE);
		
		return tableNodes.to(tableDefinitionMapper::createTableDefinitionFromTableNode);
	}
}
