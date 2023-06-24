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
public final class GridContainerHtmlBuilder implements IControlHtmlBuilder<GridContainer> {
	
	//static attribute
	public static final GridContainerHtmlBuilder INSTANCE = new GridContainerHtmlBuilder();
	
	//constructor
	private GridContainerHtmlBuilder() {}
	
	//method
	@Override
	public IHtmlElement<?, ?> createHtmlElementForControl(final GridContainer control) {
		return
		HtmlElement.withTypeAndAttributesAndChildElement(
			HtmlElementTypeCatalogue.DIV,
			ImmutableList.withElements(ControlHelper.INSTANCE.createIdHtmlAttributeForControl(control)),
			createHtmlElementForTableOfGridContainer(control)
		);
	}
	
	//method
	public HtmlElement createHtmlElementForTableOfGridContainer(final GridContainer control) {
		return
		HtmlElement.withTypeAndChildElement(
			HtmlElementTypeCatalogue.TABLE,
			createHtmlElementForTableBodyOfGridContainer(control)
		);
	}
	
	//method
	private HtmlElement createHtmlElementForTableBodyOfGridContainer(final GridContainer gridContainer) {
		return
		HtmlElement.withTypeAndChildElements(
			HtmlElementTypeCatalogue.TBODY,
			createHtmlElementsForChildControlsOfGridContainer(gridContainer)
		);
	}
	
	//method
	private IContainer<HtmlElement> createHtmlElementsForChildControlsOfGridContainer(
		final GridContainer gridContainer
	) {
		
		final var htmlElements = new LinkedList<HtmlElement>();
		
		for (var ri = 1; ri <= gridContainer.getRowCount(); ri++) {
			htmlElements.addAtEnd(createHtmlElementForRowOfGridContainer(gridContainer, ri));
		}
		
		return htmlElements;
	}
	
	//method
	private HtmlElement createHtmlElementForRowOfGridContainer(final GridContainer gridContainer, final int rowIndex) {
		return
		HtmlElement.withTypeAndChildElements(
			HtmlElementTypeCatalogue.TR,
			createHtmlElementsForCellsOfRowOfGridContainer(gridContainer, rowIndex)
		);
	}
	
	//method
	private IContainer<HtmlElement> createHtmlElementsForCellsOfRowOfGridContainer(
		final GridContainer gridContainer,
		final int rowIndex
	) {
		
		final var htmlElements = new LinkedList<HtmlElement>();
		
		for (var ci = 1; ci <= gridContainer.getColumnCount(); ci++) {
			htmlElements.addAtEnd(createHtmlElementForCellOfGridContainer(gridContainer, rowIndex, ci));
		}
		
		return htmlElements;
	}
	
	//method
	private HtmlElement createHtmlElementForCellOfGridContainer(
		final GridContainer gridContainer,
		final int rowIndex,
		final int columnIndex
	) {
		
		if (!gridContainer.containsControlAt1BasedRowAndColumnIndex(rowIndex, columnIndex)) {
			return HtmlElement.withType(HtmlElementTypeCatalogue.TD);
		}
		
		final var childControl = gridContainer.getOriChildControlAt1BasedRowAndColumnIndex(rowIndex, columnIndex);
		final var childControlHtmlElement = childControl.toHtmlElement();
		return HtmlElement.withTypeAndChildElement(HtmlElementTypeCatalogue.TD, childControlHtmlElement);
	}
}
