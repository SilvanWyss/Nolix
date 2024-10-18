package ch.nolix.core.container.matrix;

import java.util.NoSuchElementException;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

/**
 * @author Silvan Wyss
 * @version 2016-08-01
 * @param <E> is the type of the elements of a {@link MatrixIterator}.
 */
final class MatrixIterator<E> implements CopyableIterator<E> {

  private final Matrix<E> parentMatrix;

  private int nextElement1BasedIndex;

  /**
   * Creates a new {@link MatrixIterator} with the given parentMatrix.
   * 
   * @param parentMatrix
   * @throws ArgumentIsNullException if the given parentMatrix is null.
   */
  private MatrixIterator(final Matrix<E> parentMatrix) {

    GlobalValidator.assertThat(parentMatrix).thatIsNamed("parent Matrix").isNotNull();

    this.parentMatrix = parentMatrix;
    nextElement1BasedIndex = 1;
  }

  /**
   * Creates a new {@link MatrixIterator} with the given parentMatrix and
   * param1BasedStartIndex.
   * 
   * @param parentMatrix
   * @param param1BasedStartIndex
   * @throws ArgumentIsNullException if the given parentMatrix is null.
   * @throws BiggerArgumentException if the given param1BasedStartIndex is bigger
   *                                 than the element count of the given
   *                                 parentMatrix.
   */
  private MatrixIterator(final Matrix<E> parentMatrix, final int param1BasedStartIndex) {

    GlobalValidator.assertThat(parentMatrix).thatIsNamed("parent Matrix").isNotNull();

    GlobalValidator
      .assertThat(param1BasedStartIndex)
      .thatIsNamed("start index")
      .isNotBiggerThan(parentMatrix.getCount());

    this.parentMatrix = parentMatrix;
    nextElement1BasedIndex = param1BasedStartIndex;
  }

  public static <E2> MatrixIterator<E2> forMatrix(final Matrix<E2> matrix) {
    return new MatrixIterator<>(matrix);
  }

  //static mehtod
  public static <E2> MatrixIterator<E2> forMatrixAnd1BasedStartIndex(
    final Matrix<E2> matrix,
    final int param1BasedStartIndex) {
    return new MatrixIterator<>(matrix, param1BasedStartIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> getCopy() {
    return forMatrixAnd1BasedStartIndex(parentMatrix, nextElement1BasedIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return (nextElement1BasedIndex <= parentMatrix.getCount());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E next() {

    assertHasNext();

    return nextWhenHasNext();
  }

  /**
   * @throws NoSuchElementException if the current {@link MatrixIterator} does not
   *                                have a next element.
   */
  private void assertHasNext() throws NoSuchElementException {
    if (!hasNext()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.NEXT_ELEMENT)
        .toNoSuchElementException();
    }
  }

  /**
   * @return the next element of the current {@link MatrixIterator} for the case
   *         when the current {@link MatrixIterator} has a next element.
   */
  private E nextWhenHasNext() {

    final var element = parentMatrix.getStoredAt1BasedIndex(nextElement1BasedIndex);

    nextElement1BasedIndex++;

    return element;
  }
}
