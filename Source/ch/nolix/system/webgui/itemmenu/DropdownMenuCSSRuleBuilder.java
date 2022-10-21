//package declaration
package ch.nolix.system.webgui.itemmenu;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class DropdownMenuCSSRuleBuilder extends ItemMenuCSSRuleBuilder<DropdownMenu, DropdownMenuStyle> {
	
	//static method
	public static DropdownMenuCSSRuleBuilder forDropdownMenu(final DropdownMenu dropdownMenu) {
		return new DropdownMenuCSSRuleBuilder(dropdownMenu);
	}
	
	//constructor
	private DropdownMenuCSSRuleBuilder(final DropdownMenu parentDropdownMenu) {
		super(parentDropdownMenu);
	}
	
	//method
	@Override
	protected void fillUpExtendedControlCSSPropertiesForStateIntoList(
		final ControlState state,
		final LinkedList<CSSProperty> list
	) {
		//Does nothing.
	}
}
