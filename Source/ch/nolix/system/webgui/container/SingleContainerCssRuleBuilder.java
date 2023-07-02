//package declaration
package ch.nolix.system.webgui.container;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCssRuleBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class SingleContainerCssRuleBuilder
extends ExtendedControlCssRuleBuilder<SingleContainer, SingleContainerStyle> {
	
	//static attribute
	public static final SingleContainerCssRuleBuilder INSTANCE = new SingleContainerCssRuleBuilder();
	
	//constructor
	private SingleContainerCssRuleBuilder() {}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndAllStatesIntoList(
		final SingleContainer control,
		final LinkedList<? super ICssRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndStateIntoList(
		final SingleContainer control,
		final ControlState state,
		final LinkedList<? super ICssRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForControlAndAllStatesIntoList(
		final SingleContainer control,
		final LinkedList<CssProperty> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForExtendedControlAndStateIntoList(
		final SingleContainer control,
		final ControlState state,
		final LinkedList<ICssProperty> list
	) {
		//Does nothing.
	}
}
