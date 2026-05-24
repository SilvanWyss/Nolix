/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.base;

import ch.nolix.base.document.node.Node;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.systemapi.element.base.IElement;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractElement implements IElement {
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean equals(final Object object) {
    return //
    object != null
    && getClass() == object.getClass()
    && hasSameSpecificationAs((AbstractElement) object);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final INode<?> getSpecification() {
    return Node.withHeaderAndChildNodes(getSpecificationHeader(), getAttributes());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int hashCode() {
    return getSpecification().hashCode();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String toString() {
    return getSpecification().toString();
  }

  /**
   * @return the header of the specification of the current
   *         {@link AbstractElement}
   */
  private String getSpecificationHeader() {
    final var localClass = getClass();

    if (!localClass.isAnonymousClass()) {
      return localClass.getSimpleName();
    }

    return PascalCaseVariableCatalog.ELEMENT;
  }

  /**
   * @param abstractElement
   * @return true if the current {@link AbstractElement} has the same
   *         specification as the given element, false otherwise.
   */
  private boolean hasSameSpecificationAs(final AbstractElement abstractElement) {
    return getSpecification().equals(abstractElement.getSpecification());
  }
}
