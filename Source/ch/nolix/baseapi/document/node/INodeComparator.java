/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.node;

/**
 * @author Silvan Wyss
 */
public interface INodeComparator {
  /**
   * @param node1
   * @param node2
   * @return true if the given node1 equals the given node2, false otherwise
   */
  boolean areEqual(final Node<?> node1, final Node<?> node2);

  /**
   * @param node1
   * @param node2
   * @return true if the given ndoe1 and node2 have an equal child {@link Node}s
   *         constellation, false otherwise
   */
  boolean haveEqualChildNodesConstellationLikeNodes(Node<?> node1, Node<?> node2);

  /**
   * @param node1
   * @param node2
   * @return true if the given ndoe1 and node2 have an equal header constellation,
   *         false otherwise
   */
  boolean haveEqualHeaderConstellationLikeNode(Node<?> node1, Node<?> node2);
}
