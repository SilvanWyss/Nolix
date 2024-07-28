//package declaration
package ch.nolix.core.container.readcontainer;

//Java imports
import java.util.function.Function;

//own imports
import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.CharacterCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
/**
 * @author Silvan Wyss
 * @version 2017-11-26
 * @param <E> is the type of the elements of a {@link IterableReadContainer}.
 */
public final class IterableReadContainer<E> extends Container<E> {

  //attribute
  private final Iterable<E> container;

  //constructor
  /**
   * Creates a new {@link IterableReadContainer} for a new empty container.
   */
  public IterableReadContainer() {

    //Calls other constructor.
    this(LinkedList.createEmpty());
  }

  //constructor
  /**
   * Creates a new {@link IterableReadContainer} for the given container.
   * 
   * @param container
   * @param <E2>      is the type of the elements of the given container.
   * @throws ArgumentIsNullException if the given container is null.
   */
  @SuppressWarnings("unchecked")
  private <E2 extends E> IterableReadContainer(final Iterable<E2> container) {

    //Asserts that the given container is not null.
    GlobalValidator
      .assertThat(container)
      .thatIsNamed(LowerCaseVariableCatalogue.CONTAINER)
      .isNotNull();

    //Sets the container of the current IterableReadContainer.
    this.container = (Iterable<E>) container;
  }

  //static method
  /**
   * @param iterable
   * @param <E2>     is the type of the elements of the given iterable.
   * @return a new {@link IterableReadContainer} for the given iterable.
   * @throws ArgumentIsNullException if the given iterable is null.
   */
  public static <E2> IterableReadContainer<E2> forIterable(final Iterable<? extends E2> iterable) {
    return new IterableReadContainer<>(iterable);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {

    var size = 0;

    //Iterates the current IterableReadContainer.
    final var iterator = container.iterator();
    while (iterator.hasNext()) {
      size++;
      iterator.next();
    }

    return size;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAt1BasedIndex(final int p1BasedIndex) {

    //Iterates the current IterableReadContainer.
    var i = 1;
    for (final var e : this) {

      //Asserts that the current index is the given index.
      if (i == p1BasedIndex) {
        return e;
      }

      i++;
    }

    throw ArgumentIsOutOfRangeException.forArgumentNameAndArgumentAndRangeWithMinAndMax(
      "1-based index",
      p1BasedIndex,
      1,
      getCount());
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredLast() {
    return getStoredAt1BasedIndex(getCount());
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return false;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return IterableReadContainerIterator.forIterable(container);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public <C extends Comparable<C>> IContainer<E> toOrderedList(final Function<E, C> norm) {
    return LinkedList.fromIterable(this).toOrderedList(norm);
  }

  //method
  /**
   * The complexity of this implementation is O(n). if the current
   * {@link IterableReadContainer} contains n elements.
   * 
   * @return a {@link String} representation of the current
   *         {@link IterableReadContainer}.
   */
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalogue.COMMA);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
    return LinkedList.createEmpty();
  }
}
