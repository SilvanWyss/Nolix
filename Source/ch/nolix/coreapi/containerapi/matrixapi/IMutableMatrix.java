//package declaration
package ch.nolix.coreapi.containerapi.matrixapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationapi.Clearable;

//interface
public interface IMutableMatrix<E> extends Clearable, IMatrix<E> {

  // method declaration
  void setAt1BasedRowIndexAndColumnIndex(int param1BasedRowIndex, int param1BasedColumnIndex, E element);
}
