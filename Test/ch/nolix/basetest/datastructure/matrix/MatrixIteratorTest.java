/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.datastructure.matrix;

import ch.nolix.base.datastructure.matrix.Matrix;
import ch.nolix.base.datastructure.matrix.MatrixIterator;
import ch.nolix.baseapi.datastructure.iterator.CopyableIterator;
import ch.nolix.basetest.datastructure.extendediterable.CopyableIteratorTest;

/**
 * @author Silvan Wyss
 */
final class MatrixIteratorTest extends CopyableIteratorTest {
  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> CopyableIterator<E> createIteratorForEmptyContainerForType(final Class<E> type) {
    return MatrixIterator.forMatrix(Matrix.createEmpty());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> CopyableIterator<E> createIteratorForContainerWithElements(
    @SuppressWarnings("unchecked") final E... elements) {
    final Matrix<E> matrix = Matrix.createEmpty();

    matrix.addRow(elements);

    return MatrixIterator.forMatrix(matrix);
  }
}
