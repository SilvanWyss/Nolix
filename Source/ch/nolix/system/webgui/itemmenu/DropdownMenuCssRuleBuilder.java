//package declaration
package ch.nolix.system.webgui.itemmenu;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class DropdownMenuCssRuleBuilder extends ItemMenuCSSRuleBuilder<DropdownMenu, DropdownMenuStyle> {
	
	//static attribute
	public static final DropdownMenuCssRuleBuilder INSTANCE = new DropdownMenuCssRuleBuilder();
	
	//constructor
	private DropdownMenuCssRuleBuilder() {}
	
	//method
	@Override
	protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
		final DropdownMenu control,
		final LinkedList<CssProperty> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCssPropertiesForExtendedControlAndStateIntoList(
		final DropdownMenu dropdownMenu,
		final ControlState state,
		final LinkedList<ICssProperty> list
	) {
		//Does nothing.
	}
}
