//package declaration
package ch.nolix.coreapi.documentapi.chainednodeapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalHeaderHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.requestapi.BlanknessRequestable;

//interface
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
 * @date 2022-07-05
 */
public interface IChainedNode extends BlanknessRequestable, IOptionalHeaderHolder {

  //method declaration
  /**
   * @return true if the current {@link IChainedNode} contains child nodes.
   */
  boolean containsChildNodes();

  //method declaration
  /**
   * @param p1BasedIndex
   * @return the child node of the current {@link IChainedNode} at the given
   *         p1BasedIndex .
   * @throws RuntimeException if the current {@link IChainedNode} does not contain
   *                          a child node at the given p1BasedIndex.
   */
  IChainedNode getChildNodeAt1BasedIndex(int p1BasedIndex);

  //method declaration
  /**
   * @return the number of child nodes of the current {@link IChainedNode}.
   */
  int getChildNodeCount();

  //method declaration
  /**
   * @return the child nodes of the current {@link IChainedNode}.
   */
  IContainer<? extends IChainedNode> getChildNodes();

  //method declaration
  /**
   * @param header
   * @return the first child node with the given header from the current
   *         {@link IChainedNode}.
   * @throws RuntimeException if the current {@link IChainedNode} does not contain
   *                          a child node with the given header.
   */
  IChainedNode getFirstChildNodeWithHeader(String header);

  //method declaration
  /**
   * @return the next node of the current {@link IChainedNode}.
   * @throws RuntimeException if the current {@link IChainedNode} does not have a
   *                          next node.
   */
  IChainedNode getNextNode();

  //method declaration
  /**
   * @return the single child node of the current {@link IChainedNode}.
   * @throws RuntimeException if the current {@link IChainedNode} does not contain
   *                          child nodes or contains several child nodes.
   */
  IChainedNode getSingleChildNode();

  //method declaration
  /**
   * 
   * @return the header of the single child node of the current
   *         {@link IChainedNode}.
   * @throws RuntimeException if the current {@link IChainedNode} does not contain
   *                          child nodes or contains several child nodes.
   * @throws RuntimeException if the single child node of the current
   *                          {@link IChainedNode} does not have a header.
   */
  String getSingleChildNodeHeader();

  //method declaration
  /**
   * @return true if the current {@link IChainedNode} has a next node.
   */
  boolean hasNextNode();

  //method declaration
  /**
   * @return the double the current {@link IChainedNode} represents.
   * @throws RuntimeException if the current {@link IChainedNode} does not
   *                          represent a double.
   */
  double toDouble();

  //method declaration
  /**
   * @return the int the current {@link IChainedNode} represents.
   * @throws RuntimeException if the current {@link IChainedNode} does not
   *                          represent an int.
   */
  int toInt();

  //method declaration
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
