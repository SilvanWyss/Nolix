package ch.nolix.coreapi.containerapi.matrixapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

public interface IMatrix<E> extends Clearable, IContainer<E> {

  int getColumnCount();

  IContainer<? extends IContainer<E>> getColumns();

  E getStoredAt1BasedRowIndexAndColumnIndex(int param1BasedRowIndex, int param1BasedColumnIndex);

  int getRowCount();

  IContainer<? extends IContainer<E>> getRows();

  void setAt1BasedRowIndexAndColumnIndex(int param1BasedRowIndex, int param1BasedColumnIndex, E element);
}
