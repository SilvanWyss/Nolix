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
final class GapMatrixIterator<E> implements CopyableIterator<E> {

  //attribute
  private final GapMatrix<E> parentGapMatrix;

  //attribute
  private int nextElementRowIndex = -1;

  //attribute
  private int nextElementColumnIndex = -1;

  //constructor
  private GapMatrixIterator(final GapMatrix<E> parentGapMatrix) {

    GlobalValidator.assertThat(parentGapMatrix).thatIsNamed("parent GapMatrix").isNotNull();

    this.parentGapMatrix = parentGapMatrix;

    incrementNextElementRowAndColumnIndex();
  }

  //constructor
  private GapMatrixIterator(
    final GapMatrix<E> parentGapMatrix,
    final int p1BasedNextElementRowIndex,
    final int p1BasedNextElementColumnIndex) {
    GlobalValidator.assertThat(parentGapMatrix).thatIsNamed("parent GapMatrix").isNotNull();

    this.parentGapMatrix = parentGapMatrix;
    nextElementColumnIndex = p1BasedNextElementRowIndex;
    nextElementColumnIndex = p1BasedNextElementColumnIndex;
  }

  //static method
  public static <E2> GapMatrixIterator<E2> forGapMatrix(final GapMatrix<E2> gapMatrix) {
    return new GapMatrixIterator<>(gapMatrix);
  }

  //static method
  private static <E2> GapMatrixIterator<E2> forGapMatrixAnd1BasedNextElementRowIndexAndColumnIndex(
    final GapMatrix<E2> gapMatrix,
    final int p1BasedNextElementRowIndex,
    final int p1BasedNextElementColumnIndex) {
    return new GapMatrixIterator<>(gapMatrix, p1BasedNextElementRowIndex, p1BasedNextElementColumnIndex);
  }

  //method
  @Override
  public CopyableIterator<E> getCopy() {
    return forGapMatrixAnd1BasedNextElementRowIndexAndColumnIndex(
      parentGapMatrix,
      nextElementRowIndex,
      nextElementColumnIndex);
  }

  //method
  @Override
  public boolean hasNext() {
    return (nextElementRowIndex != -1);
  }

  //method
  @Override
  public E next() {

    assertHasNextElement();

    return nextWhenHasNext();
  }

  //method
  private void assertHasNextElement() throws NoSuchElementException {
    if (!hasNext()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.NEXT_ELEMENT)
        .toNoSuchElementException();
    }
  }

  //method
  private void incrementNextElementRowAndColumnIndex() {

    nextElementColumnIndex++;

    while (nextElementRowIndex <= parentGapMatrix.getRowCount()) {

      while (nextElementColumnIndex <= parentGapMatrix.getColumnCount()) {

        if (parentGapMatrix.containsAt1BasedRowIndexAndColumnIndex(nextElementRowIndex, nextElementColumnIndex)) {
          return;
        }

        nextElementColumnIndex++;
      }

      nextElementRowIndex++;
      nextElementColumnIndex = 1;
    }

    nextElementRowIndex = -1;
    nextElementColumnIndex = -1;
  }

  //method
  private E nextWhenHasNext() {

    final var element = parentGapMatrix.getStoredAt1BasedRowIndexAndColumnIndex(nextElementRowIndex,
      nextElementColumnIndex);

    incrementNextElementRowAndColumnIndex();

    return element;
  }
}
