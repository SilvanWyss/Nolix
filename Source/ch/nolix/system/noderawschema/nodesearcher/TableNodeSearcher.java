//package declaration
package ch.nolix.system.noderawschema.nodesearcher;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.ITableNodeSearcher;

//class
public final class TableNodeSearcher implements ITableNodeSearcher {

  //constant
  private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  //method
  @Override
  public IMutableNode<?> getStoredColumnNodeFromTableNodeByColumnName(
    final IMutableNode<?> tableNode,
    final String columnName) {
    return getStoredColumnNodesFromTableNode(tableNode).getStoredFirst(
      csn -> COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode(csn).getStoredSingleChildNode()
        .hasHeader(columnName));
  }

  //method
  @Override
  public IContainer<? extends IMutableNode<?>> getStoredColumnNodesFromTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredChildNodesWithHeader(StructureHeaderCatalogue.COLUMN);
  }

  //method
  @Override
  public IMutableNode<?> getStoredIdNodeFromTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.ID);
  }

  //method
  @Override
  public IMutableNode<?> getStoredNameNodeFromTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.NAME);
  }

  //method
  @Override
  public String getTableIdFromTableNode(final IMutableNode<?> tableNode) {
    return getStoredIdNodeFromTableNode(tableNode).getSingleChildNodeHeader();
  }

  //method
  @Override
  public String getTableNameFromTableNode(final IMutableNode<?> tableNode) {
    return getStoredNameNodeFromTableNode(tableNode).getSingleChildNodeHeader();
  }
}
