/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.datastructure.matrix;

import ch.nolix.base.datastructure.matrix.MatrixIterator;
import ch.nolix.base.datastructure.matrix.MutableMatrix;
import ch.nolix.baseapi.datastructure.copyableiterator.CopyableIterator;
import ch.nolix.basetest.datastructure.iterator.CopyableIteratorTest;

/**
 * @author Silvan Wyss
 */
final class MatrixIteratorTest extends CopyableIteratorTest {
  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> CopyableIterator<E> createIteratorForEmptyContainerForType(final Class<E> type) {
    return MatrixIterator.forMatrix(MutableMatrix.createEmpty());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> CopyableIterator<E> createIteratorForContainerWithElements(
    @SuppressWarnings("unchecked") final E... elements) {
    final MutableMatrix<E> matrix = MutableMatrix.createEmpty();

    matrix.addRow(elements);

    return MatrixIterator.forMatrix(matrix);
  }
}
