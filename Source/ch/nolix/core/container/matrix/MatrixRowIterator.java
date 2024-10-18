package ch.nolix.core.container.matrix;

import java.util.NoSuchElementException;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

final class MatrixRowIterator<E> implements CopyableIterator<E> {

  private final MatrixRow<E> parentMatrixRow;

  private int nextElement1BasedColumnIndex;

  private MatrixRowIterator(final MatrixRow<E> parentMatrixRow) {

    GlobalValidator.assertThat(parentMatrixRow).thatIsNamed("parent MatrixRow").isNotNull();

    this.parentMatrixRow = parentMatrixRow;
    nextElement1BasedColumnIndex = 1;
  }

  private MatrixRowIterator(final MatrixRow<E> parentMatrixRow, final int nextElement1BasedColumnIndex) {

    GlobalValidator.assertThat(parentMatrixRow).thatIsNamed("parent MatrixRow").isNotNull();

    GlobalValidator
      .assertThat(nextElement1BasedColumnIndex)
      .thatIsNamed("next element 1-based column index")
      .isPositive();

    this.parentMatrixRow = parentMatrixRow;
    this.nextElement1BasedColumnIndex = nextElement1BasedColumnIndex;
  }

  public static <E2> MatrixRowIterator<E2> forMatrixRow(final MatrixRow<E2> matrixRow) {
    return new MatrixRowIterator<>(matrixRow);
  }

  @Override
  public CopyableIterator<E> getCopy() {
    return new MatrixRowIterator<>(parentMatrixRow, nextElement1BasedColumnIndex);
  }

  @Override
  public boolean hasNext() {
    return (nextElement1BasedColumnIndex <= parentMatrixRow.getCount());
  }

  @Override
  public E next() {

    assertHasNext();

    return nextWhenHasNext();
  }

  private void assertHasNext() throws NoSuchElementException {
    if (!hasNext()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.NEXT_ELEMENT)
        .toNoSuchElementException();
    }
  }

  private E nextWhenHasNext() {

    final var element = parentMatrixRow.getStoredAt1BasedIndex(nextElement1BasedColumnIndex);

    nextElement1BasedColumnIndex++;

    return element;
  }
}
