/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.arraylist;

import java.util.function.Function;
import java.util.function.Predicate;

import ch.nolix.base.commontype.arraytool.ArraySorter;
import ch.nolix.base.datastructure.extendediterable.AbstractExtendedIterable;
import ch.nolix.base.datastructure.extendediterable.Marker;
import ch.nolix.base.datastructure.extendediterablemapperview.ExtendedIterableMapperView;
import ch.nolix.base.datastructure.filterextendediterableview.FilterExtendedIterableView;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.IArrayList;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of a
 *            {@link AbstractExtendedContainer}.
 */
public abstract class AbstractExtendedContainer<E> extends AbstractExtendedIterable<E> {
  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<E> getViewFromOneBasedStartIndexToOneBasedEndIndex(
    final int oneBasedStartIndex,
    final int oneBasedEndIndex) {
    return IntervallContainerView.forContainerAndStartIndexAndEndIndex(this, oneBasedStartIndex, oneBasedEndIndex);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <T> ExtendedIterable<T> getViewOf(final Function<E, T> mapper) {
    return ExtendedIterableMapperView.forContainerAndMapper(this, mapper);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<E> getViewOfStoredSelected(final Predicate<E> selector) {
    return FilterExtendedIterableView.forContainerAndSelector(this, selector);
  }

  /**
   * The time complexity of this implementation is O(n * log(n)) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <C extends Comparable<C>> ExtendedIterable<E> toOrderedList(final Function<E, C> norm) {
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
