//package declaration
package ch.nolix.system.noderawschema.nodesearcher;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IDatabaseNodeSearcher;

//class
public final class DatabaseNodeSearcher implements IDatabaseNodeSearcher {

  //constant
  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  //constant
  private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  //method
  @Override
  public IMutableNode<?> getStoredColumnNodeByColumnIdFromDatabaseNode(
    final IMutableNode<?> databaseNode,
    final String columnId) {
    return getStoredTableNodesFromDatabaseNode(databaseNode)
      .toFromGroups(TABLE_NODE_SEARCHER::getStoredColumnNodesFromTableNode)
      .getStoredFirst(
        cn -> COLUMN_NODE_SEARCHER.getStoredIdNodeFromColumnNode(cn).getStoredSingleChildNode()
          .hasHeader(columnId));
  }

  //method
  @Override
  public IMutableNode<?> getStoredDatabasePropertiesNodeFromDatabaseNode(final IMutableNode<?> databaseNode) {
    return databaseNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.DATABASE_PROPERTIES);
  }

  //method
  @Override
  public IMutableNode<?> getStoredEntityHeadsNodeFromDatabaseNode(final IMutableNode<?> databaseNode) {
    return databaseNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.ENTITY_HEADS);
  }

  //method
  @Override
  public IMutableNode<?> getStoredTableNodeByTableIdFromDatabaseNode(
    final IMutableNode<?> databaseNode,
    final String tableId) {
    return getStoredTableNodesFromDatabaseNode(databaseNode).getStoredFirst(
      tsn -> tsn.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.ID).getStoredSingleChildNode()
        .hasHeader(tableId));
  }

  //method
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

  //method
  @Override
  public IContainer<? extends IMutableNode<?>> getStoredTableNodesFromDatabaseNode(final IMutableNode<?> databaseNode) {
    return databaseNode.getStoredChildNodesWithHeader(StructureHeaderCatalogue.TABLE);
  }

  //method
  @Override
  public int getTableNodeCount(final IMutableNode<?> databaseNode) {
    return databaseNode.getStoredChildNodes().getCount(a -> a.hasHeader(StructureHeaderCatalogue.TABLE));
  }
}
