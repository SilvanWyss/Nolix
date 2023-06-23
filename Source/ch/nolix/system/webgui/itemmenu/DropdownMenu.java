//package declaration
package ch.nolix.system.webgui.itemmenu;

import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.main.HTMLElementEvent;
import ch.nolix.systemapi.guiapi.structureproperty.CursorIcon;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//class
public final class DropdownMenu
extends ItemMenu<DropdownMenu, DropdownMenuStyle>
implements IDropdownMenu<DropdownMenu, DropdownMenuStyle> {
	
	//constant
	public static final CursorIcon DEFAULT_CURSOR_ICON = CursorIcon.HAND;
	
	//constructor
	public DropdownMenu() {
		getOriStyle()
		.setBackgroundColorForState(ControlState.BASE, Color.AQUAMARINE)
		.setBackgroundColorForState(ControlState.HOVER, Color.MEDIUM_AQUA_MARINE)
		.setBackgroundColorForState(ControlState.FOCUS, Color.MEDIUM_AQUA_MARINE);
	}
	
	//method
	@Override
	public CursorIcon getDefaultCursorIcon() {
		return DEFAULT_CURSOR_ICON;
	}
	
	//method
	@Override
	public ISingleContainer<String> getOptionalJavaScriptUserInputFunction() {
		return new SingleContainer<>(
			"if (x.selectedIndex == -1) {return '';} return x.options[x.selectedIndex].text;"
		);
	}
	
	//method
	@Override
	public void registerHTMLElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
		list.addAtEnd(HTMLElementEvent.withHTMLElementIdAndHTMLEvent(getInternalId(), "onchange"));
	}
	
	//method
	@Override
	protected DropdownMenuStyle createStyle() {
		return new DropdownMenuStyle();
	}
	
	//method
	@Override
	protected IControlHtmlBuilder<DropdownMenu> getHTMLBuilder() {
		return DropdownMenuHTMLBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected IControlCSSRuleBuilder<DropdownMenu, DropdownMenuStyle> getCSSRuleCreator() {
		return DropdownMenuCSSRuleBuilder.INSTANCE;
	}
}
