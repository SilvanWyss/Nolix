package ch.nolix.core.document.chainednode;

import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;

public final class ChainedNodeComparator {

  public static boolean areEqual(final IChainedNode chainedNode1, final IChainedNode chainedNode2) {

    if (chainedNode1 == null) {
      return (chainedNode2 == null);
    }

    return //
    chainedNode2 != null
    && areEqualWhenNotNull(chainedNode1, chainedNode2);
  }

  private static boolean areEqualWhenNotNull(final IChainedNode chainedNode1, final IChainedNode chainedNode2) {
    return //
    canEqualBecauseOfHeaderWhenNotNull(chainedNode1, chainedNode2)
    && canEqualBecauseOfChildNodesWhenNotNull(chainedNode1, chainedNode2);
  }

  private static boolean canEqualBecauseOfHeaderWhenNotNull(
    final IChainedNode chainedNode1,
    final IChainedNode chainedNode2) {

    if (!chainedNode1.hasHeader()) {
      return !chainedNode2.hasHeader();
    }

    return chainedNode2.hasHeader(chainedNode1.getHeader());
  }

  private static boolean canEqualBecauseOfChildNodesWhenNotNull(
    final IChainedNode chainedNode1,
    final IChainedNode chainedNode2) {
    return chainedNode1.getChildNodes().containsExactlyEqualingInSameOrder(chainedNode2.getChildNodes());
  }

  private ChainedNodeComparator() {
  }
}
