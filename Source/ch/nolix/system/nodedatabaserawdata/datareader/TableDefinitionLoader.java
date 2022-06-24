//package declaration
package ch.nolix.system.nodedatabaserawdata.datareader;

import ch.nolix.core.document.node.BaseNode;
import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.system.nodedatabaserawschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

//class
public final class TableDefinitionLoader {
	
	//static attribute
	private static final TableDefinitionMapper tableDefinitionMapper = new TableDefinitionMapper();
	
	//method
	public IContainer<ITableInfo> loadTableDefinitionsFromDatabaseNode(final BaseNode databaseNode) {
		return 
		databaseNode
		.getRefAttributes(SubNodeHeaderCatalogue.TABLE)
		.to(tableDefinitionMapper::createTableDefinitionFromTableNode);
	}
}
