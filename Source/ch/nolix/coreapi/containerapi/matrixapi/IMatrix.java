//package declaration
package ch.nolix.coreapi.containerapi.matrixapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;

//interface
public interface IMatrix<E> extends IContainer<E> {
	
	//method declaration
	int getColumnCount();
	
	//method declaration
	IContainer<? extends IContainer<E>> getColumns();
	
	//method declaration
	E getRefAt(int rowIndex, int columnIndex);
	
	//method declaration
	int getRowCount();
	
	//method declaration
	IContainer<? extends IContainer<E>> getRows();
	
	//method declaration
	void setAt(int rowIndex, int columnIndex, E element);
}
