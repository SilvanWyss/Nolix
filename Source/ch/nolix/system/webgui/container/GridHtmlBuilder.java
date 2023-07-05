//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;

//class
public final class GridHtmlBuilder implements IControlHtmlBuilder<Grid> {
	
	//constant
	private static final ControlHelper CONTROL_HELPER = new ControlHelper();
	
	//method
	@Override
	public IHtmlElement<?, ?> createHtmlElementForControl(final Grid control) {
		return
		HtmlElement.withTypeAndAttributesAndChildElement(
			HtmlElementTypeCatalogue.DIV,
			ImmutableList.withElements(CONTROL_HELPER.createIdHtmlAttributeForControl(control)),
			createHtmlElementForTableOfGrid(control)
		);
	}
	
	//method
	public HtmlElement createHtmlElementForTableOfGrid(final Grid control) {
		return
		HtmlElement.withTypeAndChildElement(
			HtmlElementTypeCatalogue.TABLE,
			createHtmlElementForTableBodyOfGrid(control)
		);
	}
	
	//method
	private HtmlElement createHtmlElementForTableBodyOfGrid(final Grid grid) {
		return
		HtmlElement.withTypeAndChildElements(
			HtmlElementTypeCatalogue.TBODY,
			createHtmlElementsForChildControlsOfGrid(grid)
		);
	}
	
	//method
	private IContainer<HtmlElement> createHtmlElementsForChildControlsOfGrid(
		final Grid grid
	) {
		
		final var htmlElements = new LinkedList<HtmlElement>();
		
		for (var ri = 1; ri <= grid.getRowCount(); ri++) {
			htmlElements.addAtEnd(createHtmlElementForRowOfGrid(grid, ri));
		}
		
		return htmlElements;
	}
	
	//method
	private HtmlElement createHtmlElementForRowOfGrid(final Grid grid, final int rowIndex) {
		return
		HtmlElement.withTypeAndChildElements(
			HtmlElementTypeCatalogue.TR,
			createHtmlElementsForCellsOfRowOfGrid(grid, rowIndex)
		);
	}
	
	//method
	private IContainer<HtmlElement> createHtmlElementsForCellsOfRowOfGrid(
		final Grid grid,
		final int rowIndex
	) {
		
		final var htmlElements = new LinkedList<HtmlElement>();
		
		for (var ci = 1; ci <= grid.getColumnCount(); ci++) {
			htmlElements.addAtEnd(createHtmlElementForCellOfGrid(grid, rowIndex, ci));
		}
		
		return htmlElements;
	}
	
	//method
	private HtmlElement createHtmlElementForCellOfGrid(
		final Grid grid,
		final int rowIndex,
		final int columnIndex
	) {
		
		if (!grid.containsControlAt1BasedRowAndColumnIndex(rowIndex, columnIndex)) {
			return HtmlElement.withType(HtmlElementTypeCatalogue.TD);
		}
		
		final var childControl = grid.getOriChildControlAt1BasedRowAndColumnIndex(rowIndex, columnIndex);
		final var childControlHtmlElement = childControl.toHtmlElement();
		return HtmlElement.withTypeAndChildElement(HtmlElementTypeCatalogue.TD, childControlHtmlElement);
	}
}
