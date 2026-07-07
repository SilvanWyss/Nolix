/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.matrix;

import java.util.NoSuchElementException;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.iterator.CopyableIterator;
import ch.nolix.baseapi.datastructure.matrix.IMatrixColumn;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

final class MatrixColumnIterator<E> implements CopyableIterator<E> {
  private final IMatrixColumn<E> parentMatrixColumn;

  private int nextElementOneBasedRowIndex = 1;

  private MatrixColumnIterator(final MatrixColumn<E> parentMatrixColumn) {
    Validator.assertThat(parentMatrixColumn).thatIsNamed("parent MatrixColumn").isNotNull();

    this.parentMatrixColumn = parentMatrixColumn;
  }

  private MatrixColumnIterator(final IMatrixColumn<E> parentMatrixColumn, final int nextElementOneBasedRowIndex) {
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
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.NEXT_ELEMENT)
        .toNoSuchElementException();
    }
  }

  private E nextWhenHasNext() {
    final var element = parentMatrixColumn.getStoredAtOneBasedIndex(nextElementOneBasedRowIndex);

    nextElementOneBasedRowIndex++;

    return element;
  }
}
