package ch.nolix.system.noderawschema.nodesearcher;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalog;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IColumnNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.ITableNodeSearcher;
import ch.nolix.systemapi.rawschemaapi.databasestructureapi.FixDatabasePropertyCatalogue;

public final class TableNodeSearcher implements ITableNodeSearcher {

  private static final IColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  @Override
  public int get1BasedIndexOfColumnInTableNodeByColumnName(final IMutableNode<?> tableNode, final String columnName) {

    var local1BasedColumnIndex = FixDatabasePropertyCatalogue.NUMBER_OF_ENTITY_META_FIELDS + 1;

    for (final var c : getStoredColumnNodesFromTableNode(tableNode)) {

      if (COLUMN_NODE_SEARCHER.getColumnNameFromColumnNode(c).equals(columnName)) {
        return local1BasedColumnIndex;
      }

      local1BasedColumnIndex++;
    }

    throw InvalidArgumentException.forArgumentNameAndArgument("column name", columnName);
  }

  @Override
  public IMutableNode<?> getStoredColumnNodeFromTableNodeByColumnName(
    final IMutableNode<?> tableNode,
    final String columnName) {
    return getStoredColumnNodesFromTableNode(tableNode).getStoredFirst(
      csn -> COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode(csn).getStoredSingleChildNode()
        .hasHeader(columnName));
  }

  @Override
  public IContainer<? extends IMutableNode<?>> getStoredColumnNodesFromTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredChildNodesWithHeader(NodeHeaderCatalog.COLUMN);
  }

  @Override
  public IContainer<? extends IMutableNode<?>> getStoredEntityNodesFromTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredChildNodesWithHeader(NodeHeaderCatalog.ENTITY);
  }

  @Override
  public IMutableNode<?> getStoredIdNodeFromTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.ID);
  }

  @Override
  public IMutableNode<?> getStoredNameNodeFromTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.NAME);
  }

  @Override
  public String getTableIdFromTableNode(final IMutableNode<?> tableNode) {

    final var idNode = getStoredIdNodeFromTableNode(tableNode);

    return idNode.getSingleChildNodeHeader();
  }

  @Override
  public String getTableNameFromTableNode(final IMutableNode<?> tableNode) {

    final var nameNode = getStoredNameNodeFromTableNode(tableNode);

    return nameNode.getSingleChildNodeHeader();
  }
}
