//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.webguiapi.containerapi.IGrid;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlHtmlBuilder;

//class
public final class GridHtmlBuilder implements IControlHtmlBuilder<IGrid> {

  // method
  @Override
  public IHtmlElement createHtmlElementForControl(final IGrid control) {
    return HtmlElement.withTypeAndChildElement(
        HtmlElementTypeCatalogue.DIV,
        createHtmlElementForTableOfGrid(control));
  }

  // method
  public HtmlElement createHtmlElementForTableOfGrid(final IGrid control) {
    return HtmlElement.withTypeAndChildElement(
        HtmlElementTypeCatalogue.TABLE,
        createHtmlElementForTableBodyOfGrid(control));
  }

  // method
  private HtmlElement createHtmlElementForTableBodyOfGrid(final IGrid grid) {
    return HtmlElement.withTypeAndChildElements(
        HtmlElementTypeCatalogue.TBODY,
        createHtmlElementsForChildControlsOfGrid(grid));
  }

  // method
  private IContainer<HtmlElement> createHtmlElementsForChildControlsOfGrid(
      final IGrid grid) {

    final var htmlElements = new LinkedList<HtmlElement>();

    for (var ri = 1; ri <= grid.getRowCount(); ri++) {
      htmlElements.addAtEnd(createHtmlElementForRowOfGrid(grid, ri));
    }

    return htmlElements;
  }

  // method
  private HtmlElement createHtmlElementForRowOfGrid(final IGrid grid, final int rowIndex) {
    return HtmlElement.withTypeAndChildElements(
        HtmlElementTypeCatalogue.TR,
        createHtmlElementsForCellsOfRowOfGrid(grid, rowIndex));
  }

  // method
  private IContainer<HtmlElement> createHtmlElementsForCellsOfRowOfGrid(final IGrid grid, final int rowIndex) {

    final var htmlElements = new LinkedList<HtmlElement>();

    for (var ci = 1; ci <= grid.getColumnCount(); ci++) {
      htmlElements.addAtEnd(createHtmlElementForCellOfGrid(grid, rowIndex, ci));
    }

    return htmlElements;
  }

  // method
  private HtmlElement createHtmlElementForCellOfGrid(final IGrid grid, final int rowIndex, final int columnIndex) {

    if (!grid.containsControlAt1BasedRowAndColumnIndex(rowIndex, columnIndex)) {
      return HtmlElement.withType(HtmlElementTypeCatalogue.TD);
    }

    final var childControl = grid.getStoredChildControlAt1BasedRowAndColumnIndex(rowIndex, columnIndex);
    final var childControlHtmlElement = childControl.getHtml();
    return HtmlElement.withTypeAndChildElement(HtmlElementTypeCatalogue.TD, childControlHtmlElement);
  }
}
