//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class TextCSSRuleBuilder extends ExtendedControlCSSRuleBuilder<Text, TextStyle> {
	
	//static attribute
	public static final TextCSSRuleBuilder INSTANCE = new TextCSSRuleBuilder();
	
	//constructor
	private TextCSSRuleBuilder() {}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForControlAndAllStatesIntoList(
		final Text control,
		final LinkedList<CSSProperty> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndStateIntoList(
		final Text text,
		final ControlState state,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndAllStatesIntoList(
		final Text text,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForExtendedControlAndStateIntoList(
		final Text text,
		final ControlState state,
		final LinkedList<CSSProperty> list
	) {
		//Does nothing.
	}
}
