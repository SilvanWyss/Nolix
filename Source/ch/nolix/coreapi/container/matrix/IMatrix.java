package ch.nolix.coreapi.container.matrix;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.state.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of a {@link IMatrix}.
 */
public interface IMatrix<E> extends Clearable, IContainer<E> {
  int getColumnCount();

  IContainer<? extends IContainer<E>> getColumns();

  E getStoredAtOneBasedRowIndexAndColumnIndex(int oneBasedRowIndex, int oneBasedColumnIndex);

  int getRowCount();

  IContainer<? extends IContainer<E>> getRows();

  void setAtOneBasedRowIndexAndColumnIndex(int oneBasedRowIndex, int oneBasedColumnIndex, E element);
}
