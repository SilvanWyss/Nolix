package ch.nolix.system.webcontainercontrol.grid;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.webcontainercontrol.grid.IGrid;

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

  private static IContainer<IHtmlElement> createHtmlElementsForChildControlsOfGrid(final IGrid grid) {
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

  private static IContainer<IHtmlElement> createHtmlElementsForCellsOfRowOfGrid(final IGrid grid, final int rowIndex) {
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
