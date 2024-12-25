package ch.nolix.system.noderawschema.nodesearcher;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IColumnNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IEntityNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.ITableNodeSearcher;

public final class TableNodeSearcher implements ITableNodeSearcher {

  private static final IColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  private static final IEntityNodeSearcher ENTITY_NODE_SEARCHER = new EntityNodeSearcher();

  @Override
  public boolean columnOfTableNodeIsEmptyByColumnName(final IMutableNode<?> tableNode, final String columnName) {

    final var local1BasedColumnIndex = get1BasedIndexOfColumnInTableNodeByColumnName(tableNode, columnName);
    final var entityNodes = getStoredEntityNodesFromTableNode(tableNode);

    for (final var e : entityNodes) {
      if (ENTITY_NODE_SEARCHER.fieldNodeOfEntityNodeAt1BasedColumnIndexIsEmpty(e, local1BasedColumnIndex)) {
        return false;
      }
    }

    return true;
  }

  @Override
  public int get1BasedIndexOfColumnInTableNodeByColumnName(final IMutableNode<?> tableNode, final String columnName) {

    var local1BasedColumnIndex = 3;

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
    return tableNode.getStoredChildNodesWithHeader(NodeHeaderCatalogue.COLUMN);
  }

  @Override
  public IContainer<? extends IMutableNode<?>> getStoredEntityNodesFromTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredChildNodesWithHeader(NodeHeaderCatalogue.ENTITY);
  }

  @Override
  public IMutableNode<?> getStoredIdNodeFromTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalogue.ID);
  }

  @Override
  public IMutableNode<?> getStoredNameNodeFromTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalogue.NAME);
  }

  @Override
  public String getTableIdFromTableNode(final IMutableNode<?> tableNode) {
    return getStoredIdNodeFromTableNode(tableNode).getSingleChildNodeHeader();
  }

  @Override
  public String getTableNameFromTableNode(final IMutableNode<?> tableNode) {
    return getStoredNameNodeFromTableNode(tableNode).getSingleChildNodeHeader();
  }
}
