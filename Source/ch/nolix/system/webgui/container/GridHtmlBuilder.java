package ch.nolix.system.webgui.container;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.webgui.container.IGrid;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

public final class GridHtmlBuilder implements IControlHtmlBuilder<IGrid> {
  @Override
  public IHtmlElement createHtmlElementForControl(final IGrid control) {
    return HtmlElement.withTypeAndChildElement(
      HtmlElementTypeCatalog.DIV,
      createHtmlElementForTableOfGrid(control));
  }

  public HtmlElement createHtmlElementForTableOfGrid(final IGrid control) {
    return HtmlElement.withTypeAndChildElement(
      HtmlElementTypeCatalog.TABLE,
      createHtmlElementForTableBodyOfGrid(control));
  }

  private HtmlElement createHtmlElementForTableBodyOfGrid(final IGrid grid) {
    return HtmlElement.withTypeAndChildElements(
      HtmlElementTypeCatalog.TBODY,
      createHtmlElementsForChildControlsOfGrid(grid));
  }

  private IContainer<IHtmlElement> createHtmlElementsForChildControlsOfGrid(final IGrid grid) {
    final ILinkedList<IHtmlElement> htmlElements = LinkedList.createEmpty();

    for (var ri = 1; ri <= grid.getRowCount(); ri++) {
      htmlElements.addAtEnd(createHtmlElementForRowOfGrid(grid, ri));
    }

    return htmlElements;
  }

  private HtmlElement createHtmlElementForRowOfGrid(final IGrid grid, final int rowIndex) {
    return HtmlElement.withTypeAndChildElements(
      HtmlElementTypeCatalog.TR,
      createHtmlElementsForCellsOfRowOfGrid(grid, rowIndex));
  }

  private IContainer<IHtmlElement> createHtmlElementsForCellsOfRowOfGrid(final IGrid grid, final int rowIndex) {
    final ILinkedList<IHtmlElement> htmlElements = LinkedList.createEmpty();

    for (var ci = 1; ci <= grid.getColumnCount(); ci++) {
      htmlElements.addAtEnd(createHtmlElementForCellOfGrid(grid, rowIndex, ci));
    }

    return htmlElements;
  }

  private HtmlElement createHtmlElementForCellOfGrid(final IGrid grid, final int rowIndex, final int columnIndex) {
    if (!grid.containsControlAtOneBasedRowAndColumnIndex(rowIndex, columnIndex)) {
      return HtmlElement.withType(HtmlElementTypeCatalog.TD);
    }

    final var childControl = grid.getStoredChildControlAtOneBasedRowAndColumnIndex(rowIndex, columnIndex);
    final var childControlHtmlElement = childControl.getHtml();
    return HtmlElement.withTypeAndChildElement(HtmlElementTypeCatalog.TD, childControlHtmlElement);
  }
}
