package ch.nolix.core.container.gapmatrix;

import java.util.NoSuchElementException;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

final class GapMatrixIterator<E> implements CopyableIterator<E> {

  private final GapMatrix<E> parentGapMatrix;

  private int nextElementRowIndex = -1;

  private int nextElementColumnIndex = -1;

  private GapMatrixIterator(final GapMatrix<E> parentGapMatrix) {

    GlobalValidator.assertThat(parentGapMatrix).thatIsNamed("parent GapMatrix").isNotNull();

    this.parentGapMatrix = parentGapMatrix;

    incrementNextElementRowAndColumnIndex();
  }

  private GapMatrixIterator(
    final GapMatrix<E> parentGapMatrix,
    final int param1BasedNextElementRowIndex,
    final int param1BasedNextElementColumnIndex) {
    GlobalValidator.assertThat(parentGapMatrix).thatIsNamed("parent GapMatrix").isNotNull();

    this.parentGapMatrix = parentGapMatrix;
    nextElementColumnIndex = param1BasedNextElementRowIndex;
    nextElementColumnIndex = param1BasedNextElementColumnIndex;
  }

  public static <E2> GapMatrixIterator<E2> forGapMatrix(final GapMatrix<E2> gapMatrix) {
    return new GapMatrixIterator<>(gapMatrix);
  }

  private static <E2> GapMatrixIterator<E2> forGapMatrixAnd1BasedNextElementRowIndexAndColumnIndex(
    final GapMatrix<E2> gapMatrix,
    final int param1BasedNextElementRowIndex,
    final int param1BasedNextElementColumnIndex) {
    return new GapMatrixIterator<>(gapMatrix, param1BasedNextElementRowIndex, param1BasedNextElementColumnIndex);
  }

  @Override
  public CopyableIterator<E> getCopy() {
    return forGapMatrixAnd1BasedNextElementRowIndexAndColumnIndex(
      parentGapMatrix,
      nextElementRowIndex,
      nextElementColumnIndex);
  }

  @Override
  public boolean hasNext() {
    return (nextElementRowIndex != -1);
  }

  @Override
  public E next() {

    assertHasNextElement();

    return nextWhenHasNext();
  }

  private void assertHasNextElement() throws NoSuchElementException {
    if (!hasNext()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.NEXT_ELEMENT)
        .toNoSuchElementException();
    }
  }

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

  private E nextWhenHasNext() {

    final var element = parentGapMatrix.getStoredAt1BasedRowIndexAndColumnIndex(nextElementRowIndex,
      nextElementColumnIndex);

    incrementNextElementRowAndColumnIndex();

    return element;
  }
}
