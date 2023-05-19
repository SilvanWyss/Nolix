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
	boolean containsControlAt1BasedRowAndColumnIndex(int p1BasedRowIndex, int p1BasedColumnIndex);
	
	//method declaration
	int getColumnCount();
	
	//method declaration
	IControl<?, ?> getOriChildControlAt1BasedRowAndColumnIndex(int rowIndex, int columnIndex);
	
	//method declaration
	int getRowCount();
	
	//method declaration
	GC insertControlAtRowAndColumn(int rowIndex, int columnIndex, IControl<?, ?> control);
	
	//method declaration
	GC insertTextAtRowAndColumn(int rowIndex, int columnIndex, String text);
}
