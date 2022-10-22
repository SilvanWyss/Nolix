//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class TextboxCSSRuleBuilder extends ExtendedControlCSSRuleBuilder<Textbox, TextboxStyle> {
	
	//static attribute
	public static final TextboxCSSRuleBuilder INSTANCE = new TextboxCSSRuleBuilder();
	
	//constructor
	private TextboxCSSRuleBuilder() {}

	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndStateIntoList(
		final Textbox textbox,
		final ControlState state,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndAllStatesIntoList(
		final Textbox textbox,	
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForControlAndAllStatesIntoList(
		final Textbox control,
		final LinkedList<CSSProperty> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForExtendedControlAndStateIntoList(
		final Textbox textbox,
		final ControlState state,
		final LinkedList<CSSProperty> list
	) {
		//Does nothing.
	}
}
