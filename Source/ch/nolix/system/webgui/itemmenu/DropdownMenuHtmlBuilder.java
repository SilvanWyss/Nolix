//package declaration
package ch.nolix.system.webgui.itemmenu;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlAttribute;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuItem;

//class
public final class DropdownMenuHtmlBuilder implements IControlHtmlBuilder<DropdownMenu> {
	
	//static attribute
	public static final DropdownMenuHtmlBuilder INSTANCE = new DropdownMenuHtmlBuilder();
	
	//constructor
	private DropdownMenuHtmlBuilder() {}
	
	//method
	@Override
	public HtmlElement createHtmlElementForControl(final DropdownMenu dropdownMenu) {
		return
		HtmlElement.withTypeAndAttributesAndChildElements(
			HtmlElementTypeCatalogue.SELECT,
			createHtmlAttributesForDropdownMenu(dropdownMenu),
			createHtmlChildElementsForDropdownMenu(dropdownMenu)
		);
	}
	
	//method
	private IContainer<? extends IHtmlAttribute> createHtmlAttributesForDropdownMenu(final DropdownMenu dropdownMenu) {
		
		final var lHTMLAttribtues = new LinkedList<IHtmlAttribute>();
		
		lHTMLAttribtues.addAtEnd(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(dropdownMenu));
		
		return lHTMLAttribtues;
	}
	
	//method
	private IContainer<IHtmlElement<?, ?>> createHtmlChildElementsForDropdownMenu(
		final IDropdownMenu<?, ?> dropdownMenu
	) {
		return createHtmlElementsFromItems(dropdownMenu.getOriItems());
	}
	
	//method
	private IContainer<IHtmlElement<?, ?>> createHtmlElementsFromItems(
		final IContainer<? extends IItemMenuItem<?>> items
	) {
		return items.to(this::createHtmlElementForItem);
	}
	
	//method
	private IHtmlElement<?, ?> createHtmlElementForItem(final IItemMenuItem<?> item) {
		return
		HtmlElement.withTypeAndAttributesAndInnerText(
			HtmlElementTypeCatalogue.OPTION,
			createHtmlAttributesForItem(item),
			item.getText()
		);
	}
	
	//method
	private IContainer<HtmlAttribute> createHtmlAttributesForItem(final IItemMenuItem<?> item) {
		
		final var lHTMLAttributes = new LinkedList<HtmlAttribute>();
		
		if (item.isSelected()) {
			lHTMLAttributes.addAtEnd(HtmlAttribute.withNameAndValue("selected", "selected"));
		}
		
		return lHTMLAttributes;
	}
}
