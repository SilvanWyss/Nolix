//package declaration
package ch.nolix.system.webgui.control;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCssRuleBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class LabelCssRuleBuilder extends ExtendedControlCssRuleBuilder<Label, LabelStyle> {
	
	//static attribute
	public static final LabelCssRuleBuilder INSTANCE = new LabelCssRuleBuilder();
	
	//constructor
	private LabelCssRuleBuilder() {}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForControlAndAllStatesIntoList(
		final Label control,
		final LinkedList<CssProperty> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndStateIntoList(
		final Label label,
		final ControlState state,
		final LinkedList<? super ICssRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndAllStatesIntoList(
		final Label label,
		final LinkedList<? super ICssRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForExtendedControlAndStateIntoList(
		final Label label,
		final ControlState state,
		final LinkedList<ICssProperty> list
	) {
		//Does nothing.
	}
}
