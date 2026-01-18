/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.container.arraylist;

import java.util.Iterator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.iterator.CopyableIterator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

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
   * @throws ArgumentIsNullException      if the given parentContainer is null.
   * @throws NonPositiveArgumentException if the given startIndex is not positive.
   * @throws NonPositiveArgumentException if the given endIndex is not positive.
   * @throws SmallerArgumentException     if the given endIndex is not bigger than
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
   * @throws ArgumentIsNullException      if the given parentContainer is null.
   * @throws NonPositiveArgumentException if the given startIndex is not positive.
   * @throws NonPositiveArgumentException if the given endIndex is not positive.
   * @throws SmallerArgumentException     if the given endIndex is not bigger than
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
