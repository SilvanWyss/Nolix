package ch.nolix.coreapi.documentapi.nodeapi;

import java.util.Optional;
import java.util.function.Predicate;

import ch.nolix.coreapi.attribute.optionalattribute.IOptionalHeaderHolder;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.documentapi.xmlapi.IXmlNode;
import ch.nolix.coreapi.stateapi.staterequestapi.BlanknessRequestable;

/**
 * A {@link INode} has the following attributes. -0 or 1 header -an arbitrary
 * number of child {@link INode}s
 * 
 * A {@link INode} that does not have a header and does not contains attributes
 * is blank.
 * 
 * A sub type of {@link INode} may be or may be not mutable.
 * 
 * @author Silvan Wyss
 * @version 2022-06-24
 * @param <N> is the type of a {@link INode}.
 */
public interface INode<N extends INode<N>> extends BlanknessRequestable, IOptionalHeaderHolder {

  /**
   * @param header
   * @return a new {@link INode} that is a copy of the current {@link INode} with
   *         the given header.
   * @throws RuntimeException if the given header is null.
   * @throws RuntimeException if the given header is blank.
   */
  INode<?> asWithHeader(String header);

  /**
   * @return true if the current {@link INode} contains child {@link INode}s.
   */
  boolean containsChildNodes();

  /**
   * @param selector
   * @return true if the current {@link INode} contains a child {@link INode} the
   *         given selector selects.
   */
  boolean containsChildNodeThat(Predicate<INode<?>> selector);

  /**
   * @param header
   * @return true if the current {@link INode} contains a child {@link INode} with
   *         the given header.
   */
  boolean containsChildNodeWithHeader(String header);

  /**
   * @return true if the current {@link INode} contains 1 child {@link INode}.
   */
  boolean containsOneChildNode();

  /**
   * @return the number of child {@link INode}s of the current {@link INode}.
   */
  int getChildNodeCount();

  /**
   * @return the headers of the child {@link INode}s of the current {@link INode}.
   * @throws RuntimeException if one of the child {@link INode}s of the current
   *                          {@link INode} does not have a header.
   */
  IContainer<String> getChildNodesHeaders();

  /**
   * @param selector
   * @return a new {@link Optional} wit the first child {@link INode} the given
   *         selector selects from the current {@link INode}, an empty
   *         {@link Optional} otherwise.
   */
  Optional<N> getOptionalStoredFirstChildNodeThat(Predicate<INode<?>> selector);

  /**
   * @param oneBasedIndex
   * @return the child {@link INode} at the given oneBasedIndex from the
   *         current {@link INode}.
   * @throws RuntimeException if the given index is not positive.
   * @throws RuntimeException if the current {@link INode} does not contain a
   *                          child {@link INode} at the given oneBasedIndex.
   */
  N getStoredChildNodeAtOneBasedIndex(int oneBasedIndex);

  /**
   * @return the child {@link INode}s of the current {@link INode}.
   */
  IContainer<N> getStoredChildNodes();

  /**
   * @param selector
   * @return the child {@link INode}s the given selector selects from the current
   *         {@link INode}.
   */
  IContainer<N> getStoredChildNodesThat(Predicate<INode<?>> selector);

  /**
   * @param header
   * @return the child {@link INode}s with the given header from the current
   *         {@link INode}.
   */
  IContainer<N> getStoredChildNodesWithHeader(String header);

  /**
   * @return the first child {@link INode} from the current {@link INode}.
   * @throws RuntimeException if the current {@link INode} does not contain child
   *                          {@link INode}s.
   */
  N getStoredFirstChildNode();

  /**
   * @param selector
   * @return the first child {@link INode} the given selector selects from the
   *         current {@link INode}.
   * @throws RuntimeException if the current {@link INode} does not contain a
   *                          child {@link INode} the given selector selects.
   */
  N getStoredFirstChildNodeThat(Predicate<INode<?>> selector);

  /**
   * @param header
   * @return the first child {@link INode} with the given header from the current
   *         {@link INode}.
   * @throws RuntimeException if the current {@link INode} does not contain a
   *                          child {@link INode} with the given header.
   */
  N getStoredFirstChildNodeWithHeader(String header);

  /**
   * @return the single child {@link INode} of the current {@link INode}.
   * @throws RuntimeException if the current {@link INode} does not contain child
   *                          {@link INode}s or contains several child
   *                          {@link INode}s.
   */
  N getStoredSingleChildNode();

  /**
   * @return the boolean the single child {@link INode} of the current
   *         {@link INode} represents.
   * @throws RuntimeException if the current {@link INode} does not contain child
   *                          {@link INode}s or contains several child
   *                          {@link INode}s.
   * @throws RuntimeException if the single child {@link INode} of the current
   *                          {@link INode} does not represent a boolean.
   */
  boolean getSingleChildNodeAsBoolean(); //NOSONAR: This method returns a boolean representation.

  /**
   * @return the double the single child {@link INode} of the current
   *         {@link INode} represents.
   * @throws RuntimeException if the current {@link INode} does not contain child
   *                          {@link INode}s or contains several child
   *                          {@link INode}s.
   * @throws RuntimeException if the single child {@link INode} of the current
   *                          {@link INode} does not represent a double.
   */
  double getSingleChildNodeAsDouble();

  /**
   * @return the int the single child {@link INode} of the current {@link INode}
   *         represents.
   * @throws RuntimeException if the current {@link INode} does not contain child
   *                          {@link INode}s or contains several child
   *                          {@link INode}s.
   * @throws RuntimeException if the single child {@link INode} of the current
   *                          {@link INode} does not represent an int.
   */
  int getSingleChildNodeAsInt();

  /**
   * @return the header of the single child {@link INode} of the current
   *         {@link INode}.
   * @throws RuntimeException if the current {@link INode} does not contain child
   *                          {@link INode}s or contains several child
   *                          {@link INode}s.
   * @throws RuntimeException if the single child {@link INode} of the current
   *                          {@link INode} does not have a header.
   */
  String getSingleChildNodeHeader();

  /**
   * @return the boolean the current {@link INode} represents.
   * @throws RuntimeException if the current {@link INode} does not represent a
   *                          boolean.
   */
  boolean toBoolean();

  /**
   * @return the double the current {@link INode} represents.
   * @throws RuntimeException if the current {@link INode} does not represent a
   *                          double.
   */
  double toDouble();

  /**
   * @return a formatted {@link String} representation of the current
   *         {@link INode}.
   */
  String toFormattedString();

  /**
   * @return the int the current {@link INode} represents.
   * @throws RuntimeException if the current {@link INode} does not represent an
   *                          int.
   */
  int toInt();

  /**
   * @return a {@link IXmlNode} representation of the current {@link INode}.
   */
  IXmlNode<?> toXml();
}
