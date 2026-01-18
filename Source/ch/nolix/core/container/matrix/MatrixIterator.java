/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.container.matrix;

import java.util.NoSuchElementException;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.iterator.CopyableIterator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of a {@link MatrixIterator}.
 */
public final class MatrixIterator<E> implements CopyableIterator<E> {
  private final Matrix<E> parentMatrix;

  private int nextElementOneBasedIndex;

  /**
   * Creates a new {@link MatrixIterator} with the given parentMatrix.
   * 
   * @param parentMatrix
   * @throws ArgumentIsNullException if the given parentMatrix is null.
   */
  private MatrixIterator(final Matrix<E> parentMatrix) {
    Validator.assertThat(parentMatrix).thatIsNamed("parent Matrix").isNotNull();

    this.parentMatrix = parentMatrix;
    nextElementOneBasedIndex = 1;
  }

  /**
   * Creates a new {@link MatrixIterator} with the given parentMatrix and
   * oneBasedStartIndex.
   * 
   * @param parentMatrix
   * @param oneBasedStartIndex
   * @throws ArgumentIsNullException if the given parentMatrix is null.
   * @throws BiggerArgumentException if the given oneBasedStartIndex is bigger
   *                                 than the element count of the given
   *                                 parentMatrix.
   */
  private MatrixIterator(final Matrix<E> parentMatrix, final int oneBasedStartIndex) {
    Validator.assertThat(parentMatrix).thatIsNamed("parent Matrix").isNotNull();

    Validator
      .assertThat(oneBasedStartIndex)
      .thatIsNamed("start index")
      .isNotBiggerThan(parentMatrix.getCount());

    this.parentMatrix = parentMatrix;
    nextElementOneBasedIndex = oneBasedStartIndex;
  }

  public static <T> MatrixIterator<T> forMatrix(final Matrix<T> matrix) {
    return new MatrixIterator<>(matrix);
  }

  //static mehtod
  public static <T> MatrixIterator<T> forMatrixAndOneBasedStartIndex(
    final Matrix<T> matrix,
    final int oneBasedStartIndex) {
    return new MatrixIterator<>(matrix, oneBasedStartIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> getCopy() {
    return forMatrixAndOneBasedStartIndex(parentMatrix, nextElementOneBasedIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return (nextElementOneBasedIndex <= parentMatrix.getCount());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E next() {
    assertHasNext();

    return nextWhenHasNext();
  }

  /**
   * @throws NoSuchElementException if the current {@link MatrixIterator} does not
   *                                have a next element.
   */
  private void assertHasNext() throws NoSuchElementException {
    if (!hasNext()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.NEXT_ELEMENT)
        .toNoSuchElementException();
    }
  }

  /**
   * @return the next element of the current {@link MatrixIterator} for the case
   *         when the current {@link MatrixIterator} has a next element.
   */
  private E nextWhenHasNext() {
    final var element = parentMatrix.getStoredAtOneBasedIndex(nextElementOneBasedIndex);

    nextElementOneBasedIndex++;

    return element;
  }
}
