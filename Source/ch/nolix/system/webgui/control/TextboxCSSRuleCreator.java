//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCSSRuleCreator;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//class
public final class TextboxCSSRuleCreator extends ExtendedControlCSSRuleCreator<Textbox, TextboxStyle> {
	
	//static method
	public static TextboxCSSRuleCreator forTextbox(final Textbox textbox) {
		return new TextboxCSSRuleCreator(textbox);
	}
	
	//constructor
	private TextboxCSSRuleCreator(final Textbox parentTextbox) {
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
