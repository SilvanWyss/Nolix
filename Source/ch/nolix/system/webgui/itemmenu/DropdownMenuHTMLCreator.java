//package declaration
package ch.nolix.system.webgui.itemmenu;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.html.HTMLAttribute;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.core.web.html.HTMLElementTypeCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLAttribute;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuItem;

//class
public final class DropdownMenuHTMLCreator {
	
	//static attribute
	public static final DropdownMenuHTMLCreator INSTANCE = new DropdownMenuHTMLCreator();
	
	//constructor
	private DropdownMenuHTMLCreator() {}
	
	//method
	public HTMLElement createHTMLElementForDropdownMenu(final IDropdownMenu<?, ?, ?> dropdownMenu) {
		return
		HTMLElement.withTypeAndAttributesAndChildElements(
			HTMLElementTypeCatalogue.SELECT,
			createHTMLAttributesForDropdownMenu(dropdownMenu),
			createHTMLChildElementsForDropdownMenu(dropdownMenu)
		);
	}
	
	//method
	private IContainer<? extends IHTMLAttribute> createHTMLAttributesForDropdownMenu(
		final IDropdownMenu<?, ?, ?> dropdownMenu
	) {
		
		final var lHTMLAttribtues = new LinkedList<IHTMLAttribute>();
		
		lHTMLAttribtues.addAtEnd(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(dropdownMenu));
		
		if (dropdownMenu.hasSelectAction()) {
			lHTMLAttribtues.addAtEnd(
				HTMLAttribute.withNameAndValue(
					"onchange",
					
					//Use noteLeftMouseButtonPress as pseudo event.
					"NoteLeftMouseButtonPress_" + dropdownMenu.getFixedId()
				)
			);
		}
		
		return lHTMLAttribtues;
	}
	
	//method
	private IContainer<IHTMLElement<?, ?>> createHTMLChildElementsForDropdownMenu(
		final IDropdownMenu<?, ?, ? extends IItemMenuItem<?>> dropdownMenu
	) {
		return createHTMLElementsFromItems(dropdownMenu.getRefItems());
	}
	
	//method
	private IContainer<IHTMLElement<?, ?>> createHTMLElementsFromItems(
		final IContainer<? extends IItemMenuItem<?>> items
	) {
		return items.to(this::createHTMLElementForItem);
	}
	
	//method
	private IHTMLElement<?, ?> createHTMLElementForItem(final IItemMenuItem<?> item) {
		return HTMLElement.withTypeAndInnerText(HTMLElementTypeCatalogue.OPTION, item.getText());
	}
}
