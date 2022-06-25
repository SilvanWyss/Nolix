//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

import ch.nolix.core.document.node.BaseNode;
import ch.nolix.coreapi.containerapi.IContainer;

//class
public final class DatabaseNodeSearcher {
	
	//static attribute
	private static final TableNodeSearcher tableNodeSearcher = new TableNodeSearcher();
	
	//static attribute
	private static final ColumnNodeSearcher columnNodeSearcher = new ColumnNodeSearcher();
	
	//method
	public BaseNode getRefColumnNodeByColumnIdFromDatabaseNode(final BaseNode databaseNode, final String columnId) {
		return
		getRefTableNodesFromDatabaseNode(databaseNode)
		.toFromMany(tableNodeSearcher::getRefColumnNodesFromTableNode)
		.getRefFirst(cn -> columnNodeSearcher.getRefIdNodeFromColumnNode(cn).getRefSingleChildNode().hasHeader(columnId));
	}
	
	//method
	public BaseNode getRefDatabasePropertiesNodeFromDatabaseNode(final BaseNode databaseNode) {
		return databaseNode.getRefFirstAttribute(SubNodeHeaderCatalogue.DATABASE_PROPERTIES);
	}
	
	//method
	public BaseNode getRefTableNodeByTableIdFromDatabaseNode(final BaseNode databaseNode, final String tableId) {
		return
		getRefTableNodesFromDatabaseNode(databaseNode).getRefFirst(
			tsn -> tsn.getRefFirstAttribute(SubNodeHeaderCatalogue.ID).getRefSingleChildNode().hasHeader(tableId)
		);
	}
	
	//method
	public BaseNode getRefTableNodeByTableNameFromDatabaseNode(final BaseNode databaseNode, final String tableName) {
		return
		getRefTableNodesFromDatabaseNode(databaseNode).getRefFirst(
			tsn -> tsn.getRefFirstAttribute(SubNodeHeaderCatalogue.NAME).getRefSingleChildNode().hasHeader(tableName)
		);
	}
	
	//method
	public IContainer<BaseNode> getRefTableNodesFromDatabaseNode(final BaseNode databaseNode) {
		return databaseNode.getRefAttributes(SubNodeHeaderCatalogue.TABLE);
	}
	
	//method
	public int getTableNodeCount(final BaseNode databaseNode) {
		return databaseNode.getRefChildNodes().getCount(a -> a.hasHeader(SubNodeHeaderCatalogue.TABLE));
	}
}
