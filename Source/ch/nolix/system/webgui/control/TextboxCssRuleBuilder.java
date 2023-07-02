//package declaration
package ch.nolix.system.webgui.control;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCssRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlapi.ITextbox;
import ch.nolix.systemapi.webguiapi.controlapi.ITextboxStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class TextboxCssRuleBuilder extends ExtendedControlCssRuleBuilder<ITextbox, ITextboxStyle> {
	
	//static attribute
	public static final TextboxCssRuleBuilder INSTANCE = new TextboxCssRuleBuilder();
	
	//constructor
	private TextboxCssRuleBuilder() {}

	//method
	@Override
	protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
		final ITextbox textbox,
		final ControlState state,
		final LinkedList<? super ICssRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
		final ITextbox textbox,	
		final LinkedList<? super ICssRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
		final ITextbox control,
		final LinkedList<CssProperty> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCssPropertiesForExtendedControlAndStateIntoList(
		final ITextbox textbox,
		final ControlState state,
		final LinkedList<ICssProperty> list
	) {
		//Does nothing.
	}
}
