package ch.nolix.core.container.matrix;

import java.util.NoSuchElementException;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

final class MatrixColumnIterator<E> implements CopyableIterator<E> {

  private final MatrixColumn<E> parentMatrixColumn;

  private int nextElement1BasedRowIndex = 1;

  private MatrixColumnIterator(final MatrixColumn<E> parentMatrixColumn) {

    GlobalValidator.assertThat(parentMatrixColumn).thatIsNamed("parent MatrixColumn").isNotNull();

    this.parentMatrixColumn = parentMatrixColumn;
  }

  private MatrixColumnIterator(final MatrixColumn<E> parentMatrixColumn, final int nextElement1BasedRowIndex) {

    GlobalValidator.assertThat(parentMatrixColumn).thatIsNamed("parent MatrixColumn").isNotNull();

    GlobalValidator
      .assertThat(nextElement1BasedRowIndex)
      .thatIsNamed("next element 1-based row index")
      .isPositive();

    this.parentMatrixColumn = parentMatrixColumn;
    this.nextElement1BasedRowIndex = nextElement1BasedRowIndex;
  }

  public static <E2> MatrixColumnIterator<E2> forMatrixColumn(final MatrixColumn<E2> matrixColumn) {
    return new MatrixColumnIterator<>(matrixColumn);
  }

  @Override
  public CopyableIterator<E> createCopy() {
    return new MatrixColumnIterator<>(parentMatrixColumn, nextElement1BasedRowIndex);
  }

  @Override
  public boolean hasNext() {
    return (nextElement1BasedRowIndex <= parentMatrixColumn.getCount());
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

    final var element = parentMatrixColumn.getStoredAt1BasedIndex(nextElement1BasedRowIndex);

    nextElement1BasedRowIndex++;

    return element;
  }
}
