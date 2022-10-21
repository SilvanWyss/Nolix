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
	
	//static method
	public static TextboxCSSRuleBuilder forTextbox(final Textbox textbox) {
		return new TextboxCSSRuleBuilder(textbox);
	}
	
	//constructor
	private TextboxCSSRuleBuilder(final Textbox parentTextbox) {
		super(parentTextbox);
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForStateIntoList(
		final ControlState state,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForBaseStateIntoList(final LinkedList<? super ICSSRule<?>> list) {
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
