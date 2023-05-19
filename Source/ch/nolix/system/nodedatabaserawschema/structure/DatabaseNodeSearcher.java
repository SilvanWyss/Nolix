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
	public IMutableNode<?> getOriColumnNodeByColumnIdFromDatabaseNode(
		final IMutableNode<?> databaseNode,
		final String columnId
	) {
		return
		getOriTableNodesFromDatabaseNode(databaseNode)
		.toFromGroups(tableNodeSearcher::getOriColumnNodesFromTableNode)
		.getOriFirst(cn -> columnNodeSearcher.getOriIdNodeFromColumnNode(cn).getOriSingleChildNode().hasHeader(columnId));
	}
	
	//method
	public IMutableNode<?> getOriDatabasePropertiesNodeFromDatabaseNode(final IMutableNode<?> databaseNode) {
		return databaseNode.getOriFirstChildNodeWithHeader(SubNodeHeaderCatalogue.DATABASE_PROPERTIES);
	}
	
	//method
	public IMutableNode<?> getOriTableNodeByTableIdFromDatabaseNode(
		final IMutableNode<?> databaseNode,
		final String tableId
	) {
		return
		getOriTableNodesFromDatabaseNode(databaseNode).getOriFirst(
			tsn -> tsn.getOriFirstChildNodeWithHeader(SubNodeHeaderCatalogue.ID).getOriSingleChildNode().hasHeader(tableId)
		);
	}
	
	//method
	public IMutableNode<?> getOriTableNodeByTableNameFromDatabaseNode(
		final IMutableNode<?> databaseNode,
		final String tableName
	) {
		return
		getOriTableNodesFromDatabaseNode(databaseNode).getOriFirst(
			tsn -> tsn.getOriFirstChildNodeWithHeader(SubNodeHeaderCatalogue.NAME).getOriSingleChildNode().hasHeader(tableName)
		);
	}
	
	//method
	public IContainer<? extends IMutableNode<?>> getOriTableNodesFromDatabaseNode(final IMutableNode<?> databaseNode) {
		return databaseNode.getOriChildNodesWithHeader(SubNodeHeaderCatalogue.TABLE);
	}
	
	//method
	public int getTableNodeCount(final IMutableNode<?> databaseNode) {
		return databaseNode.getOriChildNodes().getCount(a -> a.hasHeader(SubNodeHeaderCatalogue.TABLE));
	}
}
