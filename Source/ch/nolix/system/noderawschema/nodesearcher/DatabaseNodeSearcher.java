package ch.nolix.system.noderawschema.nodesearcher;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalog;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IColumnNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IDatabaseNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IDatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.ITableNodeSearcher;

public final class DatabaseNodeSearcher implements IDatabaseNodeSearcher {

  private static final IDatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  private static final ITableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final IColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  @Override
  public String getNameFromNodeDatabase(IMutableNode<?> nodeDatabase) {

    final var databasePropertiesNode = getStoredDatabasePropertiesNodeFromNodeDatabase(nodeDatabase);

    return DATABASE_PROPERTIES_NODE_SEARCHER.getNameFromDatabasePropertiesNode(databasePropertiesNode);
  }

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
    return nodeDatabase.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.ENTITY_INDEXES);
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
