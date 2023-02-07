//package declaration
package ch.nolix.system.webgui.control;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlapi.ITextbox;
import ch.nolix.systemapi.webguiapi.controlapi.ITextboxStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class TextboxCSSRuleBuilder extends ExtendedControlCSSRuleBuilder<ITextbox, ITextboxStyle> {
	
	//static attribute
	public static final TextboxCSSRuleBuilder INSTANCE = new TextboxCSSRuleBuilder();
	
	//constructor
	private TextboxCSSRuleBuilder() {}

	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndStateIntoList(
		final ITextbox textbox,
		final ControlState state,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndAllStatesIntoList(
		final ITextbox textbox,	
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForControlAndAllStatesIntoList(
		final ITextbox control,
		final LinkedList<CSSProperty> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForExtendedControlAndStateIntoList(
		final ITextbox textbox,
		final ControlState state,
		final LinkedList<ICSSProperty> list
	) {
		//Does nothing.
	}
}
