package ch.nolix.system.element.base;

import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.baseapi.IElement;

/**
 * @author Silvan Wyss
 * @version 2022-07-08
 */
public abstract class Element implements IElement {

  private static final SpecificationCreator SPECIFICATION_CREATOR = new SpecificationCreator();

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean equals(final Object object) {
    return object != null
    && getClass() == object.getClass()
    && hasSameSpecificationAs((Element) object);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final INode<?> getSpecification() {
    return SPECIFICATION_CREATOR.getSpecificationOfElement(this);
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
   * @param element
   * @return true if the current {@link Element} has the same specification as the
   *         given element.
   */
  private boolean hasSameSpecificationAs(final Element element) {
    return getSpecification().equals(element.getSpecification());
  }
}
