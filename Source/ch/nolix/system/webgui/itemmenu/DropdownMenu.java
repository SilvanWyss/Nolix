//package declaration
package ch.nolix.system.webgui.itemmenu;

import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.main.HtmlElementEvent;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCssRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//class
public final class DropdownMenu
extends ItemMenu<DropdownMenu, DropdownMenuStyle>
implements IDropdownMenu<DropdownMenu, DropdownMenuStyle> {
	
	//constant
	private static final DropdownMenuHtmlBuilder HTML_BUILDER = new DropdownMenuHtmlBuilder();
	
	//constructor
	public DropdownMenu() {
		
		//Info: Reset is technically optional, but required to achieve a custom state on reset.
		reset();
		
		getOriStyle()
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
	public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
		list.addAtEnd(HtmlElementEvent.withHtmlElementIdAndHtmlEvent(getInternalId(), "onchange"));
	}
	
	//method
	@Override
	protected DropdownMenuStyle createStyle() {
		return new DropdownMenuStyle();
	}
	
	//method
	@Override
	protected IControlHtmlBuilder<DropdownMenu> getHtmlBuilder() {
		return HTML_BUILDER;
	}
	
	//method
	@Override
	protected IControlCssRuleBuilder<DropdownMenu, DropdownMenuStyle> getCssRuleCreator() {
		return DropdownMenuCssRuleBuilder.INSTANCE;
	}
}
