/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.containercontrol.grid;

import ch.nolix.systemapi.containercontrol.container.IContainer;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface IGrid extends IContainer<IGrid, IGridStyle> {
  boolean containsControlAtOneBasedRowAndColumnIndex(int oneBasedRowIndex, int oneBasedColumnIndex);

  int getColumnCount();

  Control<?, ?> getStoredChildControlAtOneBasedRowAndColumnIndex(int rowIndex, int columnIndex);

  int getRowCount();

  IGrid insertControlAtRowAndColumn(int oneBasedRowIndex, int oneBasedColumnIndex, Control<?, ?> control);

  IGrid insertTextAtRowAndColumn(int oneBasedRowIndex, int oneBasedColumnIndex, String text);
}
