/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.container.arraylist;

import java.util.function.Function;
import java.util.function.Predicate;

import ch.nolix.base.commontypetool.arraytool.ArraySorter;
import ch.nolix.base.container.wellordercontainer.AbstractWellOrderContainer;
import ch.nolix.base.container.wellordercontainer.Marker;
import ch.nolix.baseapi.container.list.IArrayList;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of a
 *            {@link AbstractExtendedContainer}.
 */
public abstract class AbstractExtendedContainer<E> extends AbstractWellOrderContainer<E> {
  /**
   * {@inheritDoc}
   */
  @Override
  public final IWellOrderContainer<E> getViewFromOneBasedStartIndexToOneBasedEndIndex(
    final int oneBasedStartIndex,
    final int oneBasedEndIndex) {
    return IntervallContainerView.forContainerAndStartIndexAndEndIndex(this, oneBasedStartIndex, oneBasedEndIndex);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractWellOrderContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <T> IWellOrderContainer<T> getViewOf(final Function<E, T> mapper) {
    return MappingContainerView.forContainerAndMapper(this, mapper);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final IWellOrderContainer<E> getViewOfStoredSelected(final Predicate<E> selector) {
    return FilterContainerView.forContainerAndSelector(this, selector);
  }

  /**
   * The time complexity of this implementation is O(n * log(n)) if the current
   * {@link AbstractWellOrderContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <C extends Comparable<C>> IWellOrderContainer<E> toOrderedList(final Function<E, C> norm) {
    @SuppressWarnings("unchecked")
    final var array = (E[]) toArray();

    ArraySorter.sortArray(array, getCount(), norm);

    return ArrayList.withElements(array);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  protected final <T> IArrayList<T> createEmptyMutableList(Marker<T> marker) {
    return ArrayList.createEmpty();
  }
}
