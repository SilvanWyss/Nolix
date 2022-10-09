//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCSSRuleCreator;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//class
public final class ButtonCSSRuleCreator extends ExtendedControlCSSRuleCreator<Button, ButtonStyle> {
	
	//static method
	public static ButtonCSSRuleCreator forButton(final Button button) {
		return new ButtonCSSRuleCreator(button);
	}
	
	//constructor
	private ButtonCSSRuleCreator(final Button parentButton) {
		super(parentButton);
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesIntoList(final LinkedList<? super ICSSRule<?>> list) {
		//Does nothing.
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
