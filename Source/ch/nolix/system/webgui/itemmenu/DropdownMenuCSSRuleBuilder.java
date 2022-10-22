//package declaration
package ch.nolix.system.webgui.itemmenu;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class DropdownMenuCSSRuleBuilder extends ItemMenuCSSRuleBuilder<DropdownMenu, DropdownMenuStyle> {
	
	//static attribute
	public static final DropdownMenuCSSRuleBuilder INSTANCE = new DropdownMenuCSSRuleBuilder();
	
	//constructor
	private DropdownMenuCSSRuleBuilder() {}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForAllStatesOfControlIntoList(
		final DropdownMenu control,
		final LinkedList<CSSProperty> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForExtendedControlAndStateIntoList(
		final DropdownMenu dropdownMenu,
		final ControlState state,
		final LinkedList<CSSProperty> list
	) {
		//Does nothing.
	}
}
