/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coretest.container.matrix;

import ch.nolix.core.container.matrix.Matrix;
import ch.nolix.core.container.matrix.MatrixIterator;
import ch.nolix.coreapi.container.iterator.CopyableIterator;
import ch.nolix.coretest.container.base.CopyableIteratorTest;

/**
 * @author Silvan Wyss
 */
final class MatrixIteratorTest extends CopyableIteratorTest {
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
