/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.chainednode;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalHeaderHolder;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.generalstate.staterequest.BlanknessRequestable;

/**
 * A {@link ChainedNode} has the following attributes. -0 or 1 header -an
 * arbitrary number of child {@link ChainedNode}s -0 or 1 next
 * {@link ChainedNode}
 * 
 * A {@link ChainedNode} that does not have a header, does not contain
 * attributes and does not have a next {@link ChainedNode} is blank.
 * 
 * A {@link ChainedNode} is not mutable.
 * 
 * @author Silvan Wyss
 */
public interface ChainedNode extends BlanknessRequestable, OptionalHeaderHolder {
  /**
   * @return true if the current {@link ChainedNode} contains child nodes, false
   *         otherwise
   */
  boolean containsChildNodes();

  /**
   * @param oneBasedIndex
   * @return the child node of the current {@link ChainedNode} at the given
   *         oneBasedIndex
   * @throws RuntimeException if the current {@link ChainedNode} does not contain
   *                          a child node at the given oneBasedIndex.
   */
  ChainedNode getChildNodeAtOneBasedIndex(int oneBasedIndex);

  /**
   * @return the number of child nodes of the current {@link ChainedNode}.
   */
  int getChildNodeCount();

  /**
   * @return the child nodes of the current {@link ChainedNode}.
   */
  ExtendedIterable<? extends ChainedNode> getChildNodes();

  /**
   * @param header
   * @return the first child node with the given header from the current
   *         {@link ChainedNode}
   * @throws RuntimeException if the current {@link ChainedNode} does not contain
   *                          a child node with the given header.
   */
  ChainedNode getFirstChildNodeWithHeader(String header);

  /**
   * @return the next node of the current {@link ChainedNode}
   * @throws RuntimeException if the current {@link ChainedNode} does not have a
   *                          next node.
   */
  ChainedNode getNextNode();

  /**
   * @return the single child node of the current {@link ChainedNode}
   * @throws RuntimeException if the current {@link ChainedNode} does not contain
   *                          a child node or contains multiple child nodes.
   */
  ChainedNode getSingleChildNode();

  /**
   * 
   * @return the header of the single child node of the current
   *         {@link ChainedNode}
   * @throws RuntimeException if the current {@link ChainedNode} does not contain
   *                          a child node or contains multiple child nodes
   * @throws RuntimeException if the single child node of the current
   *                          {@link ChainedNode} does not have a header.
   */
  String getSingleChildNodeHeader();

  /**
   * @return a double representation of the single child node of the current
   *         {@link ChainedNode}
   * @throws RuntimeException if the current {@link ChainedNode} does not contain
   *                          a child nodes or contains multiple child nodes
   * @throws RuntimeException if the single child node of the current
   *                          {@link ChainedNode} does not have a header
   * @throws RuntimeException if the header of the single child node of the
   *                          current {@link ChainedNode} does not represent a
   *                          double.
   */
  double getSingleChildNodeAsDouble();

  /**
   * @return a int representation of the single child node of the current
   *         {@link ChainedNode}
   * @throws RuntimeException if the current {@link ChainedNode} does not contain
   *                          a child node or contains multiple child nodes
   * @throws RuntimeException if the single child node of the current
   *                          {@link ChainedNode} does not have a header
   * @throws RuntimeException if the header of the single child node of the
   *                          current {@link ChainedNode} does not represent an
   *                          int.
   */
  int getSingleChildNodeAsInt();

  /**
   * @return a {@link String} representation of the single child node of the
   *         current {@link ChainedNode}
   * @throws RuntimeException if the current {@link ChainedNode} does not contain
   *                          a child node or contains multiple child nodes.
   */
  String getSingleChildNodeAsString();

  /**
   * @return true if the current {@link ChainedNode} has a next node, false
   *         otherwise
   */
  boolean hasNextNode();

  /**
   * @return the double the current {@link ChainedNode} represents
   * @throws RuntimeException if the current {@link ChainedNode} does not
   *                          represent a double.
   */
  double toDouble();

  /**
   * @return the int the current {@link ChainedNode} represents
   * @throws RuntimeException if the current {@link ChainedNode} does not
   *                          represent an int.
   */
  int toInt();

  /**
   * A {@link ChainedNode} represents a {@link Node} if: -The
   * {@link ChainedNode} does not have a next node. -Each child nodes of the
   * {@link ChainedNode} represents a {@link Node}.
   * 
   * @return a {@link Node} representation of the current {@link ChainedNode}
   * @throws RuntimeException if the current {@link ChainedNode} does not
   *                          represent a {@link Node}.
   */
  Node<?> toNode();
}
