package ch.nolix.core.document.node;

import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.document.node.INodeComparator;

/**
 * @author Silvan Wyss
 */
public final class NodeComparator implements INodeComparator {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean areEqual(final INode<?> node1, final INode<?> node2) {
    return //
    haveEqualHeaderConstellationLikeNode(node1, node2)
    && haveEqualChildNodesConstellationLikeNodes(node1, node2);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean haveEqualChildNodesConstellationLikeNodes(final INode<?> node1, final INode<?> node2) {
    if (node1 != null && node2 != null && node1.getChildNodeCount() == node2.getChildNodeCount()) {
      final var iterator = node2.getStoredChildNodes().iterator();

      for (final var n : node1.getStoredChildNodes()) {
        if (!n.equals(iterator.next())) {
          return false;
        }
      }

      return true;
    }

    return (node1 == null && node2 == null);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean haveEqualHeaderConstellationLikeNode(final INode<?> node1, final INode<?> node2) {
    return //
    (node1 == null && node2 == null) //NOSONAR: This implementation bases on chained conditions only.
    || (node1 != null && node2 != null && !node1.hasHeader() && !node2.hasHeader())
    || (node1 != null && node2 != null && node1.hasHeader(node2.getHeader()));
  }
}
