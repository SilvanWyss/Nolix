//package declaration
package ch.nolix.system.webgui.control;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCssRuleBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class ButtonCSSRuleBuilder extends ExtendedControlCssRuleBuilder<Button, ButtonStyle> {
	
	//static attribute
	public static final ButtonCSSRuleBuilder INSTANCE = new ButtonCSSRuleBuilder();
	
	//constructor
	private ButtonCSSRuleBuilder() {}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndAllStatesIntoList(
		final Button button,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndStateIntoList(
		final Button button,
		final ControlState state,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForControlAndAllStatesIntoList(
		final Button control,
		final LinkedList<CSSProperty> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForExtendedControlAndStateIntoList(
		final Button button,
		final ControlState state,
		final LinkedList<ICSSProperty> list
	) {
		//Does nothing.
	}
}
