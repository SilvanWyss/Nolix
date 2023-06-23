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
public final class GridContainerHTMLBuilder implements IControlHtmlBuilder<GridContainer> {
	
	//static attribute
	public static final GridContainerHTMLBuilder INSTANCE = new GridContainerHTMLBuilder();
	
	//constructor
	private GridContainerHTMLBuilder() {}
	
	//method
	@Override
	public IHtmlElement<?, ?> createHTMLElementForControl(final GridContainer control) {
		return
		HtmlElement.withTypeAndAttributesAndChildElement(
			HtmlElementTypeCatalogue.DIV,
			ImmutableList.withElements(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(control)),
			createHTMLElementForTableOfGridContainer(control)
		);
	}
	
	//method
	public HtmlElement createHTMLElementForTableOfGridContainer(final GridContainer control) {
		return
		HtmlElement.withTypeAndChildElement(
			HtmlElementTypeCatalogue.TABLE,
			createHTMLElementForTableBodyOfGridContainer(control)
		);
	}
	
	//method
	private HtmlElement createHTMLElementForTableBodyOfGridContainer(final GridContainer gridContainer) {
		return
		HtmlElement.withTypeAndChildElements(
			HtmlElementTypeCatalogue.TBODY,
			createHTMLElementsForChildControlsOfGridContainer(gridContainer)
		);
	}
	
	//method
	private IContainer<HtmlElement> createHTMLElementsForChildControlsOfGridContainer(
		final GridContainer gridContainer
	) {
		
		final var lHTMLElements = new LinkedList<HtmlElement>();
		
		for (var ri = 1; ri <= gridContainer.getRowCount(); ri++) {
			lHTMLElements.addAtEnd(createHTMLElementForRowOfGridContainer(gridContainer, ri));
		}
		
		return lHTMLElements;
	}
	
	//method
	private HtmlElement createHTMLElementForRowOfGridContainer(final GridContainer gridContainer, final int rowIndex) {
		return
		HtmlElement.withTypeAndChildElements(
			HtmlElementTypeCatalogue.TR,
			createHTMLElementsForCellsOfRowOfGridContainer(gridContainer, rowIndex)
		);
	}
	
	//method
	private IContainer<HtmlElement> createHTMLElementsForCellsOfRowOfGridContainer(
		final GridContainer gridContainer,
		final int rowIndex
	) {
		
		final var lHTMLElements = new LinkedList<HtmlElement>();
		
		for (var ci = 1; ci <= gridContainer.getColumnCount(); ci++) {
			lHTMLElements.addAtEnd(createHTMLElementForCellOfGridContainer(gridContainer, rowIndex, ci));
		}
		
		return lHTMLElements;
	}
	
	//method
	private HtmlElement createHTMLElementForCellOfGridContainer(
		final GridContainer gridContainer,
		final int rowIndex,
		final int columnIndex
	) {
		
		if (!gridContainer.containsControlAt1BasedRowAndColumnIndex(rowIndex, columnIndex)) {
			return HtmlElement.withType(HtmlElementTypeCatalogue.TD);
		}
		
		final var childControl = gridContainer.getOriChildControlAt1BasedRowAndColumnIndex(rowIndex, columnIndex);
		final var childControlHTMLElement = childControl.toHTMLElement();
		return HtmlElement.withTypeAndChildElement(HtmlElementTypeCatalogue.TD, childControlHTMLElement);
	}
}
