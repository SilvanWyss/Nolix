//package declaration
package ch.nolix.system.webgui.itemmenu;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//class
public final class DropdownMenuCSSRuleCreator extends ItemMenuCSSRuleCreator<DropdownMenu, DropdownMenuStyle> {
	
	//static method
	public static DropdownMenuCSSRuleCreator forDropdownMenu(final DropdownMenu dropdownMenu) {
		return new DropdownMenuCSSRuleCreator(dropdownMenu);
	}
	
	//constructor
	private DropdownMenuCSSRuleCreator(final DropdownMenu parentDropdownMenu) {
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
