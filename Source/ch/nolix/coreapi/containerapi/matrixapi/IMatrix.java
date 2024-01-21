//package declaration
package ch.nolix.coreapi.containerapi.matrixapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.mutationapi.Clearable;

//interface
public interface IMatrix<E> extends Clearable, IContainer<E> {

  //method declaration
  int getColumnCount();

  //method declaration
  IContainer<? extends IContainer<E>> getColumns();

  //method declaration
  E getStoredAt1BasedRowIndexAndColumnIndex(int p1BasedRowIndex, int p1BasedColumnIndex);

  //method declaration
  int getRowCount();

  //method declaration
  IContainer<? extends IContainer<E>> getRows();

  //method declaration
  void setAt1BasedRowIndexAndColumnIndex(int param1BasedRowIndex, int param1BasedColumnIndex, E element);
}
