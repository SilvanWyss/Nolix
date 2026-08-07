/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.node;

import java.util.Optional;
import java.util.function.Predicate;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalHeaderHolder;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.base.FormattedStringRepresentable;
import ch.nolix.baseapi.document.xml.IXmlNode;
import ch.nolix.baseapi.generalstate.staterequest.BlanknessRequestable;

/**
 * A {@link Node} has the following attributes. -0 or 1 header -an arbitrary
 * number of child {@link Node}s
 * 
 * A {@link Node} that does not have a header and does not contains attributes
 * is blank.
 * 
 * A sub type of {@link Node} may be or may be not mutable.
 * 
 * @author Silvan Wyss
 * @param <N> the type of a {@link Node}
 */
public interface Node<N extends Node<N>>
extends BlanknessRequestable, FormattedStringRepresentable, OptionalHeaderHolder {
  /**
   * @return true if the current {@link Node} contains child {@link Node}s,
   *         false otherwise
   */
  boolean containsChildNodes();

  /**
   * @param selector
   * @return true if the current {@link Node} contains a child {@link Node} the
   *         given selector selects, false otherwise
   */
  boolean containsChildNodeThat(Predicate<Node<?>> selector);

  /**
   * @param header
   * @return true if the current {@link Node} contains a child {@link Node} with
   *         the given header, false otherwise
   */
  boolean containsChildNodeWithHeader(String header);

  /**
   * @return true if the current {@link Node} contains 1 child {@link Node},
   *         false otherwise
   */
  boolean containsOneChildNode();

  /**
   * @return the number of child {@link Node}s of the current {@link Node}
   */
  int getChildNodeCount();

  /**
   * @param selector
   * @return the number of child {@link Node}s the given selector selects from
   *         the current {@link Node}
   * @throws RuntimeException if the given selector is null
   */
  int getChildNodeCount(Predicate<Node<?>> selector);

  /**
   * @return the headers of the child {@link Node}s of the current {@link Node}
   * @throws RuntimeException if one of the child {@link Node}s of the current
   *                          {@link Node} does not have a header
   */
  ExtendedIterable<String> getChildNodesHeaders();

  /**
   * @param selector
   * @return a new {@link Optional} with the first child {@link Node} the given
   *         selector selects from the current {@link Node}, an empty
   *         {@link Optional} otherwise
   */
  Optional<N> getOptionalStoredFirstChildNodeThat(Predicate<Node<?>> selector);

  /**
   * @param oneBasedIndex
   * @return the child {@link Node} at the given oneBasedIndex from the current
   *         {@link Node}
   * @throws RuntimeException if the given index is not positive
   * @throws RuntimeException if the current {@link Node} does not contain a
   *                          child {@link Node} at the given oneBasedIndex
   */
  N getStoredChildNodeAtOneBasedIndex(int oneBasedIndex);

  /**
   * @return the child {@link Node}s of the current {@link Node}
   */
  ExtendedIterable<N> getStoredChildNodes();

  /**
   * @param selector
   * @return the child {@link Node}s the given selector selects from the current
   *         {@link Node}
   */
  ExtendedIterable<N> getStoredChildNodesThat(Predicate<Node<?>> selector);

  /**
   * @param header
   * @return the child {@link Node}s with the given header from the current
   *         {@link Node}
   */
  ExtendedIterable<N> getStoredChildNodesWithHeader(String header);

  /**
   * @return the first child {@link Node} from the current {@link Node}
   * @throws RuntimeException if the current {@link Node} does not contain child
   *                          {@link Node}s
   */
  N getStoredFirstChildNode();

  /**
   * @param selector
   * @return the first child {@link Node} the given selector selects from the
   *         current {@link Node}
   * @throws RuntimeException if the current {@link Node} does not contain a
   *                          child {@link Node} the given selector selects
   */
  N getStoredFirstChildNodeThat(Predicate<Node<?>> selector);

  /**
   * @param header
   * @return the first child {@link Node} with the given header from the current
   *         {@link Node}
   * @throws RuntimeException if the current {@link Node} does not contain a
   *                          child {@link Node} with the given header
   */
  N getStoredFirstChildNodeWithHeader(String header);

  /**
   * @return the single child {@link Node} of the current {@link Node}
   * @throws RuntimeException if the current {@link Node} does not contain child
   *                          {@link Node}s or contains several child
   *                          {@link Node}s
   */
  N getStoredSingleChildNode();

  /**
   * @return the boolean the single child {@link Node} of the current
   *         {@link Node} represents
   * @throws RuntimeException if the current {@link Node} does not contain child
   *                          {@link Node}s or contains several child
   *                          {@link Node}s
   * @throws RuntimeException if the single child {@link Node} of the current
   *                          {@link Node} does not represent a boolean
   */
  boolean getSingleChildNodeAsBoolean(); // NOSONAR: This method returns a boolean representation.

  /**
   * @return the double the single child {@link Node} of the current
   *         {@link Node} represents
   * @throws RuntimeException if the current {@link Node} does not contain child
   *                          {@link Node}s or contains several child
   *                          {@link Node}s
   * @throws RuntimeException if the single child {@link Node} of the current
   *                          {@link Node} does not represent a double
   */
  double getSingleChildNodeAsDouble();

  /**
   * @return the int the single child {@link Node} of the current {@link Node}
   *         represents
   * @throws RuntimeException if the current {@link Node} does not contain child
   *                          {@link Node}s or contains several child
   *                          {@link Node}s
   * @throws RuntimeException if the single child {@link Node} of the current
   *                          {@link Node} does not represent an int
   */
  int getSingleChildNodeAsInt();

  /**
   * @return the header of the single child {@link Node} of the current
   *         {@link Node}
   * @throws RuntimeException if the current {@link Node} does not contain child
   *                          {@link Node}s or contains several child
   *                          {@link Node}s
   * @throws RuntimeException if the single child {@link Node} of the current
   *                          {@link Node} does not have a header
   */
  String getSingleChildNodeHeader();

  /**
   * @return the boolean the current {@link Node} represents
   * @throws RuntimeException if the current {@link Node} does not represent a
   *                          boolean
   */
  boolean toBoolean();

  /**
   * @return the double the current {@link Node} represents
   * @throws RuntimeException if the current {@link Node} does not represent a
   *                          double
   */
  double toDouble();

  /**
   * @return the int the current {@link Node} represents
   * @throws RuntimeException if the current {@link Node} does not represent an
   *                          int
   */
  int toInt();

  /**
   * @return a {@link IXmlNode} representation of the current {@link Node}
   */
  IXmlNode<?> toXml();

  /**
   * @param header
   * @return a new {@link Node} from the current {@link Node} with the given new
   *         header
   * @throws RuntimeException if the given header is null or blank
   */
  Node<?> withNewHeader(String header);
}
