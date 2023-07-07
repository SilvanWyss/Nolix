//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//class
public final class DatabaseNodeSearcher {
	
	//constant
	private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();
	
	//constant
	private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();
	
	//method
	public IMutableNode<?> getOriColumnNodeByColumnIdFromDatabaseNode(
		final IMutableNode<?> databaseNode,
		final String columnId
	) {
		return
		getOriTableNodesFromDatabaseNode(databaseNode)
		.toFromGroups(TABLE_NODE_SEARCHER::getOriColumnNodesFromTableNode)
		.getOriFirst(cn -> COLUMN_NODE_SEARCHER.getOriIdNodeFromColumnNode(cn).getOriSingleChildNode().hasHeader(columnId));
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
