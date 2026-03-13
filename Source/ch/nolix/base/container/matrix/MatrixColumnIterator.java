/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.container.matrix;

import java.util.NoSuchElementException;

import ch.nolix.base.errorcontrol.validator.Validator;
import ch.nolix.baseapi.container.iterator.CopyableIterator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;

final class MatrixColumnIterator<E> implements CopyableIterator<E> {
  private final MatrixColumn<E> parentMatrixColumn;

  private int nextElementOneBasedRowIndex = 1;

  private MatrixColumnIterator(final MatrixColumn<E> parentMatrixColumn) {
    Validator.assertThat(parentMatrixColumn).thatIsNamed("parent MatrixColumn").isNotNull();

    this.parentMatrixColumn = parentMatrixColumn;
  }

  private MatrixColumnIterator(final MatrixColumn<E> parentMatrixColumn, final int nextElementOneBasedRowIndex) {
    Validator.assertThat(parentMatrixColumn).thatIsNamed("parent MatrixColumn").isNotNull();

    Validator
      .assertThat(nextElementOneBasedRowIndex)
      .thatIsNamed("next element 1-based row index")
      .isPositive();

    this.parentMatrixColumn = parentMatrixColumn;
    this.nextElementOneBasedRowIndex = nextElementOneBasedRowIndex;
  }

  public static <T> MatrixColumnIterator<T> forMatrixColumn(final MatrixColumn<T> matrixColumn) {
    return new MatrixColumnIterator<>(matrixColumn);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> getCopy() {
    return new MatrixColumnIterator<>(parentMatrixColumn, nextElementOneBasedRowIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return (nextElementOneBasedRowIndex <= parentMatrixColumn.getCount());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E next() {
    assertHasNext();

    return nextWhenHasNext();
  }

  private void assertHasNext() throws NoSuchElementException {
    if (!hasNext()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.NEXT_ELEMENT)
        .toNoSuchElementException();
    }
  }

  private E nextWhenHasNext() {
    final var element = parentMatrixColumn.getStoredAtOneBasedIndex(nextElementOneBasedRowIndex);

    nextElementOneBasedRowIndex++;

    return element;
  }
}
