//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class ButtonCSSRuleBuilder extends ExtendedControlCSSRuleBuilder<Button, ButtonStyle> {
	
	//static attribute
	public static final ButtonCSSRuleBuilder INSTACE = new ButtonCSSRuleBuilder();
	
	//constructor
	private ButtonCSSRuleBuilder() {}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForStateIntoList(
		final Button button,
		final ControlState state,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForBaseStateIntoList(
		final Button button,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpExtendedControlCSSPropertiesForStateIntoList(
		final Button button,
		final ControlState state,
		final LinkedList<CSSProperty> list
	) {
		//Does nothing.
	}
}
