package ch.nolix.core.container.matrix;

import java.util.NoSuchElementException;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.iterator.CopyableIterator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

final class MatrixRowIterator<E> implements CopyableIterator<E> {
  private final MatrixRow<E> parentMatrixRow;

  private int nextElementOneBasedColumnIndex;

  private MatrixRowIterator(final MatrixRow<E> parentMatrixRow) {
    Validator.assertThat(parentMatrixRow).thatIsNamed("parent MatrixRow").isNotNull();

    this.parentMatrixRow = parentMatrixRow;
    nextElementOneBasedColumnIndex = 1;
  }

  private MatrixRowIterator(final MatrixRow<E> parentMatrixRow, final int nextElementOneBasedColumnIndex) {
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

  @Override
  public CopyableIterator<E> getCopy() {
    return new MatrixRowIterator<>(parentMatrixRow, nextElementOneBasedColumnIndex);
  }

  @Override
  public boolean hasNext() {
    return (nextElementOneBasedColumnIndex <= parentMatrixRow.getCount());
  }

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
    final var element = parentMatrixRow.getStoredAtOneBasedIndex(nextElementOneBasedColumnIndex);

    nextElementOneBasedColumnIndex++;

    return element;
  }
}
