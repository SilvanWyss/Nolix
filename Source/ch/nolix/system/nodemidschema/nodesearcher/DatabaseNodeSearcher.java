package ch.nolix.system.nodemidschema.nodesearcher;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IColumnNodeSearcher;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IDatabaseNodeSearcher;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IDatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.nodemidschema.nodesearcher.ITableNodeSearcher;

/**
 * @author Silvan Wyss
 */
public final class DatabaseNodeSearcher implements IDatabaseNodeSearcher {
  private static final IDatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  private static final ITableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final IColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  @Override
  public String getDatabaseNameFromNodeDatabase(IMutableNode<?> nodeDatabase) {
    final var databasePropertiesNode = getStoredDatabasePropertiesNodeFromNodeDatabase(nodeDatabase);

    return DATABASE_PROPERTIES_NODE_SEARCHER.getDatabaseNameFromDatabasePropertiesNode(databasePropertiesNode);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredColumnNodeByColumnIdFromNodeDatabase(
    final IMutableNode<?> nodeDatabase,
    final String columnId) {
    return getStoredTableNodesFromNodeDatabase(nodeDatabase)
      .toMultiples(TABLE_NODE_SEARCHER::getStoredColumnNodesFromTableNode)
      .getStoredFirst(
        cn -> COLUMN_NODE_SEARCHER.getStoredIdNodeFromColumnNode(cn).getStoredSingleChildNode()
          .hasHeader(columnId));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredDatabasePropertiesNodeFromNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return nodeDatabase.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.DATABASE_PROPERTIES);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredEntityIndexesNodeFromNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return nodeDatabase.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.ENTITY_INDEXES);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredTableNodeByTableIdFromNodeDatabase(
    final IMutableNode<?> nodeDatabase,
    final String tableId) {
    return getStoredTableNodesFromNodeDatabase(nodeDatabase).getStoredFirst(
      tsn -> tsn.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.ID).getStoredSingleChildNode()
        .hasHeader(tableId));
  }

  /**
   * {@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<? extends IMutableNode<?>> getStoredTableNodesFromNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return nodeDatabase.getStoredChildNodesWithHeader(NodeHeaderCatalog.TABLE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getTableNodeCount(final IMutableNode<?> nodeDatabase) {
    return nodeDatabase.getStoredChildNodes().getCount(a -> a.hasHeader(NodeHeaderCatalog.TABLE));
  }
}
