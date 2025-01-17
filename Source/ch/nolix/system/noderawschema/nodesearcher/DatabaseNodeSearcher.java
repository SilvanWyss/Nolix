package ch.nolix.system.noderawschema.nodesearcher;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalog;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalog;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IDatabaseNodeSearcher;

public final class DatabaseNodeSearcher implements IDatabaseNodeSearcher {

  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  @Override
  public IMutableNode<?> getStoredColumnNodeByColumnIdFromNodeDatabase(
    final IMutableNode<?> nodeDatabase,
    final String columnId) {
    return getStoredTableNodesFromNodeDatabase(nodeDatabase)
      .toMultiple(TABLE_NODE_SEARCHER::getStoredColumnNodesFromTableNode)
      .getStoredFirst(
        cn -> COLUMN_NODE_SEARCHER.getStoredIdNodeFromColumnNode(cn).getStoredSingleChildNode()
          .hasHeader(columnId));
  }

  @Override
  public IMutableNode<?> getStoredDatabasePropertiesNodeFromNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return nodeDatabase.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.DATABASE_PROPERTIES);
  }

  @Override
  public IMutableNode<?> getStoredEntityIndexesNodeFromNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return nodeDatabase.getStoredFirstChildNodeWithHeader(StructureHeaderCatalog.ENTITY_HEADS);
  }

  @Override
  public IMutableNode<?> getStoredTableNodeByTableIdFromNodeDatabase(
    final IMutableNode<?> nodeDatabase,
    final String tableId) {
    return getStoredTableNodesFromNodeDatabase(nodeDatabase).getStoredFirst(
      tsn -> tsn.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.ID).getStoredSingleChildNode()
        .hasHeader(tableId));
  }

  @Override
  public IMutableNode<?> getStoredTableNodeByTableNameFromNodeDatabase(
    final IMutableNode<?> nodeDatabase,
    final String tableName) {
    return getStoredTableNodesFromNodeDatabase(nodeDatabase).getStoredFirst(
      tsn -> tsn
        .getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.NAME)
        .getStoredSingleChildNode()
        .hasHeader(tableName));
  }

  @Override
  public IContainer<? extends IMutableNode<?>> getStoredTableNodesFromNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return nodeDatabase.getStoredChildNodesWithHeader(NodeHeaderCatalog.TABLE);
  }

  @Override
  public int getTableNodeCount(final IMutableNode<?> nodeDatabase) {
    return nodeDatabase.getStoredChildNodes().getCount(a -> a.hasHeader(NodeHeaderCatalog.TABLE));
  }
}
