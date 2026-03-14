/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.container.arraylist;

import java.util.Iterator;

import ch.nolix.base.errorcontrol.validator.Validator;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.container.iterator.CopyableIterator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of a
 *            {@link IntervallContainerViewIterator}.
 */
final class IntervallContainerViewIterator<E> implements CopyableIterator<E> {
  private final IContainer<E> parentContainer;

  private final int endIndex;

  private final Iterator<E> iterator;

  private int currentIndex;

  /**
   * Creates a new {@link IntervallContainerViewIterator} for the given
   * parentContainer, startIndex and endIndex.
   * 
   * @param parentContainer
   * @param startIndex
   * @param endIndex
   * @throws RuntimeException      if the given parentContainer is null.
   * @throws RuntimeException if the given startIndex is not positive.
   * @throws RuntimeException if the given endIndex is not positive.
   * @throws RuntimeException     if the given endIndex is not bigger than
   *                                      the given startIndex or does not equal
   *                                      the given startIndex.
   */
  private IntervallContainerViewIterator(
    final IContainer<E> parentContainer,
    final int startIndex,
    final int endIndex) {
    Validator.assertThat(parentContainer).thatIsNamed("parent container").isNotNull();
    Validator.assertThat(startIndex).thatIsNamed(LowerCaseVariableCatalog.START_INDEX).isPositive();
    Validator.assertThat(endIndex).thatIsNamed(LowerCaseVariableCatalog.END_INDEX).isPositive();
    Validator.assertThat(endIndex).thatIsNamed(LowerCaseVariableCatalog.END_INDEX).isBiggerThanOrEquals(startIndex);

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
   * @param <T>             is the type of the elements of the created
   *                        {@link IntervallContainerViewIterator}.
   * @return a new {@link IntervallContainerViewIterator} for the given
   *         parentContainer, startIndex and endIndex.
   * @throws RuntimeException      if the given parentContainer is null.
   * @throws RuntimeException if the given startIndex is not positive.
   * @throws RuntimeException if the given endIndex is not positive.
   * @throws RuntimeException     if the given endIndex is not bigger than
   *                                      the given startIndex or does not equal
   *                                      the given startIndex.
   */
  public static <T> IntervallContainerViewIterator<T> forParentContainerAndStartIndexAndEndIndex(
    final IContainer<T> parentContainer,
    final int startIndex,
    final int endIndex) {
    return new IntervallContainerViewIterator<>(parentContainer, startIndex, endIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> getCopy() {
    return new IntervallContainerViewIterator<>(parentContainer, currentIndex, endIndex);
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
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.NEXT_ELEMENT);
    }

    currentIndex++;

    return iterator.next();
  }
}
