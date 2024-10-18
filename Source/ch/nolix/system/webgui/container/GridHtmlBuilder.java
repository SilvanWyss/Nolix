package ch.nolix.system.webgui.container;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.webguiapi.containerapi.IGrid;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;

public final class GridHtmlBuilder implements IControlHtmlBuilder<IGrid> {

  @Override
  public IHtmlElement createHtmlElementForControl(final IGrid control) {
    return HtmlElement.withTypeAndChildElement(
      HtmlElementTypeCatalogue.DIV,
      createHtmlElementForTableOfGrid(control));
  }

  public HtmlElement createHtmlElementForTableOfGrid(final IGrid control) {
    return HtmlElement.withTypeAndChildElement(
      HtmlElementTypeCatalogue.TABLE,
      createHtmlElementForTableBodyOfGrid(control));
  }

  private HtmlElement createHtmlElementForTableBodyOfGrid(final IGrid grid) {
    return HtmlElement.withTypeAndChildElements(
      HtmlElementTypeCatalogue.TBODY,
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
      HtmlElementTypeCatalogue.TR,
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

    if (!grid.containsControlAt1BasedRowAndColumnIndex(rowIndex, columnIndex)) {
      return HtmlElement.withType(HtmlElementTypeCatalogue.TD);
    }

    final var childControl = grid.getStoredChildControlAt1BasedRowAndColumnIndex(rowIndex, columnIndex);
    final var childControlHtmlElement = childControl.getHtml();
    return HtmlElement.withTypeAndChildElement(HtmlElementTypeCatalogue.TD, childControlHtmlElement);
  }
}
