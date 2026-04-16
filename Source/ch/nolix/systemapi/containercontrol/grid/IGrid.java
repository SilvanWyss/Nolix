/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.containercontrol.grid;

import ch.nolix.systemapi.containercontrol.container.IContainer;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public interface IGrid extends IContainer<IGrid, IGridStyle> {
  boolean containsControlAtOneBasedRowAndColumnIndex(int oneBasedRowIndex, int oneBasedColumnIndex);

  int getColumnCount();

  IControl<?, ?> getStoredChildControlAtOneBasedRowAndColumnIndex(int rowIndex, int columnIndex);

  int getRowCount();

  IGrid insertControlAtRowAndColumn(int oneBasedRowIndex, int oneBasedColumnIndex, IControl<?, ?> control);

  IGrid insertTextAtRowAndColumn(int oneBasedRowIndex, int oneBasedColumnIndex, String text);
}
