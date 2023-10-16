//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

//own imports
import ch.nolix.systemapi.webguiapi.basecontainerapi.IContainer;
import ch.nolix.systemapi.webguiapi.basecontainerapi.IControlGetter;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IGrid extends IContainer<IGrid, IGridStyle> {

  //method declaration
  boolean containsControlAt1BasedRowAndColumnIndex(int p1BasedRowIndex, int p1BasedColumnIndex);

  //method declaration
  int getColumnCount();

  //method declaration
  IControl<?, ?> getStoredChildControlAt1BasedRowAndColumnIndex(int rowIndex, int columnIndex);

  //method declaration
  int getRowCount();

  //method declaration
  IGrid insertComponentAtRowAndColumn(int param1BasedRowIndex, int param1BasedColumnIndex, IControlGetter component);

  //method declaration
  IGrid insertControlAtRowAndColumn(int param1BasedRowIndex, int param1BasedColumnIndex, IControl<?, ?> control);

  //method declaration
  IGrid insertTextAtRowAndColumn(int param1BasedRowIndex, int param1BasedColumnIndex, String text);
}
