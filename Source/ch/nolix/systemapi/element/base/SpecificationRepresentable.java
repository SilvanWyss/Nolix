/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.element.base;

import ch.nolix.baseapi.document.node.Node;

/**
 * A {@link SpecificationRepresentable} can be represented by a specification.
 * 
 * @author Silvan Wyss
 */
public interface SpecificationRepresentable {
  /**
   * @return the specification of the current {@link SpecificationRepresentable}
   */
  Node<?> getSpecification();

  /**
   * @param element
   * @return true if the current {@link SpecificationRepresentable} has an equal
   *         specification as the given element, false otherwise
   */
  default boolean hasEqualSpecificationAsElement(final SpecificationRepresentable element) {
    return element != null && getSpecification().equals(element.getSpecification());
  }

  /**
   * @return a formated {@link String} representation of the current
   *         {@link SpecificationRepresentable}
   */
  default String toFormatedString() {
    return getSpecification().toFormattedString();
  }
}
