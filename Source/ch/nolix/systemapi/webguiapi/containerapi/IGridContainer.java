//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

//own imports
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IGridContainer<
	GC extends IGridContainer<GC, GCS>,
	GCS extends IGridContainerStyle<GCS>
>
extends IContainer<GC, GCS> {
	
	//method declaration
	boolean containsControlAtRowAndColumn(int rowIndex, int columnIndex);
	
	//method declaration
	int getColumnCount();
	
	//method declaration
	IControl<?, ?> getRefChildControlAtRowAndColumn(int rowIndex, int columnIndex);
	
	//method declaration
	int getRowCount();
	
	//method declaration
	GC insertControlAtRowAndColumn(int rowIndex, int columnIndex, IControl<?, ?> control);
	
	//method declaration
	GC insertTextAtRowAndColumn(int rowIndex, int columnIndex, String text);
}
