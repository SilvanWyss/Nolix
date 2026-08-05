/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.extendediterable;

import java.util.Iterator;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.copyableiterator.CopyableIterator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of a
 *            {@link ExtendedIterableIntervalViewIterator}
 */
final class ExtendedIterableIntervalViewIterator<E> implements CopyableIterator<E> {
  private final ExtendedIterable<E> parentContainer;

  private final int endIndex;

  private final Iterator<E> iterator;

  private int currentIndex;

  /**
   * Creates a new {@link ExtendedIterableIntervalViewIterator} for the given
   * parentContainer, startIndex and endIndex.
   * 
   * @param parentContainer
   * @param startIndex
   * @param endIndex
   * @throws RuntimeException if the given parentContainer is null
   * @throws RuntimeException if the given startIndex is not positive
   * @throws RuntimeException if the given endIndex is not positive
   * @throws RuntimeException if the given endIndex is not bigger than the given
   *                          startIndex or does not equal the given startIndex.
   */
  private ExtendedIterableIntervalViewIterator(
    final ExtendedIterable<E> parentContainer,
    final int startIndex,
    final int endIndex) {
    Validator.assertThat(parentContainer).thatIsNamed("parent container").isNotNull();
    Validator.assertThat(startIndex).thatIsNamed(LowerCaseVariableNameCatalog.START_INDEX).isPositive();
    Validator.assertThat(endIndex).thatIsNamed(LowerCaseVariableNameCatalog.END_INDEX).isPositive();
    Validator.assertThat(endIndex).thatIsNamed(LowerCaseVariableNameCatalog.END_INDEX).isBiggerThanOrEquals(startIndex);

    this.parentContainer = parentContainer;
    this.endIndex = endIndex;
    this.currentIndex = startIndex;
    this.iterator = parentContainer.iterator();

    for (var i = 1; i < startIndex; i++) {
      iterator.next();
    }
  }

  /**
   * @param parentContainer
   * @param startIndex
   * @param endIndex
   * @param <T>             the type of the elements of the created
   *                        {@link ExtendedIterableIntervalViewIterator}
   * @return a new {@link ExtendedIterableIntervalViewIterator} for the given
   *         parentContainer, startIndex and endIndex
   * @throws RuntimeException if the given parentContainer is null
   * @throws RuntimeException if the given startIndex is not positive
   * @throws RuntimeException if the given endIndex is not positive
   * @throws RuntimeException if the given endIndex is not bigger than the given
   *                          startIndex or does not equal the given startIndex.
   */
  public static <T> ExtendedIterableIntervalViewIterator<T> forParentContainerAndStartIndexAndEndIndex(
    final ExtendedIterable<T> parentContainer,
    final int startIndex,
    final int endIndex) {
    return new ExtendedIterableIntervalViewIterator<>(parentContainer, startIndex, endIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> getCopy() {
    return new ExtendedIterableIntervalViewIterator<>(parentContainer, currentIndex, endIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return //
    iterator.hasNext()
    && currentIndex <= endIndex;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E next() {
    if (!hasNext()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(
        this,
        LowerCaseVariableNameCatalog.NEXT_ELEMENT);
    }

    currentIndex++;

    return iterator.next();
  }
}
