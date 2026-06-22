/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.extendediterableview;

import ch.nolix.base.datastructure.arrayextendediterableview.ArrayExtendedIterableView;
import ch.nolix.base.datastructure.arraylist.AbstractExtendedContainer;
import ch.nolix.base.datastructure.arraylist.ArrayList;
import ch.nolix.base.datastructure.extendediterable.Marker;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.baseapi.commontype.charactertool.CharacterCatalog;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.iterator.CopyableIterator;
import ch.nolix.baseapi.datastructure.list.IArrayList;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of a {@link MultiContainerView}.
 */
public final class MultiContainerView<E> extends AbstractExtendedContainer<E> {
  private final ExtendedIterable<ExtendedIterable<E>> wellOrderContainers;

  private MultiContainerView() {
    wellOrderContainers = ImmutableList.createEmpty();
  }

  private MultiContainerView(@SuppressWarnings("unchecked") final E[]... arrays) {
    final IArrayList<ExtendedIterable<E>> localContainers = ArrayList.createEmpty();

    for (final var a : arrays) {
      localContainers.addAtEnd(ArrayExtendedIterableView.forArray(a));
    }

    wellOrderContainers = localContainers;
  }

  private MultiContainerView(@SuppressWarnings("unchecked") final Iterable<? extends E>... iterables) {
    final IArrayList<ExtendedIterable<E>> localContainers = ArrayList.createEmpty();

    for (final var i : iterables) {
      localContainers.addAtEnd(ExtendedIterableView.forIterable(i));
    }

    wellOrderContainers = localContainers;
  }

  public static <T> MultiContainerView<T> forArrays(
    @SuppressWarnings("unchecked") final T[]... arrays) {
    return new MultiContainerView<>(arrays);
  }

  public static <T> MultiContainerView<T> forEmpty() {
    return new MultiContainerView<>();
  }

  @SafeVarargs
  public static <T> MultiContainerView<T> forIterables(final Iterable<? extends T>... iterables) {
    return new MultiContainerView<>(iterables);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return MultiContainerViewIterator.forContainers(wellOrderContainers);
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
    return toStringWithSeparator(CharacterCatalog.COMMA);
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
