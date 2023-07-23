//package declaration
package ch.nolix.system.webgui.itemmenu;

//own imports
import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.main.HtmlElementEvent;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenuStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//class
public final class DropdownMenu extends ItemMenu<IDropdownMenu, IDropdownMenuStyle> implements IDropdownMenu {
	
	//constant
	private static final DropdownMenuHtmlBuilder HTML_BUILDER = new DropdownMenuHtmlBuilder();
	
	//constant
	private static final DropdownMenuCssBuilder CSS_RULE_BUILDER = new DropdownMenuCssBuilder();
	
	//constructor
	public DropdownMenu() {
		
		//Info: Reset is technically optional, but required to achieve a custom state on reset.
		reset();
		
		getStoredStyle()
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
	protected IDropdownMenuStyle createStyle() {
		return new DropdownMenuStyle();
	}
	
	//method
	@Override
	protected IControlHtmlBuilder<IDropdownMenu> getHtmlBuilder() {
		return HTML_BUILDER;
	}
	
	//method
	@Override
	protected IControlCssBuilder<IDropdownMenu, IDropdownMenuStyle> getCssRuleCreator() {
		return CSS_RULE_BUILDER;
	}
}
