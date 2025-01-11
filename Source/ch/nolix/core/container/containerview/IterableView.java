package ch.nolix.core.container.containerview;

import java.util.function.Function;

import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.CharacterCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2017-11-26
 * @param <E> is the type of the elements of a {@link IterableView}.
 */
public final class IterableView<E> extends Container<E> {

  private final Iterable<E> iterable;

  /**
   * Creates a new {@link IterableView} for a new empty container.
   */
  public IterableView() {

    //Calls other constructor.
    this(LinkedList.createEmpty());
  }

  /**
   * Creates a new {@link IterableView} for the given container.
   * 
   * @param container
   * @param <E2>      is the type of the elements of the given container.
   * @throws ArgumentIsNullException if the given container is null.
   */
  @SuppressWarnings("unchecked")
  private <E2 extends E> IterableView(final Iterable<E2> container) {

    //Asserts that the given container is not null.
    GlobalValidator
      .assertThat(container)
      .thatIsNamed(LowerCaseVariableCatalog.CONTAINER)
      .isNotNull();

    //Sets the container of the current IterableView.
    this.iterable = (Iterable<E>) container;
  }

  /**
   * @param iterable
   * @param <E2>     is the type of the elements of the given iterable.
   * @return a new {@link IterableView} for the given iterable.
   * @throws ArgumentIsNullException if the given iterable is null.
   */
  public static <E2> IterableView<E2> forIterable(final Iterable<? extends E2> iterable) {
    return new IterableView<>(iterable);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {

    var size = 0;

    //Iterates the current IterableView.
    final var iterator = iterable.iterator();
    while (iterator.hasNext()) {
      size++;
      iterator.next();
    }

    return size;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAt1BasedIndex(final int param1BasedIndex) {

    //Iterates the current IterableView.
    var i = 1;
    for (final var e : this) {

      //Asserts that the current index is the given index.
      if (i == param1BasedIndex) {
        return e;
      }

      i++;
    }

    throw ArgumentIsOutOfRangeException.forArgumentNameAndArgumentAndRangeWithMinAndMax(
      "1-based index",
      param1BasedIndex,
      1,
      getCount());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return IterableViewIterator.forIterable(iterable);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <C extends Comparable<C>> IContainer<E> toOrderedList(final Function<E, C> norm) {
    return LinkedList.fromIterable(this).toOrderedList(norm);
  }

  /**
   * The time complexity of this implementation is O(n). if the current
   * {@link IterableView} contains n elements.
   * 
   * @return a {@link String} representation of the current {@link IterableView}.
   */
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalog.COMMA);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
    return LinkedList.createEmpty();
  }
}
