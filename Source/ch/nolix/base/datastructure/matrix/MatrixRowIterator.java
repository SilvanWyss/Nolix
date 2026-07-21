/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.matrix;

import java.util.NoSuchElementException;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.copyableiterator.CopyableIterator;
import ch.nolix.baseapi.datastructure.matrix.IMatrixRow;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

final class MatrixRowIterator<E> implements CopyableIterator<E> {
  private final IMatrixRow<E> parentMatrixRow;

  private int nextElementOneBasedColumnIndex;

  private MatrixRowIterator(final IMatrixRow<E> parentMatrixRow) {
    Validator.assertThat(parentMatrixRow).thatIsNamed("parent MatrixRow").isNotNull();

    this.parentMatrixRow = parentMatrixRow;
    nextElementOneBasedColumnIndex = 1;
  }

  private MatrixRowIterator(final IMatrixRow<E> parentMatrixRow, final int nextElementOneBasedColumnIndex) {
    Validator.assertThat(parentMatrixRow).thatIsNamed("parent MatrixRow").isNotNull();

    Validator
      .assertThat(nextElementOneBasedColumnIndex)
      .thatIsNamed("next element 1-based column index")
      .isPositive();

    this.parentMatrixRow = parentMatrixRow;
    this.nextElementOneBasedColumnIndex = nextElementOneBasedColumnIndex;
  }

  public static <T> MatrixRowIterator<T> forMatrixRow(final MatrixRow<T> matrixRow) {
    return new MatrixRowIterator<>(matrixRow);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> getCopy() {
    return new MatrixRowIterator<>(parentMatrixRow, nextElementOneBasedColumnIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return (nextElementOneBasedColumnIndex <= parentMatrixRow.getCount());
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
    final var element = parentMatrixRow.getStoredAtOneBasedIndex(nextElementOneBasedColumnIndex);

    nextElementOneBasedColumnIndex++;

    return element;
  }
}
