/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.element.mutableelement;

import ch.nolix.baseapi.document.node.Node;

/**
 * @author Silvan Wyss
 * @param <E> the type of a {@link IRespondingMutableElement}.
 */
public interface IRespondingMutableElement<E extends IRespondingMutableElement<E>> extends IMutableElement {
  /**
   * Adds or changes the given attribute to the current
   * {@link IRespondingMutableElement} if the given attributes matches.
   * 
   * @param attribute
   * @return true if the given attribute was added or changed to the current
   *         {@link IRespondingMutableElement}, false otherwise
   */
  boolean addedOrChangedAttribute(Node<?> attribute);
}
