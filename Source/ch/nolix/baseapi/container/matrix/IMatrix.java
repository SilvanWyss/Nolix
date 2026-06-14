/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.container.matrix;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.state.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of a {@link IMatrix}.
 */
public interface IMatrix<E> extends Clearable, IWellOrderContainer<E> {
  int getColumnCount();

  IWellOrderContainer<IMatrixColumn<E>> getColumns();

  E getStoredAtOneBasedRowIndexAndColumnIndex(int oneBasedRowIndex, int oneBasedColumnIndex);

  int getRowCount();

  IWellOrderContainer<IMatrixRow<E>> getRows();

  void setAtOneBasedRowIndexAndColumnIndex(int oneBasedRowIndex, int oneBasedColumnIndex, E element);
}
