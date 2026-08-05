/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.extendediterable;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.copyableiterator.CopyableIterator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ArrayListCreator;
import ch.nolix.baseapi.datastructure.list.IArrayList;
import ch.nolix.baseapi.foundation.marker.Marker;
import ch.nolix.baseapi.generalcatalog.textcatalog.CharacterCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * A {@link ExtendedIterableIntervalView} can iterate over an intervall of a
 * {@link ExtendedIterable}.
 * 
 * @author Silvan Wyss
 * @param <E> the type of the elements of a {@link ExtendedIterableIntervalView}
 */
public final class ExtendedIterableIntervalView<E> extends AbstractExtendedIterable<E> {
  private final ExtendedIterable<E> extendedIterable;

  private final int startIndex;

  private final int endIndex;

  private final ArrayListCreator arrayListCreator;

  /**
   * Creates a new {@link ExtendedIterableIntervalView} for the given
   * extendedIterable and with the given startIndex, endIndex and
   * arrayListCreator.
   * 
   * @param extendedIterable
   * @param startIndex
   * @param endIndex
   * @param arrayListCreator
   * @throws RuntimeException if the given extendedIterable is null
   * @throws RuntimeException if the given startIndex is not positive
   * @throws RuntimeException if the given endIndex is not positive
   * @throws RuntimeException if the given endIndex is smaller than the given
   *                          startIndex
   * @throws RuntimeException if the given endIndex is bigger than the number of
   *                          elements of the given extendedIterable.
   * @throws RuntimeException if the given arrayListCreator is null
   */
  private ExtendedIterableIntervalView(
    final ExtendedIterable<E> extendedIterable,
    final int startIndex,
    final int endIndex,
    final ArrayListCreator arrayListCreator) {
    Validator.assertThat(extendedIterable).thatIsNamed(LowerCaseVariableNameCatalog.CONTAINER).isNotNull();
    Validator.assertThat(startIndex).thatIsNamed(LowerCaseVariableNameCatalog.START_INDEX).isPositive();
    Validator.assertThat(endIndex).thatIsNamed(LowerCaseVariableNameCatalog.END_INDEX).isPositive();
    Validator.assertThat(arrayListCreator).thatIsNamed(ArrayListCreator.class).isNotNull();

    Validator
      .assertThat(endIndex)
      .thatIsNamed(LowerCaseVariableNameCatalog.END_INDEX)
      .isBiggerThanOrEquals(startIndex);

    Validator
      .assertThat(endIndex)
      .thatIsNamed(LowerCaseVariableNameCatalog.END_INDEX)
      .isNotBiggerThan(extendedIterable.getCount());

    this.extendedIterable = extendedIterable;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
    this.arrayListCreator = arrayListCreator;
  }

  /**
   * @param extendedIterable
   * @param startIndex
   * @param endIndex
   * @param arrayListCreator
   * @param <T>              the type of the elements of the created
   *                         {@link ExtendedIterableIntervalView}
   * @return a new {@link ExtendedIterableIntervalView} for the given
   *         extendedIterable and with the given startIndex, endIndex and
   *         arrayListCreator
   * @throws RuntimeException if the given extendedIterable is null
   * @throws RuntimeException if the given startIndex is not positive
   * @throws RuntimeException if the given endIndex is not positive
   * @throws RuntimeException if the given endIndex is smaller than the given
   *                          startIndex
   * @throws RuntimeException if the given endIndex is bigger than the number of
   *                          elements of the given extendedIterable
   * @throws RuntimeException if the given arrayListCreator is null
   */
  public static <T> ExtendedIterableIntervalView<T> forExtendedIterableAndStartIndexAndEndIndexAndArrayListCreator(
    final ExtendedIterable<T> extendedIterable,
    final int startIndex,
    final int endIndex,
    final ArrayListCreator arrayListCreator) {
    return new ExtendedIterableIntervalView<>(extendedIterable, startIndex, endIndex, arrayListCreator);
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
    Validator.assertThat(oneBasedIndex).thatIsNamed(LowerCaseVariableNameCatalog.INDEX).isPositive();
    Validator.assertThat(oneBasedIndex).thatIsNamed(LowerCaseVariableNameCatalog.INDEX).isNotBiggerThan(getCount());

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
    return toStringWithDelimiter(CharacterCatalog.COMMA);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <T> IArrayList<T> createEmptyArrayListFromMarkerWithInitialCapacity(
    final Marker<T> marker,
    final int initialCapacity) {
    return arrayListCreator.createEmptyArrayListFromMarkerWithInitialCapacity(marker, initialCapacity);
  }
}
