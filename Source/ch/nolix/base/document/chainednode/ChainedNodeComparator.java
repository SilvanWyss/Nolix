/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.document.chainednode;

import ch.nolix.baseapi.document.chainednode.ChainedNode;

/**
 * @author Silvan Wyss
 */
public final class ChainedNodeComparator {
  private ChainedNodeComparator() {
  }

  public static boolean areEqual(final ChainedNode chainedNode1, final ChainedNode chainedNode2) {
    if (chainedNode1 == null) {
      return (chainedNode2 == null);
    }

    return //
    chainedNode2 != null
    && areEqualWhenNotNull(chainedNode1, chainedNode2);
  }

  private static boolean areEqualWhenNotNull(final ChainedNode chainedNode1, final ChainedNode chainedNode2) {
    return //
    canEqualBecauseOfHeaderWhenNotNull(chainedNode1, chainedNode2)
    && canEqualBecauseOfChildNodesWhenNotNull(chainedNode1, chainedNode2);
  }

  private static boolean canEqualBecauseOfHeaderWhenNotNull(
    final ChainedNode chainedNode1,
    final ChainedNode chainedNode2) {
    if (!chainedNode1.hasHeader()) {
      return !chainedNode2.hasHeader();
    }

    return chainedNode2.hasHeader(chainedNode1.getHeader());
  }

  private static boolean canEqualBecauseOfChildNodesWhenNotNull(
    final ChainedNode chainedNode1,
    final ChainedNode chainedNode2) {
    return chainedNode1.getChildNodes().containsExactlyAllEqualInSameOrder(chainedNode2.getChildNodes());
  }
}
