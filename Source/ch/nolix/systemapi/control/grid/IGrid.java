/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.grid;

import ch.nolix.systemapi.control.container.Container;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface IGrid extends Container<IGrid, IGridStyle> {
  /**
   * @param oneBasedRowIndex
   * @param oneBasedColumnIndex
   * @return true if the current {@link IGrid} contains a {@link Control} at the
   *         given oneBasedRowIndex and oneBasedColumnIndex, false otherwise
   */
  boolean containsControlAtOneBasedRowAndColumnIndex(int oneBasedRowIndex, int oneBasedColumnIndex);

  /**
   * @return the number of columns of the current {@link IGrid}
   */
  int getColumnCount();

  /**
   * @return the number of rows of the current {@link IGrid}
   */
  int getRowCount();

  /**
   * @param rowIndex
   * @param columnIndex
   * @return the child {@link Control} at the given one-based rowIndex and
   *         columnIndex of the current {@link IGrid}
   * @throws RuntimeException if the current {@link IGrid} does not contain a
   *                          {@link Control} at the given rowIndex and
   *                          columnIndex
   */
  Control<?, ?> getStoredChildControlAtOneBasedRowAndColumnIndex(int rowIndex, int columnIndex);

  /**
   * Inserts the given control at the given oneBasedRowIndex and
   * oneBasedColumnIndex into the current {@link IGrid}.
   * 
   * @param oneBasedRowIndex
   * @param oneBasedColumnIndex
   * @param control
   * @return the current {@link IGrid}
   */
  IGrid insertControlAtRowAndColumn(int oneBasedRowIndex, int oneBasedColumnIndex, Control<?, ?> control);

  /**
   * Inserts the given text at the given oneBasedRowIndex and oneBasedColumnIndex
   * into the current {@link IGrid}.
   * 
   * @param oneBasedRowIndex
   * @param oneBasedColumnIndex
   * @param text
   * @return the current {@link IGrid}
   */
  IGrid insertTextAtRowAndColumn(int oneBasedRowIndex, int oneBasedColumnIndex, String text);
}
