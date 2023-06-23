//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHTMLBuilder;

//class
public final class GridContainerHTMLBuilder implements IControlHTMLBuilder<GridContainer> {
	
	//static attribute
	public static final GridContainerHTMLBuilder INSTANCE = new GridContainerHTMLBuilder();
	
	//constructor
	private GridContainerHTMLBuilder() {}
	
	//method
	@Override
	public IHtmlElement<?, ?> createHTMLElementForControl(final GridContainer control) {
		return
		HTMLElement.withTypeAndAttributesAndChildElement(
			HtmlElementTypeCatalogue.DIV,
			ImmutableList.withElements(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(control)),
			createHTMLElementForTableOfGridContainer(control)
		);
	}
	
	//method
	public HTMLElement createHTMLElementForTableOfGridContainer(final GridContainer control) {
		return
		HTMLElement.withTypeAndChildElement(
			HtmlElementTypeCatalogue.TABLE,
			createHTMLElementForTableBodyOfGridContainer(control)
		);
	}
	
	//method
	private HTMLElement createHTMLElementForTableBodyOfGridContainer(final GridContainer gridContainer) {
		return
		HTMLElement.withTypeAndChildElements(
			HtmlElementTypeCatalogue.TBODY,
			createHTMLElementsForChildControlsOfGridContainer(gridContainer)
		);
	}
	
	//method
	private IContainer<HTMLElement> createHTMLElementsForChildControlsOfGridContainer(
		final GridContainer gridContainer
	) {
		
		final var lHTMLElements = new LinkedList<HTMLElement>();
		
		for (var ri = 1; ri <= gridContainer.getRowCount(); ri++) {
			lHTMLElements.addAtEnd(createHTMLElementForRowOfGridContainer(gridContainer, ri));
		}
		
		return lHTMLElements;
	}
	
	//method
	private HTMLElement createHTMLElementForRowOfGridContainer(final GridContainer gridContainer, final int rowIndex) {
		return
		HTMLElement.withTypeAndChildElements(
			HtmlElementTypeCatalogue.TR,
			createHTMLElementsForCellsOfRowOfGridContainer(gridContainer, rowIndex)
		);
	}
	
	//method
	private IContainer<HTMLElement> createHTMLElementsForCellsOfRowOfGridContainer(
		final GridContainer gridContainer,
		final int rowIndex
	) {
		
		final var lHTMLElements = new LinkedList<HTMLElement>();
		
		for (var ci = 1; ci <= gridContainer.getColumnCount(); ci++) {
			lHTMLElements.addAtEnd(createHTMLElementForCellOfGridContainer(gridContainer, rowIndex, ci));
		}
		
		return lHTMLElements;
	}
	
	//method
	private HTMLElement createHTMLElementForCellOfGridContainer(
		final GridContainer gridContainer,
		final int rowIndex,
		final int columnIndex
	) {
		
		if (!gridContainer.containsControlAt1BasedRowAndColumnIndex(rowIndex, columnIndex)) {
			return HTMLElement.withType(HtmlElementTypeCatalogue.TD);
		}
		
		final var childControl = gridContainer.getOriChildControlAt1BasedRowAndColumnIndex(rowIndex, columnIndex);
		final var childControlHTMLElement = childControl.toHTMLElement();
		return HTMLElement.withTypeAndChildElement(HtmlElementTypeCatalogue.TD, childControlHTMLElement);
	}
}
