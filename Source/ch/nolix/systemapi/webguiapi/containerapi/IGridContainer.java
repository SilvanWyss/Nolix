//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IGridContainer extends Clearable, IControl<IGridContainer, IGridContainerLook> {
	
	//method declaration
	int getColumnCount();
	
	//method declaration
	int getRowCount();
	
	//method declaration
	IGridContainer insertControlAtRowAndColumn(int rowIndex, int columnIndex, IControl<?, ?> control);
	
	//method declaration
	IGridContainer insertTextAtRowAndColumn(int rowIndex, int columnIndex, String text);
}
