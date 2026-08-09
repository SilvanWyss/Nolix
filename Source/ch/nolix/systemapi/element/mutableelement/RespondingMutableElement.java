/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.element.mutableelement;

import ch.nolix.baseapi.document.node.Node;

/**
 * @author Silvan Wyss
 * @param <E> the type of a {@link RespondingMutableElement}.
 */
public interface RespondingMutableElement<E extends RespondingMutableElement<E>> extends MutableElement {
  /**
   * Adds or changes the given attribute to the current
   * {@link RespondingMutableElement} if the given attributes matches.
   * 
   * @param attribute
   * @return true if the given attribute was added or changed to the current
   *         {@link RespondingMutableElement}, false otherwise
   */
  boolean addedOrChangedAttribute(Node<?> attribute);
}
