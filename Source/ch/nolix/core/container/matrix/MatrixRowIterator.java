//package declaration
package ch.nolix.core.container.matrix;

//Java imports
import java.util.NoSuchElementException;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;

//class
final class MatrixRowIterator<E> implements CopyableIterator<E> {

  //attribute
  private final MatrixRow<E> parentMatrixRow;

  //attribute
  private int nextElement1BasedColumnIndex;

  //constructor
  private MatrixRowIterator(final MatrixRow<E> parentMatrixRow) {

    GlobalValidator.assertThat(parentMatrixRow).thatIsNamed("parent MatrixRow").isNotNull();

    this.parentMatrixRow = parentMatrixRow;
    nextElement1BasedColumnIndex = 1;
  }

  //constructor
  private MatrixRowIterator(final MatrixRow<E> parentMatrixRow, final int nextElement1BasedColumnIndex) {

    GlobalValidator.assertThat(parentMatrixRow).thatIsNamed("parent MatrixRow").isNotNull();

    GlobalValidator
      .assertThat(nextElement1BasedColumnIndex)
      .thatIsNamed("next element 1-based column index")
      .isPositive();

    this.parentMatrixRow = parentMatrixRow;
    this.nextElement1BasedColumnIndex = nextElement1BasedColumnIndex;
  }

  //static method
  public static <E2> MatrixRowIterator<E2> forMatrixRow(final MatrixRow<E2> matrixRow) {
    return new MatrixRowIterator<>(matrixRow);
  }

  //method
  @Override
  public CopyableIterator<E> getCopy() {
    return new MatrixRowIterator<>(parentMatrixRow, nextElement1BasedColumnIndex);
  }

  //method
  @Override
  public boolean hasNext() {
    return (nextElement1BasedColumnIndex <= parentMatrixRow.getElementCount());
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
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.NEXT_ELEMENT)
        .toNoSuchElementException();
    }
  }

  //method
  private E nextWhenHasNext() {

    final var element = parentMatrixRow.getStoredAt1BasedIndex(nextElement1BasedColumnIndex);

    nextElement1BasedColumnIndex++;

    return element;
  }
}
