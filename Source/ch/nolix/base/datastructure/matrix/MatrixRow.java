/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.matrix;

import ch.nolix.base.datastructure.arraylist.ArrayList;
import ch.nolix.base.datastructure.extendediterable.AbstractExtendedIterable;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.copyableiterator.CopyableIterator;
import ch.nolix.baseapi.datastructure.list.IArrayList;
import ch.nolix.baseapi.datastructure.matrix.IMatrixRow;
import ch.nolix.baseapi.foundation.marker.Marker;
import ch.nolix.baseapi.generalcatalog.textcatalog.CharacterCatalog;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of the parent {@link MutableMatrix} of a
 *            {@link MatrixRow}.
 */
public final class MatrixRow<E> extends AbstractExtendedIterable<E> implements IMatrixRow<E> {
  private final MutableMatrix<E> parentMatrix;

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
  private MatrixRow(final MutableMatrix<E> parentMatrix, final int oneBasedRowIndex) {
    Validator.assertThat(parentMatrix).thatIsNamed("parent matrix").isNotNull();
    Validator.assertThat(oneBasedRowIndex).thatIsNamed("one based row index").isBetween(1, parentMatrix.getRowCount());

    this.parentMatrix = parentMatrix;
    this.oneBasedRowIndex = oneBasedRowIndex;
  }

  /**
   * @param parentMatrix
   * @param oneBasedRowIndex
   * @param <T>              the type of the elements of the parent {@link MutableMatrix}
   *                         of the created {@link MatrixRow}
   * @return a new {@link MatrixRow} for the given parentMatrix and
   *         oneBasedRowIndex
   * @throws RuntimeException if the given oneBasedRowIndex is not positive or
   *                          bigger than the number of rows of the given
   *                          parentMatrix.
   */
  public static <T> MatrixRow<T> forMatrixAndOneBasedRowIndex(final MutableMatrix<T> parentMatrix,
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
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {
    return parentMatrix.getStoredAtOneBasedRowIndexAndColumnIndex(getOneBasedRowIndex(), oneBasedIndex);
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
    return toStringWithDelimiter(CharacterCatalog.COMMA);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <T> IArrayList<T> createEmptyArrayListFromMarkerWithInitialCapacity(
    final Marker<T> marker,
    final int initialCapacity) {
    return ArrayList.withInitialCapacity(initialCapacity);
  }
}
