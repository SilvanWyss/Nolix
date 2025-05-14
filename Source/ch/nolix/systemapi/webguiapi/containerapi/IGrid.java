package ch.nolix.systemapi.webguiapi.containerapi;

import ch.nolix.systemapi.webguiapi.basecontainerapi.IContainer;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public interface IGrid extends IContainer<IGrid, IGridStyle> {

  boolean containsControlAtOneBasedRowAndColumnIndex(int oneBasedRowIndex, int oneBasedColumnIndex);

  int getColumnCount();

  IControl<?, ?> getStoredChildControlAtOneBasedRowAndColumnIndex(int rowIndex, int columnIndex);

  int getRowCount();

  IGrid insertControlAtRowAndColumn(int oneBasedRowIndex, int oneBasedColumnIndex, IControl<?, ?> control);

  IGrid insertTextAtRowAndColumn(int oneBasedRowIndex, int oneBasedColumnIndex, String text);
}
