/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.matrix;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalstate.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of a {@link IMatrix}.
 */
public interface IMatrix<E> extends Clearable, ExtendedIterable<E> {
  /**
   * @return the number of columns of the current {@link IMatrix}
   */
  int getColumnCount();

  /**
   * @return the columns of the current {@link IMatrix}
   */
  ExtendedIterable<IMatrixColumn<E>> getColumns();

  /**
   * @return the number of rows of the current {@link IMatrix}
   */
  int getRowCount();

  /**
   * @return the rows of the current {@link IMatrix}
   */
  ExtendedIterable<IMatrixRow<E>> getRows();

  /**
   * @param oneBasedRowIndex
   * @param oneBasedColumnIndex
   * @return the element at the given oneBasedRowIndex and oneBasedColumnIndex of
   *         the current {@link IMatrix}
   * @throws RuntimeException if the current {@link IMatrix} does not contain an
   *                          element at the given oneBasedRowIndex and
   *                          oneBasedColumnIndex
   */
  E getStoredAtOneBasedRowIndexAndColumnIndex(int oneBasedRowIndex, int oneBasedColumnIndex);

  /**
   * Sets the given element at the given oneBasedRowIndex and oneBasedColumnIndex
   * of the current {@link IMatrix}.
   * 
   * @param oneBasedRowIndex
   * @param oneBasedColumnIndex
   * @param element
   * @throws RuntimeException if the current {@link IMatrix} does not have a
   *                          position at the given oneBasedRowIndex and
   *                          oneBasedColumnIndex
   */
  void setAtOneBasedRowIndexAndColumnIndex(int oneBasedRowIndex, int oneBasedColumnIndex, E element);
}
