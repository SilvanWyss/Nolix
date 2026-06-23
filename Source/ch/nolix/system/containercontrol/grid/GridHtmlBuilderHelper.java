/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.containercontrol.grid;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.html.htmlmodel.HtmlElement;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.html.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.baseapi.html.htmlmodel.IHtmlElement;
import ch.nolix.systemapi.containercontrol.grid.IGrid;

/**
 * @author Silvan Wyss
 */
public final class GridHtmlBuilderHelper {
  private GridHtmlBuilderHelper() {
  }

  public static HtmlElement createHtmlElementForTableOfGrid(final IGrid control) {
    final var type = HtmlElementTypeCatalog.TABLE;
    final var childElement = GridHtmlBuilderHelper.createHtmlElementForTableBodyOfGrid(control);

    return HtmlElement.withTypeAndChildElement(type, childElement);
  }

  public static HtmlElement createHtmlElementForTableBodyOfGrid(final IGrid grid) {
    final var type = HtmlElementTypeCatalog.TBODY;
    final var childElement = createHtmlElementsForChildControlsOfGrid(grid);

    return HtmlElement.withTypeAndChildElements(type, childElement);
  }

  private static ExtendedIterable<IHtmlElement> createHtmlElementsForChildControlsOfGrid(final IGrid grid) {
    final ILinkedList<IHtmlElement> htmlElements = LinkedList.createEmpty();
    final var rowCount = grid.getRowCount();

    for (var r = 1; r <= rowCount; r++) {
      htmlElements.addAtEnd(createHtmlElementForRowOfGrid(grid, r));
    }

    return htmlElements;
  }

  private static HtmlElement createHtmlElementForRowOfGrid(final IGrid grid, final int rowIndex) {
    final var type = HtmlElementTypeCatalog.TR;
    final var childElements = createHtmlElementsForCellsOfRowOfGrid(grid, rowIndex);

    return HtmlElement.withTypeAndChildElements(type, childElements);
  }

  private static ExtendedIterable<IHtmlElement> createHtmlElementsForCellsOfRowOfGrid(
    final IGrid grid,
    final int rowIndex) {
    final ILinkedList<IHtmlElement> htmlElements = LinkedList.createEmpty();
    final var columnCount = grid.getColumnCount();

    for (var c = 1; c <= columnCount; c++) {
      htmlElements.addAtEnd(createHtmlElementForCellOfGrid(grid, rowIndex, c));
    }

    return htmlElements;
  }

  private static HtmlElement createHtmlElementForCellOfGrid(
    final IGrid grid,
    final int rowIndex,
    final int columnIndex) {
    final var type = HtmlElementTypeCatalog.TD;

    if (grid.containsControlAtOneBasedRowAndColumnIndex(rowIndex, columnIndex)) {
      final var childControl = grid.getStoredChildControlAtOneBasedRowAndColumnIndex(rowIndex, columnIndex);
      final var childElement = childControl.getHtml();

      return HtmlElement.withTypeAndChildElement(type, childElement);
    }

    return HtmlElement.withType(type);
  }
}
