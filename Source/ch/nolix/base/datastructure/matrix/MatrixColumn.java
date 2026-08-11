/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.matrix;

import ch.nolix.base.datastructure.arraylist.ArrayList;
import ch.nolix.base.datastructure.extendediterable.AbstractExtendedIterable;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.copyableiterator.CopyableIterator;
import ch.nolix.baseapi.datastructure.list.IArrayList;
import ch.nolix.baseapi.datastructure.matrix.IMatrixColumn;
import ch.nolix.baseapi.foundation.marker.Marker;
import ch.nolix.baseapi.generalcatalog.textcatalog.CharacterCatalog;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of the parent {@link MutableMatrix} of a
 *            {@link MatrixColumn}.
 */
public final class MatrixColumn<E> extends AbstractExtendedIterable<E> implements IMatrixColumn<E> {
  private final MutableMatrix<E> parentMatrix;

  private final int oneBasedColumnIndex;

  /**
   * Create a new {@link MatrixColumn} for the given parentMatrix and
   * oneBasedColumnIndex.
   * 
   * @param parentMatrix
   * @param oneBasedColumnIndex
   * @throws RuntimeException if the given oneBasedColumnIndex is not positive or
   *                          bigger than the number of columns of the given
   *                          parentMatrix.
   */
  private MatrixColumn(final MutableMatrix<E> parentMatrix, final int oneBasedColumnIndex) {
    Validator.assertThat(parentMatrix).thatIsNamed("parent matrix").isNotNull();

    Validator
      .assertThat(oneBasedColumnIndex)
      .thatIsNamed("one based column index")
      .isBetween(1, parentMatrix.getColumnCount());

    this.parentMatrix = parentMatrix;
    this.oneBasedColumnIndex = oneBasedColumnIndex;
  }

  /**
   * @param parentMatrix
   * @param oneBasedColumnIndex
   * @param <T>                 the type of the elements of the parent
   *                            {@link MutableMatrix} of the created {@link MatrixColumn}
   * @return a new {@link MatrixColumn} for the given parentMatrix and
   *         oneBasedColumnIndex
   * @throws RuntimeException if the given oneBasedColumnIndex is not positive or
   *                          bigger than the number of columns of the given
   *                          parentMatrix.
   */
  public static <T> MatrixColumn<T> forMatrixAndOneBasedColumnIndex(final MutableMatrix<T> parentMatrix,
    final int oneBasedColumnIndex) {
    return new MatrixColumn<>(parentMatrix, oneBasedColumnIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return parentMatrix.getRowCount();
  }

  @Override
  public int getOneBasedColumnIndex() {
    return oneBasedColumnIndex;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {
    return parentMatrix.getStoredAtOneBasedRowIndexAndColumnIndex(oneBasedIndex, getOneBasedColumnIndex());
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
    return MatrixColumnIterator.forMatrixColumn(this);
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
