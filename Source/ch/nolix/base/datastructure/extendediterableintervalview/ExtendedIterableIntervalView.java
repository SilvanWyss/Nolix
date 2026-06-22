/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.extendediterableintervalview;

import ch.nolix.base.datastructure.arraylist.AbstractExtendedContainer;
import ch.nolix.base.datastructure.arraylist.ArrayList;
import ch.nolix.base.datastructure.extendediterable.Marker;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.commontype.charactertool.CharacterCatalog;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.iterator.CopyableIterator;
import ch.nolix.baseapi.datastructure.list.IArrayList;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;

/**
 * A {@link ExtendedIterableIntervalView} can iterate over an intervall of a
 * {@link ExtendedIterable}.
 * 
 * @author Silvan Wyss
 * @param <E> the type of the elements of a
 *            {@link ExtendedIterableIntervalView}
 */
public final class ExtendedIterableIntervalView<E> extends AbstractExtendedContainer<E> {
  private final ExtendedIterable<E> extendedIterable;

  private final int startIndex;

  private final int endIndex;

  /**
   * Creates a new {@link ExtendedIterableIntervalView} with the given
   * extendedIterable, startIndex and endIndex.
   * 
   * @param extendedIterable
   * @param startIndex
   * @param endIndex
   * @throws RuntimeException if the given extendedIterable is null.
   * @throws RuntimeException if the given startIndex is not positive.
   * @throws RuntimeException if the given endIndex is not positive.
   * @throws RuntimeException if the given endIndex is smaller than the given
   *                          startIndex.
   * @throws RuntimeException if the given endIndex is bigger than the number of
   *                          elements of the given extendedIterable.
   */
  private ExtendedIterableIntervalView(
    final ExtendedIterable<E> extendedIterable,
    final int startIndex,
    final int endIndex) {
    Validator.assertThat(extendedIterable).thatIsNamed(LowerCaseVariableCatalog.CONTAINER).isNotNull();
    Validator.assertThat(startIndex).thatIsNamed(LowerCaseVariableCatalog.START_INDEX).isPositive();
    Validator.assertThat(endIndex).thatIsNamed(LowerCaseVariableCatalog.END_INDEX).isPositive();

    Validator
      .assertThat(endIndex)
      .thatIsNamed(LowerCaseVariableCatalog.END_INDEX)
      .isBiggerThanOrEquals(startIndex);

    Validator
      .assertThat(endIndex)
      .thatIsNamed(LowerCaseVariableCatalog.END_INDEX)
      .isNotBiggerThan(extendedIterable.getCount());

    this.extendedIterable = extendedIterable;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

  /**
   * @param extendedIterable
   * @param startIndex
   * @param endIndex
   * @param <T>              is the type of the elements of the created
   *                         {@link ExtendedIterableIntervalView}.
   * @return a new {@link ExtendedIterableIntervalView} with the given
   *         extendedIterable, startIndex and endIndex.
   * @throws RuntimeException if the given extendedIterable is null.
   * @throws RuntimeException if the given startIndex is not positive.
   * @throws RuntimeException if the given endIndex is not positive.
   * @throws RuntimeException if the given endIndex is smaller than the given
   *                          startIndex.
   * @throws RuntimeException if the given endIndex is bigger than the number of
   *                          elements of the given extendedIterable.
   */
  public static <T> ExtendedIterableIntervalView<T> forExtendedIterableAndStartIndexAndEndIndex(
    final ExtendedIterable<T> extendedIterable,
    final int startIndex,
    final int endIndex) {
    return new ExtendedIterableIntervalView<>(extendedIterable, startIndex, endIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return endIndex - startIndex + 1;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {
    Validator.assertThat(oneBasedIndex).thatIsNamed(LowerCaseVariableCatalog.INDEX).isPositive();
    Validator.assertThat(oneBasedIndex).thatIsNamed(LowerCaseVariableCatalog.INDEX).isNotBiggerThan(getCount());

    final var internalOneBasedIndex = startIndex + oneBasedIndex - 1;

    return extendedIterable.getStoredAtOneBasedIndex(internalOneBasedIndex);
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
    return //
    ExtendedIterableIntervalViewIterator.forParentContainerAndStartIndexAndEndIndex(
      extendedIterable,
      startIndex,
      endIndex);
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
