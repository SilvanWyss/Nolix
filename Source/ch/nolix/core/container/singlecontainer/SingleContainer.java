//package declaration
package ch.nolix.core.container.singlecontainer;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;

//class
/**
 * A {@link SingleContainer} is either empty or stores 1 element. A
 * {@link SingleContainer} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2020-01-11
 * @param <E> is the type of the element of a {@link SingleContainer}.
 */
public final class SingleContainer<E> implements ISingleContainer<E> {

  // static method
  /**
   * @param <E2>    is the type of the given element.
   * @param element
   * @return a new {@link SingleContainer} with the given element if the given
   *         element is not null, a new empty {@link SingleContainer} otherwise.
   */
  public static <E2> SingleContainer<E2> withElementOrEmpty(final E2 element) {

    if (element == null) {
      return new SingleContainer<>();
    }

    return new SingleContainer<>(element);
  }

  // optional attribute
  private final E element;

  // constructor
  /**
   * Creates a new {@link SingleContainer} that is empty.
   */
  public SingleContainer() {
    element = null;
  }

  // constructor
  /**
   * Creates a new {@link SingleContainer} with the given element.
   * 
   * @param element
   * @throws ArgumentIsNullException if the given element is null.
   */
  public SingleContainer(final E element) {

    GlobalValidator.assertThat(element).thatIsNamed(LowerCaseCatalogue.ELEMENT).isNotNull();

    this.element = element;
  }

  // method
  // For a better performance, this implementation does not use all comfortable
  // methods.
  /**
   * @return the element of the current {@link SingleContainer}.
   * @throws EmptyArgumentException if the current {@link SingleContainer} is
   *                                empty.
   */
  @Override
  public E getStoredElement() {

    if (element == null) {
      throw EmptyArgumentException.forArgument(this);
    }

    return element;
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return (element == null);
  }
}
