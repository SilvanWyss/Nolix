package ch.nolix.system.element.base;

import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.element.base.IElement;

/**
 * @author Silvan Wyss
 * @version 2022-07-08
 */
public abstract class AbstractElement implements IElement {

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean equals(final Object object) {
    return object != null
    && getClass() == object.getClass()
    && hasSameSpecificationAs((AbstractElement) object);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final INode<?> getSpecification() {
    return SpecificationCreator.getSpecificationOfElement(this);
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
   * @param abstractElement
   * @return true if the current {@link AbstractElement} has the same
   *         specification as the given element.
   */
  private boolean hasSameSpecificationAs(final AbstractElement abstractElement) {
    return getSpecification().equals(abstractElement.getSpecification());
  }
}
