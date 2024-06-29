//package declaration
package ch.nolix.system.element.base;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.baseapi.IElement;

//class
/**
 * @author Silvan Wyss
 * @version 2022-07-08
 */
public abstract class Element implements IElement {

  //constant
  private static final SpecificationCreator SPECIFICATION_CREATOR = new SpecificationCreator();

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean equals(final Object object) {
    return object != null
    && getClass() == object.getClass()
    && hasSameSpecificationAs((Element) object);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final INode<?> getSpecification() {
    return SPECIFICATION_CREATOR.getSpecificationOfElement(this);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final int hashCode() {
    return getSpecification().hashCode();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final String toString() {
    return getSpecification().toString();
  }

  //method
  /**
   * @param element
   * @return true if the current {@link Element} has the same specification as the
   *         given element.
   */
  private boolean hasSameSpecificationAs(final Element element) {
    return getSpecification().equals(element.getSpecification());
  }
}
