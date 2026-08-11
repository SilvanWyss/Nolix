/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.matrix;

import java.util.NoSuchElementException;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.copyableiterator.CopyableIterator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of a {@link MatrixIterator}.
 */
public final class MatrixIterator<E> implements CopyableIterator<E> {
  private final MutableMatrix<E> parentMatrix;

  private int nextElementOneBasedIndex;

  /**
   * Creates a new {@link MatrixIterator} with the given parentMatrix.
   * 
   * @param parentMatrix
   * @throws RuntimeException if the given parentMatrix is null
   */
  private MatrixIterator(final MutableMatrix<E> parentMatrix) {
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
   * @throws RuntimeException if the given parentMatrix is null
   * @throws RuntimeException if the given oneBasedStartIndex is bigger than the
   *                          element count of the given parentMatrix.
   */
  private MatrixIterator(final MutableMatrix<E> parentMatrix, final int oneBasedStartIndex) {
    Validator.assertThat(parentMatrix).thatIsNamed("parent Matrix").isNotNull();

    Validator
      .assertThat(oneBasedStartIndex)
      .thatIsNamed("start index")
      .isNotBiggerThan(parentMatrix.getCount());

    this.parentMatrix = parentMatrix;
    nextElementOneBasedIndex = oneBasedStartIndex;
  }

  public static <T> MatrixIterator<T> forMatrix(final MutableMatrix<T> matrix) {
    return new MatrixIterator<>(matrix);
  }

  // static mehtod
  public static <T> MatrixIterator<T> forMatrixAndOneBasedStartIndex(
    final MutableMatrix<T> matrix,
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
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.NEXT_ELEMENT)
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
