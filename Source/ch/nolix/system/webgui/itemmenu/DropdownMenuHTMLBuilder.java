//package declaration
package ch.nolix.system.webgui.itemmenu;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.html.HTMLAttribute;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlAttribute;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuItem;

//class
public final class DropdownMenuHTMLBuilder implements IControlHtmlBuilder<DropdownMenu> {
	
	//static attribute
	public static final DropdownMenuHTMLBuilder INSTANCE = new DropdownMenuHTMLBuilder();
	
	//constructor
	private DropdownMenuHTMLBuilder() {}
	
	//method
	@Override
	public HTMLElement createHTMLElementForControl(final DropdownMenu dropdownMenu) {
		return
		HTMLElement.withTypeAndAttributesAndChildElements(
			HtmlElementTypeCatalogue.SELECT,
			createHTMLAttributesForDropdownMenu(dropdownMenu),
			createHTMLChildElementsForDropdownMenu(dropdownMenu)
		);
	}
	
	//method
	private IContainer<? extends IHtmlAttribute> createHTMLAttributesForDropdownMenu(final DropdownMenu dropdownMenu) {
		
		final var lHTMLAttribtues = new LinkedList<IHtmlAttribute>();
		
		lHTMLAttribtues.addAtEnd(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(dropdownMenu));
		
		return lHTMLAttribtues;
	}
	
	//method
	private IContainer<IHtmlElement<?, ?>> createHTMLChildElementsForDropdownMenu(
		final IDropdownMenu<?, ?> dropdownMenu
	) {
		return createHTMLElementsFromItems(dropdownMenu.getOriItems());
	}
	
	//method
	private IContainer<IHtmlElement<?, ?>> createHTMLElementsFromItems(
		final IContainer<? extends IItemMenuItem<?>> items
	) {
		return items.to(this::createHTMLElementForItem);
	}
	
	//method
	private IHtmlElement<?, ?> createHTMLElementForItem(final IItemMenuItem<?> item) {
		return
		HTMLElement.withTypeAndAttributesAndInnerText(
			HtmlElementTypeCatalogue.OPTION,
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
