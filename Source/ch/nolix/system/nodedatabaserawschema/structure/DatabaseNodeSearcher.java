//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//class
public final class DatabaseNodeSearcher {
	
	//static attribute
	private static final TableNodeSearcher tableNodeSearcher = new TableNodeSearcher();
	
	//static attribute
	private static final ColumnNodeSearcher columnNodeSearcher = new ColumnNodeSearcher();
	
	//method
	public IMutableNode<?> getRefColumnNodeByColumnIdFromDatabaseNode(
		final IMutableNode<?> databaseNode,
		final String columnId
	) {
		return
		getRefTableNodesFromDatabaseNode(databaseNode)
		.toFromGroups(tableNodeSearcher::getRefColumnNodesFromTableNode)
		.getRefFirst(cn -> columnNodeSearcher.getRefIdNodeFromColumnNode(cn).getRefSingleChildNode().hasHeader(columnId));
	}
	
	//method
	public IMutableNode<?> getRefDatabasePropertiesNodeFromDatabaseNode(final IMutableNode<?> databaseNode) {
		return databaseNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.DATABASE_PROPERTIES);
	}
	
	//method
	public IMutableNode<?> getRefTableNodeByTableIdFromDatabaseNode(
		final IMutableNode<?> databaseNode,
		final String tableId
	) {
		return
		getRefTableNodesFromDatabaseNode(databaseNode).getRefFirst(
			tsn -> tsn.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.ID).getRefSingleChildNode().hasHeader(tableId)
		);
	}
	
	//method
	public IMutableNode<?> getRefTableNodeByTableNameFromDatabaseNode(
		final IMutableNode<?> databaseNode,
		final String tableName
	) {
		return
		getRefTableNodesFromDatabaseNode(databaseNode).getRefFirst(
			tsn -> tsn.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.NAME).getRefSingleChildNode().hasHeader(tableName)
		);
	}
	
	//method
	public IContainer<? extends IMutableNode<?>> getRefTableNodesFromDatabaseNode(final IMutableNode<?> databaseNode) {
		return databaseNode.getRefChildNodesWithHeader(SubNodeHeaderCatalogue.TABLE);
	}
	
	//method
	public int getTableNodeCount(final IMutableNode<?> databaseNode) {
		return databaseNode.getRefChildNodes().getCount(a -> a.hasHeader(SubNodeHeaderCatalogue.TABLE));
	}
}
