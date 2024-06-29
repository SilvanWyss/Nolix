//package declaration
package ch.nolix.core.container.pair;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.pairapi.IPair;

//class
/**
 * A {@link Pair} contains 2 elements. A {@link Pair} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <E1> is the type of the element 1 of a {@link Pair}.
 * @param <E2> is the type of the element 2 of a {@link Pair}.
 */
public final class Pair<E1, E2> implements IPair<E1, E2> {

  //attribute
  private final E1 element1;

  //attribute
  private final E2 element2;

  //constructor
  /**
   * Creates a new {@link Pair} with the given elements.
   * 
   * @param element1
   * @param element2
   * @throws ArgumentIsNullException if the given element 1 is null.
   * @throws ArgumentIsNullException if the given element 2 is null.
   */
  public Pair(final E1 element1, final E2 element2) {

    //Asserts that the given element 1 is not null.
    GlobalValidator.assertThat(element1).thatIsNamed("element 1").isNotNull();

    //Asserts that the given element 2 is not null.
    GlobalValidator.assertThat(element2).thatIsNamed("element 2").isNotNull();

    //Sets the element 1 of the current {@link Pair}.
    this.element1 = element1;

    //Sets the element 2 of the current {@link Pair}.
    this.element2 = element2;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public E1 getStoredElement1() {
    return element1;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public E2 getStoredElement2() {
    return element2;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasElement1(final Object object) {
    return (element1 == object);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasElement2(final Object object) {
    return (element2 == object);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return ("(" + getStoredElement1() + "," + getStoredElement2() + ")");
  }
}
