//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HTMLElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;
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
	public IHTMLElement<?, ?> createHTMLElementForControl(final GridContainer control) {
		return
		HTMLElement.withTypeAndAttributesAndChildElement(
			HTMLElementTypeCatalogue.DIV,
			ImmutableList.withElements(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(control)),
			createHTMLElementForTableOfGridContainer(control)
		);
	}
	
	//method
	public HTMLElement createHTMLElementForTableOfGridContainer(final GridContainer control) {
		return
		HTMLElement.withTypeAndChildElement(
			HTMLElementTypeCatalogue.TABLE,
			createHTMLElementForTableBodyOfGridContainer(control)
		);
	}
	
	//method
	private HTMLElement createHTMLElementForTableBodyOfGridContainer(final GridContainer gridContainer) {
		return
		HTMLElement.withTypeAndChildElements(
			HTMLElementTypeCatalogue.TBODY,
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
			HTMLElementTypeCatalogue.TR,
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
		
		if (!gridContainer.containsControlAtRowAndColumn(rowIndex, columnIndex)) {
			return HTMLElement.withType(HTMLElementTypeCatalogue.TD);
		}
		
		final var childControl = gridContainer.getRefChildControlAtRowAndColumn(rowIndex, columnIndex);
		final var childControlHTMLElement = childControl.toHTMLElement();
		return HTMLElement.withTypeAndChildElement(HTMLElementTypeCatalogue.TD, childControlHTMLElement);
	}
}
