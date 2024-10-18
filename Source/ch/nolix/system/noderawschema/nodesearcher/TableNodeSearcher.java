package ch.nolix.system.noderawschema.nodesearcher;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.ITableNodeSearcher;

public final class TableNodeSearcher implements ITableNodeSearcher {

  private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

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
    return tableNode.getStoredChildNodesWithHeader(StructureHeaderCatalogue.COLUMN);
  }

  @Override
  public IMutableNode<?> getStoredIdNodeFromTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.ID);
  }

  @Override
  public IMutableNode<?> getStoredNameNodeFromTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.NAME);
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
