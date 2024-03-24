//package declaration
package ch.nolix.core.container.matrix;

//Java imports
import java.util.NoSuchElementException;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
final class MatrixColumnIterator<E> implements CopyableIterator<E> {

  //attribute
  private final MatrixColumn<E> parentMatrixColumn;

  //attribute
  private int nextElement1BasedRowIndex = 1;

  //constructor
  private MatrixColumnIterator(final MatrixColumn<E> parentMatrixColumn) {

    GlobalValidator.assertThat(parentMatrixColumn).thatIsNamed("parent MatrixColumn").isNotNull();

    this.parentMatrixColumn = parentMatrixColumn;
  }

  //constructor
  private MatrixColumnIterator(final MatrixColumn<E> parentMatrixColumn, final int nextElement1BasedRowIndex) {

    GlobalValidator.assertThat(parentMatrixColumn).thatIsNamed("parent MatrixColumn").isNotNull();

    GlobalValidator
      .assertThat(nextElement1BasedRowIndex)
      .thatIsNamed("next element 1-based row index")
      .isPositive();

    this.parentMatrixColumn = parentMatrixColumn;
    this.nextElement1BasedRowIndex = nextElement1BasedRowIndex;
  }

  //static method
  public static <E2> MatrixColumnIterator<E2> forMatrixColumn(final MatrixColumn<E2> matrixColumn) {
    return new MatrixColumnIterator<>(matrixColumn);
  }

  //method
  @Override
  public CopyableIterator<E> getCopy() {
    return new MatrixColumnIterator<>(parentMatrixColumn, nextElement1BasedRowIndex);
  }

  //method
  @Override
  public boolean hasNext() {
    return (nextElement1BasedRowIndex <= parentMatrixColumn.getCount());
  }

  //method
  @Override
  public E next() {

    assertHasNext();

    return nextWhenHasNext();
  }

  //method
  private void assertHasNext() throws NoSuchElementException {
    if (!hasNext()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.NEXT_ELEMENT)
        .toNoSuchElementException();
    }
  }

  //method
  private E nextWhenHasNext() {

    final var element = parentMatrixColumn.getStoredAt1BasedIndex(nextElement1BasedRowIndex);

    nextElement1BasedRowIndex++;

    return element;
  }
}
