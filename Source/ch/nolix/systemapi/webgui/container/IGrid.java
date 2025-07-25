package ch.nolix.systemapi.webgui.container;

import ch.nolix.systemapi.webgui.basecontainer.IContainer;
import ch.nolix.systemapi.webgui.main.IControl;

public interface IGrid extends IContainer<IGrid, IGridStyle> {

  boolean containsControlAtOneBasedRowAndColumnIndex(int oneBasedRowIndex, int oneBasedColumnIndex);

  int getColumnCount();

  IControl<?, ?> getStoredChildControlAtOneBasedRowAndColumnIndex(int rowIndex, int columnIndex);

  int getRowCount();

  IGrid insertControlAtRowAndColumn(int oneBasedRowIndex, int oneBasedColumnIndex, IControl<?, ?> control);

  IGrid insertTextAtRowAndColumn(int oneBasedRowIndex, int oneBasedColumnIndex, String text);
}
