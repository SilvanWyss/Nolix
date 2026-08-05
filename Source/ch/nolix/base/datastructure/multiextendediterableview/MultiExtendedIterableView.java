/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.multiextendediterableview;

import ch.nolix.base.datastructure.arrayextendediterableview.ArrayExtendedIterableView;
import ch.nolix.base.datastructure.arraylist.ArrayList;
import ch.nolix.base.datastructure.extendediterable.AbstractExtendedIterable;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.datastructure.iterableextendediterableview.IterableExtendedIterableView;
import ch.nolix.baseapi.datastructure.copyableiterator.CopyableIterator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.IArrayList;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.baseapi.foundation.marker.Marker;
import ch.nolix.baseapi.generalcatalog.textcatalog.CharacterCatalog;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of a {@link MultiExtendedIterableView}.
 */
public final class MultiExtendedIterableView<E> extends AbstractExtendedIterable<E> {
  private final ExtendedIterable<ExtendedIterable<E>> wellOrderContainers;

  private MultiExtendedIterableView() {
    wellOrderContainers = ImmutableList.createEmpty();
  }

  private MultiExtendedIterableView(@SuppressWarnings("unchecked") final E[]... arrays) {
    final IArrayList<ExtendedIterable<E>> localContainers = ArrayList.createEmpty();

    for (final var a : arrays) {
      localContainers.addAtEnd(ArrayExtendedIterableView.forArray(a));
    }

    wellOrderContainers = localContainers;
  }

  private MultiExtendedIterableView(@SuppressWarnings("unchecked") final Iterable<? extends E>... iterables) {
    final IArrayList<ExtendedIterable<E>> localContainers = ArrayList.createEmpty();

    for (final var i : iterables) {
      localContainers.addAtEnd(IterableExtendedIterableView.forIterable(i));
    }

    wellOrderContainers = localContainers;
  }

  public static <T> MultiExtendedIterableView<T> forArrays(
    @SuppressWarnings("unchecked") final T[]... arrays) {
    return new MultiExtendedIterableView<>(arrays);
  }

  public static <T> MultiExtendedIterableView<T> forEmpty() {
    return new MultiExtendedIterableView<>();
  }

  @SafeVarargs
  public static <T> MultiExtendedIterableView<T> forIterables(final Iterable<? extends T>... iterables) {
    return new MultiExtendedIterableView<>(iterables);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return MultiExtendedIterableViewIterator.forContainers(wellOrderContainers);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return wellOrderContainers.getSumOfInts(ExtendedIterable::getCount).intValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {
    var i = 1;
    for (final var e : this) {
      if (i == oneBasedIndex) {
        return e;
      }

      i++;
    }

    throw //
    ArgumentIsOutOfRangeException.forArgumentAndArgumentNameAndRangeWithMinAndMax(
      oneBasedIndex,
      "1-based index",
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
  public String toString() {
    return toStringWithDelimiter(CharacterCatalog.COMMA);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <T> IArrayList<T> createEmptyArrayListFromMarkerWithInitialCapacity(
    final Marker<T> marker,
    final int initialCapacity) {
    return ArrayList.withInitialCapacity(initialCapacity);
  }
}
