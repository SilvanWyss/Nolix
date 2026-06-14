/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.container.containerview;

import ch.nolix.base.container.arraylist.AbstractExtendedContainer;
import ch.nolix.base.container.arraylist.ArrayList;
import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.baseapi.commontypetool.charactertool.CharacterCatalog;
import ch.nolix.baseapi.container.iterator.CopyableIterator;
import ch.nolix.baseapi.container.list.IArrayList;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of a {@link MultiContainerView}.
 */
public final class MultiContainerView<E> extends AbstractExtendedContainer<E> {
  private final IWellOrderContainer<IWellOrderContainer<E>> wellOrderContainers;

  private MultiContainerView() {
    wellOrderContainers = ImmutableList.createEmpty();
  }

  private MultiContainerView(@SuppressWarnings("unchecked") final E[]... arrays) {
    final IArrayList<IWellOrderContainer<E>> localContainers = ArrayList.createEmpty();

    for (final var a : arrays) {
      localContainers.addAtEnd(ArrayContainerView.forArray(a));
    }

    wellOrderContainers = localContainers;
  }

  private MultiContainerView(@SuppressWarnings("unchecked") final Iterable<? extends E>... iterables) {
    final IArrayList<IWellOrderContainer<E>> localContainers = ArrayList.createEmpty();

    for (final var i : iterables) {
      localContainers.addAtEnd(IterableContainerView.forIterable(i));
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
    return wellOrderContainers.getSumOfInts(IWellOrderContainer::getCount).intValue();
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
}
