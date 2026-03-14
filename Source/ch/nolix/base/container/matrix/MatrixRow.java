/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.container.matrix;

import ch.nolix.base.container.arraylist.AbstractExtendedContainer;
import ch.nolix.base.errorcontrol.validator.Validator;
import ch.nolix.baseapi.commontypetool.charactertool.CharacterCatalog;
import ch.nolix.baseapi.container.iterator.CopyableIterator;
import ch.nolix.baseapi.container.matrix.IMatrixRow;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of the parent {@link Matrix} of a
 *            {@link MatrixRow}.
 */
public final class MatrixRow<E> extends AbstractExtendedContainer<E> implements IMatrixRow<E> {
  private final Matrix<E> parentMatrix;

  private final int oneBasedRowIndex;

  /**
   * Create a new {@link MatrixRow} for the given parentMatrix and
   * oneBasedRowIndex.
   * 
   * @param parentMatrix
   * @param oneBasedRowIndex
   * @throws RuntimeException if the given oneBasedRowIndex is not positive or
   *                          bigger than the number of rows of the given
   *                          parentMatrix.
   */
  private MatrixRow(final Matrix<E> parentMatrix, final int oneBasedRowIndex) {
    Validator.assertThat(parentMatrix).thatIsNamed("parent matrix").isNotNull();
    Validator.assertThat(oneBasedRowIndex).thatIsNamed("one based row index").isBetween(1, parentMatrix.getRowCount());

    this.parentMatrix = parentMatrix;
    this.oneBasedRowIndex = oneBasedRowIndex;
  }

  /**
   * @param parentMatrix
   * @param oneBasedRowIndex
   * @param <T>              is the type of the elements of the parent
   *                         {@link Matrix} of the created {@link MatrixRow}.
   * @return a new {@link MatrixRow} for the given parentMatrix and
   *         oneBasedRowIndex.
   * @throws RuntimeException if the given oneBasedRowIndex is not positive or
   *                          bigger than the number of rows of the given
   *                          parentMatrix.
   */
  public static <T> MatrixRow<T> forMatrixAndOneBasedRowIndex(final Matrix<T> parentMatrix,
    final int oneBasedRowIndex) {
    return new MatrixRow<>(parentMatrix, oneBasedRowIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return parentMatrix.getColumnCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getOneBasedRowIndex() {
    return oneBasedRowIndex;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int columnIndex) {
    return parentMatrix.getStoredAtOneBasedRowIndexAndColumnIndex(getOneBasedRowIndex(), columnIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return MatrixRowIterator.forMatrixRow(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalog.COMMA);
  }
}
