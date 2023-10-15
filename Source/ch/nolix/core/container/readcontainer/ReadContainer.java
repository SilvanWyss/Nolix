//package declaration
package ch.nolix.core.container.readcontainer;

import java.util.function.Function;

//own imports
import ch.nolix.core.commontype.commontypeconstant.CharacterCatalogue;
import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;

//class
/**
 * A {@link ReadContainer} can read a given container. A {@link ReadContainer}
 * prevents that its accessed container can be mutated. A {@link ReadContainer}
 * does not prevent that the elements of its accessed container can be mutated.
 * 
 * @author Silvan Wyss
 * @date 2017-07-01
 * @param <E> is the type of the elements of a {@link ReadContainer}.
 */
public final class ReadContainer<E> extends Container<E> {

  // static method
  /**
   * @param array
   * @param arrays
   * @param <E2>   is the type of the elements of the given array and arrays.
   * @return a new {@link ReadContainer} for the given array and arrays.
   * @throws ArgumentIsNullException if the given array is null.
   * @throws ArgumentIsNullException if the given arrays is null.
   * @throws ArgumentIsNullException if one of the given arrays is null.
   */
  @SuppressWarnings("unchecked")
  public static <E2> ReadContainer<E2> forArray(final E2[] array, final E2[]... arrays) {
    return new ReadContainer<>(MultiReadContainer.forArray(array, arrays));
  }

  // static method
  /**
   * @param firstElement
   * @param elements
   * @param <E2>         is the type of the given elements.
   * @return a new {@link ReadContainer} with the given elements.
   * @throws ArgumentIsNullException if the given firstElement or one of the given
   *                                 elements is null.
   */
  @SuppressWarnings("unchecked")
  public static <E2> ReadContainer<E2> forElement(final E2 firstElement, final E2... elements) {
    return new ReadContainer<>(LinkedList.withElement(firstElement, elements));
  }

  // static method
  /**
   * @param container
   * @param containers
   * @param <E2>       is the type of the elements of the given container and
   *                   containers.
   * @return a new {@link ReadContainer} for the given containers.
   * @throws ArgumentIsNullException if the given container is null.
   * @throws ArgumentIsNullException if the given containers is null.
   * @throws ArgumentIsNullException if one of the given containers is null.
   */
  @SafeVarargs
  public static <E2> ReadContainer<E2> forIterable(
      final Iterable<? extends E2> container,
      final Iterable<? extends E2>... containers) {
    return new ReadContainer<>(MultiReadContainer.forIterable(container, containers));
  }

  // attribute
  private final Container<E> container;

  // constructor
  /**
   * Creates a new {@link ReadContainer} for an empty container.
   */
  public ReadContainer() {

    // Calls other constructor.
    this(new LinkedList<E>());
  }

  // constructor
  /**
   * Creates a new {@link ReadContainer} for the given container.
   * 
   * @param container
   * @param <E2>      is the type of the elements of the given container.
   * @throws ArgumentIsNullException if the given container is null.
   */
  @SuppressWarnings("unchecked")
  private <E2 extends E> ReadContainer(final Container<E2> container) {

    // Asserts that the given container is not null.
    GlobalValidator.assertThat(container).thatIsNamed(LowerCaseCatalogue.CONTAINER).isNotNull();

    // Sets the container of the current ReadContainer.
    this.container = (Container<E>) container;
  }

  // method
  /**
   * An object equals a {@link ReadContainer} when the object is a
   * {@link ReadContainer} that contains exactly the same elements in the same
   * order.
   * 
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object object) {

    // Handles the case that the given object is a ReadContainer.
    if (object instanceof ReadContainer<?> localContainer) {
      return containsExactlyInSameOrder(localContainer);
    }

    // Handles the case that the given object is not a ReadContainer.
    return false;
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public int getElementCount() {
    return container.getElementCount();
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAt1BasedIndex(final int p1BasedIndex) {
    return container.getStoredAt1BasedIndex(p1BasedIndex);
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredLast() {
    return container.getStoredLast();
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return false;
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return container.iterator();
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public <C extends Comparable<C>> IContainer<E> toOrderedList(final Function<E, C> norm) {
    return LinkedList.fromIterable(this).toOrderedList(norm);
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalogue.COMMA);
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
    return new LinkedList<>();
  }
}
