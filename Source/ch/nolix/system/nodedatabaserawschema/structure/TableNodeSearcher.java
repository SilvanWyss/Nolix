//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//class
public final class TableNodeSearcher {

  // constant
  private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  // method
  public IMutableNode<?> getStoredColumnNodeFromTableNodeByColumnName(
      final IMutableNode<?> tableNode,
      final String columnName) {
    return getStoredColumnNodesFromTableNode(tableNode).getStoredFirst(
        csn -> COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode(csn).getStoredSingleChildNode()
            .hasHeader(columnName));
  }

  // method
  public IContainer<? extends IMutableNode<?>> getStoredColumnNodesFromTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredChildNodesWithHeader(SubNodeHeaderCatalogue.COLUMN);
  }

  // method
  public IMutableNode<?> getStoredIdNodeFromTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredFirstChildNodeWithHeader(SubNodeHeaderCatalogue.ID);
  }

  // method
  public IMutableNode<?> getStoredNameNodeFromTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredFirstChildNodeWithHeader(SubNodeHeaderCatalogue.NAME);
  }

  // method
  public String getTableIdFromTableNode(final IMutableNode<?> tableNode) {
    return getStoredIdNodeFromTableNode(tableNode).getSingleChildNodeHeader();
  }

  // method
  public String getTableNameFromTableNode(final IMutableNode<?> tableNode) {
    return getStoredNameNodeFromTableNode(tableNode).getSingleChildNodeHeader();
  }
}
