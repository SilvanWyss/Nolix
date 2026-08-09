/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.base;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PascalCaseVariableNameCatalog;
import ch.nolix.systemapi.element.base.Element;
import ch.nolix.systemapi.element.base.SpecificationRepresentable;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractElement implements Element {
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean equals(final Object object) {
    return object instanceof SpecificationRepresentable element && hasEqualSpecificationAsElement(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final Node<?> getSpecification() {
    return ImmutableNode.withHeaderAndChildNodes(getSpecificationHeader(), getAttributes());
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

    return PascalCaseVariableNameCatalog.ELEMENT;
  }
}
