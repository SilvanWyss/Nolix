//package declaration
package ch.nolix.system.webgui.itemmenu;

import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.main.HTMLElementEvent;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHTMLBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IHTMLElementEvent;

//class
public final class DropdownMenu
extends ItemMenu<DropdownMenu, DropdownMenuStyle>
implements IDropdownMenu<DropdownMenu, DropdownMenuStyle> {
	
	//constructor
	public DropdownMenu() {
		getRefStyle()
		.setBackgroundColorForState(ControlState.BASE, Color.AQUAMARINE)
		.setBackgroundColorForState(ControlState.HOVER, Color.MEDIUM_AQUA_MARINE)
		.setBackgroundColorForState(ControlState.FOCUS, Color.MEDIUM_AQUA_MARINE);
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
	public void registerHTMLElementEventsAt(final ILinkedList<IHTMLElementEvent> list) {
		list.addAtEnd(HTMLElementEvent.withHTMLElementIdAndHTMLEvent(getFixedId(), "onchange"));
	}
	
	//method
	@Override
	protected DropdownMenuStyle createStyle() {
		return new DropdownMenuStyle();
	}
	
	//method
	@Override
	protected IControlHTMLBuilder<DropdownMenu> getHTMLBuilder() {
		return DropdownMenuHTMLBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected IControlCSSRuleBuilder<DropdownMenu, DropdownMenuStyle> getCSSRuleCreator() {
		return DropdownMenuCSSRuleBuilder.INSTANCE;
	}
}
