/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.document.node;

/**
 * @author Silvan Wyss
 */
public interface INodeComparator {
  /**
   * @param node1
   * @param node2
   * @return true if the given node1 equals the given node2, false otherwise.
   */
  boolean areEqual(final INode<?> node1, final INode<?> node2);

  /**
   * @param node1
   * @param node2
   * @return true if the given ndoe1 and node2 have an equal child {@link INode}s
   *         constellation, false otherwise.
   */
  boolean haveEqualChildNodesConstellationLikeNodes(INode<?> node1, INode<?> node2);

  /**
   * @param node1
   * @param node2
   * @return true if the given ndoe1 and node2 have an equal header constellation,
   *         false otherwise.
   */
  boolean haveEqualHeaderConstellationLikeNode(INode<?> node1, INode<?> node2);
}
