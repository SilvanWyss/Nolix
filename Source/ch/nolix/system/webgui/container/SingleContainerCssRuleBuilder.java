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
	
	//method
	@Override
	protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
		final SingleContainer control,
		final LinkedList<? super ICssRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
		final SingleContainer control,
		final ControlState state,
		final LinkedList<? super ICssRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
		final SingleContainer control,
		final LinkedList<CssProperty> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCssPropertiesForExtendedControlAndStateIntoList(
		final SingleContainer control,
		final ControlState state,
		final LinkedList<ICssProperty> list
	) {
		//Does nothing.
	}
}
