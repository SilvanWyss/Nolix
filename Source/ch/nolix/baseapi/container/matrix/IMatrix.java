/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.container.matrix;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.state.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of a {@link IMatrix}.
 */
public interface IMatrix<E> extends Clearable, ExtendedIterable<E> {
  int getColumnCount();

  ExtendedIterable<IMatrixColumn<E>> getColumns();

  E getStoredAtOneBasedRowIndexAndColumnIndex(int oneBasedRowIndex, int oneBasedColumnIndex);

  int getRowCount();

  ExtendedIterable<IMatrixRow<E>> getRows();

  void setAtOneBasedRowIndexAndColumnIndex(int oneBasedRowIndex, int oneBasedColumnIndex, E element);
}
