package ch.nolix.coreapi.document.chainednode;

import ch.nolix.coreapi.attribute.optionalattribute.IOptionalHeaderHolder;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.stateapi.staterequestapi.BlanknessRequestable;

/**
 * A {@link IChainedNode} has the following attributes. -0 or 1 header -an
 * arbitrary number of child {@link IChainedNode}s -0 or 1 next
 * {@link IChainedNode}
 * 
 * A {@link IChainedNode} that does not have a header, does not contain
 * attributes and does not have a next {@link IChainedNode} is blank.
 * 
 * A {@link IChainedNode} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2022-07-05
 */
public interface IChainedNode extends BlanknessRequestable, IOptionalHeaderHolder {

  /**
   * @return true if the current {@link IChainedNode} contains child nodes.
   */
  boolean containsChildNodes();

  /**
   * @param oneBasedIndex
   * @return the child node of the current {@link IChainedNode} at the given
   *         oneBasedIndex .
   * @throws RuntimeException if the current {@link IChainedNode} does not contain
   *                          a child node at the given oneBasedIndex.
   */
  IChainedNode getChildNodeAtOneBasedIndex(int oneBasedIndex);

  /**
   * @return the number of child nodes of the current {@link IChainedNode}.
   */
  int getChildNodeCount();

  /**
   * @return the child nodes of the current {@link IChainedNode}.
   */
  IContainer<? extends IChainedNode> getChildNodes();

  /**
   * @param header
   * @return the first child node with the given header from the current
   *         {@link IChainedNode}.
   * @throws RuntimeException if the current {@link IChainedNode} does not contain
   *                          a child node with the given header.
   */
  IChainedNode getFirstChildNodeWithHeader(String header);

  /**
   * @return the next node of the current {@link IChainedNode}.
   * @throws RuntimeException if the current {@link IChainedNode} does not have a
   *                          next node.
   */
  IChainedNode getNextNode();

  /**
   * @return the single child node of the current {@link IChainedNode}.
   * @throws RuntimeException if the current {@link IChainedNode} does not contain
   *                          a child node or contains multiple child nodes.
   */
  IChainedNode getSingleChildNode();

  /**
   * 
   * @return the header of the single child node of the current
   *         {@link IChainedNode}.
   * @throws RuntimeException if the current {@link IChainedNode} does not contain
   *                          a child node or contains multiple child nodes.
   * @throws RuntimeException if the single child node of the current
   *                          {@link IChainedNode} does not have a header.
   */
  String getSingleChildNodeHeader();

  /**
   * @return a double representation of the single child node of the current
   *         {@link IChainedNode}.
   * @throws RuntimeException if the current {@link IChainedNode} does not contain
   *                          a child nodes or contains multiple child nodes.
   * @throws RuntimeException if the single child node of the current
   *                          {@link IChainedNode} does not have a header.
   * @throws RuntimeException if the header of the single child node of the
   *                          current {@link IChainedNode} does not represent a
   *                          double.
   */
  double getSingleChildNodeAsDouble();

  /**
   * @return a int representation of the single child node of the current
   *         {@link IChainedNode}.
   * @throws RuntimeException if the current {@link IChainedNode} does not contain
   *                          a child node or contains multiple child nodes.
   * @throws RuntimeException if the single child node of the current
   *                          {@link IChainedNode} does not have a header.
   * @throws RuntimeException if the header of the single child node of the
   *                          current {@link IChainedNode} does not represent an
   *                          int.
   */
  int getSingleChildNodeAsInt();

  /**
   * @return a {@link String} representation of the single child node of the
   *         current {@link IChainedNode}.
   * @throws RuntimeException if the current {@link IChainedNode} does not contain
   *                          a child node or contains multiple child nodes.
   */
  String getSingleChildNodeAsString();

  /**
   * @return true if the current {@link IChainedNode} has a next node.
   */
  boolean hasNextNode();

  /**
   * @return the double the current {@link IChainedNode} represents.
   * @throws RuntimeException if the current {@link IChainedNode} does not
   *                          represent a double.
   */
  double toDouble();

  /**
   * @return the int the current {@link IChainedNode} represents.
   * @throws RuntimeException if the current {@link IChainedNode} does not
   *                          represent an int.
   */
  int toInt();

  /**
   * A {@link IChainedNode} represents a {@link INode} if: -The
   * {@link IChainedNode} does not have a next node. -Each child nodes of the
   * {@link IChainedNode} represents a {@link INode}.
   * 
   * @return a {@link INode} representation of the current {@link IChainedNode}.
   * @throws RuntimeException if the current {@link IChainedNode} does not
   *                          represent a {@link INode}.
   */
  INode<?> toNode();
}
