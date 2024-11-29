package ch.nolix.system.noderawschema.nodesearcher;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IDatabaseNodeSearcher;

public final class DatabaseNodeSearcher implements IDatabaseNodeSearcher {

  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  @Override
  public IMutableNode<?> getStoredColumnNodeByColumnIdFromDatabaseNode(
    final IMutableNode<?> databaseNode,
    final String columnId) {
    return getStoredTableNodesFromDatabaseNode(databaseNode)
      .toMultiple(TABLE_NODE_SEARCHER::getStoredColumnNodesFromTableNode)
      .getStoredFirst(
        cn -> COLUMN_NODE_SEARCHER.getStoredIdNodeFromColumnNode(cn).getStoredSingleChildNode()
          .hasHeader(columnId));
  }

  @Override
  public IMutableNode<?> getStoredDatabasePropertiesNodeFromDatabaseNode(final IMutableNode<?> databaseNode) {
    return databaseNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.DATABASE_PROPERTIES);
  }

  @Override
  public IMutableNode<?> getStoredEntityHeadsNodeFromDatabaseNode(final IMutableNode<?> databaseNode) {
    return databaseNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.ENTITY_HEADS);
  }

  @Override
  public IMutableNode<?> getStoredTableNodeByTableIdFromDatabaseNode(
    final IMutableNode<?> databaseNode,
    final String tableId) {
    return getStoredTableNodesFromDatabaseNode(databaseNode).getStoredFirst(
      tsn -> tsn.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.ID).getStoredSingleChildNode()
        .hasHeader(tableId));
  }

  @Override
  public IMutableNode<?> getStoredTableNodeByTableNameFromDatabaseNode(
    final IMutableNode<?> databaseNode,
    final String tableName) {
    return getStoredTableNodesFromDatabaseNode(databaseNode).getStoredFirst(
      tsn -> tsn
        .getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.NAME)
        .getStoredSingleChildNode()
        .hasHeader(tableName));
  }

  @Override
  public IContainer<? extends IMutableNode<?>> getStoredTableNodesFromDatabaseNode(final IMutableNode<?> databaseNode) {
    return databaseNode.getStoredChildNodesWithHeader(StructureHeaderCatalogue.TABLE);
  }

  @Override
  public int getTableNodeCount(final IMutableNode<?> databaseNode) {
    return databaseNode.getStoredChildNodes().getCount(a -> a.hasHeader(StructureHeaderCatalogue.TABLE));
  }
}
