package ch.nolix.core.container.matrix;

import ch.nolix.core.container.base.CopyableIteratorTest;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;

final class MatrixIteratorTest extends CopyableIteratorTest {

  @Override
  protected <E> CopyableIterator<E> createIteratorForEmptyContainerForType(final Class<E> type) {
    return MatrixIterator.forMatrix(Matrix.createEmpty());
  }

  @Override
  protected <E> CopyableIterator<E> createIteratorForContainerWithElements(
    final E element,
    @SuppressWarnings("unchecked") final E... elements) {

    final Matrix<E> matrix = Matrix.createEmpty();

    matrix.addRow(element, elements);

    return MatrixIterator.forMatrix(matrix);
  }
}
