package ch.nolix.core.container.linkedlist;

import java.util.function.Function;
import java.util.function.Predicate;

import ch.nolix.core.commontypetool.arraytool.ArraySorter;
import ch.nolix.core.container.base.AbstractContainer;
import ch.nolix.core.container.base.Marker;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;

/**
 * @author Silvan Wyss
 * @version 2025-05-18
 * @param <E> is the type of the elements of a
 *            {@link AbstractExtendedContainer}.
 */
public abstract class AbstractExtendedContainer<E> extends AbstractContainer<E> {

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<E> getViewFromOneBasedStartIndexToOneBasedEndIndex(
    final int oneBasedStartIndex,
    final int oneBasedEndIndex) {
    return IntervallContainerView.forContainerAndStartIndexAndEndIndex(this, oneBasedStartIndex, oneBasedEndIndex);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <T> IContainer<T> getViewOf(final Function<E, T> mapper) {
    return MappingContainerView.forContainerAndMapper(this, mapper);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<E> getViewOfStoredSelected(final Predicate<E> selector) {
    return FilterContainerView.forContainerAndSelector(this, selector);
  }

  /**
   * The time complexity of this implementation is O(n * log(n)) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <C extends Comparable<C>> IContainer<E> toOrderedList(final Function<E, C> norm) {

    @SuppressWarnings("unchecked")
    final var array = (E[]) toArray();

    ArraySorter.sortArray(array, getCount(), norm);

    return LinkedList.fromArray(array);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  protected final <E2> ILinkedList<E2> createEmptyMutableList(Marker<E2> marker) {
    return LinkedList.createEmpty();
  }
}
