package ch.nolix.systemapi.webguiapi.containerapi;

import ch.nolix.systemapi.webguiapi.basecontainerapi.IContainer;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public interface IGrid extends IContainer<IGrid, IGridStyle> {

  boolean containsControlAt1BasedRowAndColumnIndex(int param1BasedRowIndex, int param1BasedColumnIndex);

  int getColumnCount();

  IControl<?, ?> getStoredChildControlAt1BasedRowAndColumnIndex(int rowIndex, int columnIndex);

  int getRowCount();

  IGrid insertControlAtRowAndColumn(int param1BasedRowIndex, int param1BasedColumnIndex, IControl<?, ?> control);

  IGrid insertTextAtRowAndColumn(int param1BasedRowIndex, int param1BasedColumnIndex, String text);
}
