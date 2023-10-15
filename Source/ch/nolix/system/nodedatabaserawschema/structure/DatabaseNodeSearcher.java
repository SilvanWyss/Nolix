//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//class
public final class DatabaseNodeSearcher {

  // constant
  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  // constant
  private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  // method
  public IMutableNode<?> getStoredColumnNodeByColumnIdFromDatabaseNode(
      final IMutableNode<?> databaseNode,
      final String columnId) {
    return getStoredTableNodesFromDatabaseNode(databaseNode)
        .toFromGroups(TABLE_NODE_SEARCHER::getStoredColumnNodesFromTableNode)
        .getStoredFirst(
            cn -> COLUMN_NODE_SEARCHER.getStoredIdNodeFromColumnNode(cn).getStoredSingleChildNode()
                .hasHeader(columnId));
  }

  // method
  public IMutableNode<?> getStoredDatabasePropertiesNodeFromDatabaseNode(final IMutableNode<?> databaseNode) {
    return databaseNode.getStoredFirstChildNodeWithHeader(SubNodeHeaderCatalogue.DATABASE_PROPERTIES);
  }

  // method
  public IMutableNode<?> getStoredTableNodeByTableIdFromDatabaseNode(
      final IMutableNode<?> databaseNode,
      final String tableId) {
    return getStoredTableNodesFromDatabaseNode(databaseNode).getStoredFirst(
        tsn -> tsn.getStoredFirstChildNodeWithHeader(SubNodeHeaderCatalogue.ID).getStoredSingleChildNode()
            .hasHeader(tableId));
  }

  // method
  public IMutableNode<?> getStoredTableNodeByTableNameFromDatabaseNode(
      final IMutableNode<?> databaseNode,
      final String tableName) {
    return getStoredTableNodesFromDatabaseNode(databaseNode).getStoredFirst(
        tsn -> tsn
            .getStoredFirstChildNodeWithHeader(SubNodeHeaderCatalogue.NAME)
            .getStoredSingleChildNode()
            .hasHeader(tableName));
  }

  // method
  public IContainer<? extends IMutableNode<?>> getStoredTableNodesFromDatabaseNode(final IMutableNode<?> databaseNode) {
    return databaseNode.getStoredChildNodesWithHeader(SubNodeHeaderCatalogue.TABLE);
  }

  // method
  public int getTableNodeCount(final IMutableNode<?> databaseNode) {
    return databaseNode.getStoredChildNodes().getCount(a -> a.hasHeader(SubNodeHeaderCatalogue.TABLE));
  }
}
