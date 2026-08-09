/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.element.mutableelement;

import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.generalstate.statemutation.Resettable;
import ch.nolix.systemapi.element.base.Element;

/**
 * A {@link MutableElement} has attributes that can be mutated.
 * 
 * @author Silvan Wyss
 */
public interface MutableElement extends Resettable, Element {
  /**
   * Adds or changes the given attribute to the current {@link MutableElement}.
   * 
   * @param attribute
   * @throws RuntimeException if the given attribute is not valid
   */
  void addOrChangeAttribute(Node<?> attribute);

  /**
   * Adds or changes the given attribute to the current {@link MutableElement}.
   * 
   * @param attribute
   * @throws RuntimeException if the given attribute is not valid
   */
  void addOrChangeAttribute(String attribute);

  /**
   * Adds or changes the given attributes to the current {@link MutableElement}.
   * 
   * @param attributes
   * @throws RuntimeException if one of the given attributes is not valid
   */
  default void addOrChangeAttributes(final Iterable<? extends Node<?>> attributes) {
    if (attributes != null) {
      attributes.forEach(this::addOrChangeAttribute);
    }
  }

  /**
   * Resets the current {@link MutableElement} from the given attributes.
   * 
   * @param attributes
   * @throws RuntimeException if one of the given attributes is not valid
   */
  default void resetFromAttributes(final Iterable<? extends Node<?>> attributes) {
    reset();
    addOrChangeAttributes(attributes);
  }

  /**
   * Resets the current {@link MutableElement} from the given specification.
   * 
   * @param specification
   * @throws RuntimeException if the given specification is not valid
   */
  default void resetFromSpecification(final Node<?> specification) {
    final var attributes = specification.getStoredChildNodes();

    resetFromAttributes(attributes);
  }
}
