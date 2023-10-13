//package declaration
package ch.nolix.coreapi.containerapi.matrixapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IMatrix<E> extends IContainer<E> {
	
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
}
