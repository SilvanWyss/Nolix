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
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHTMLBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuItem;

//class
public final class DropdownMenuHTMLBuilder implements IControlHTMLBuilder<DropdownMenu> {
	
	//static attribute
	public static final DropdownMenuHTMLBuilder INSTANCE = new DropdownMenuHTMLBuilder();
	
	//constructor
	private DropdownMenuHTMLBuilder() {}
	
	//method
	public HTMLElement createHTMLElementForControl(final DropdownMenu dropdownMenu) {
		return
		HTMLElement.withTypeAndAttributesAndChildElements(
			HTMLElementTypeCatalogue.SELECT,
			createHTMLAttributesForDropdownMenu(dropdownMenu),
			createHTMLChildElementsForDropdownMenu(dropdownMenu)
		);
	}
	
	//method
	private IContainer<? extends IHTMLAttribute> createHTMLAttributesForDropdownMenu(final DropdownMenu dropdownMenu) {
		
		final var lHTMLAttribtues = new LinkedList<IHTMLAttribute>();
		
		lHTMLAttribtues.addAtEnd(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(dropdownMenu));
		
		lHTMLAttribtues.addAtEnd(
			HTMLAttribute.withNameAndValue(
				"onchange",
				
				//Uses noteLeftMouseButtonPress as event to trigger update of user inputs.
				"NoteLeftMouseButtonPress_" + dropdownMenu.getFixedId()
			)
		);
				
		return lHTMLAttribtues;
	}
	
	//method
	private IContainer<IHTMLElement<?, ?>> createHTMLChildElementsForDropdownMenu(
		final IDropdownMenu<?, ?> dropdownMenu
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
		return
		HTMLElement.withTypeAndAttributesAndInnerText(
			HTMLElementTypeCatalogue.OPTION,
			createHTMLAttributesForItem(item),
			item.getText()
		);
	}
	
	//method
	private IContainer<HTMLAttribute> createHTMLAttributesForItem(final IItemMenuItem<?> item) {
		
		final var lHTMLAttributes = new LinkedList<HTMLAttribute>();
		
		if (item.isSelected()) {
			lHTMLAttributes.addAtEnd(HTMLAttribute.withNameAndValue("selected", "selected"));
		}
		
		return lHTMLAttributes;
	}
}
