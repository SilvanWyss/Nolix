//package declaration
package ch.nolix.system.webgui.control;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCssRuleBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class ValidationLabelCSSRuleBuilder
extends ExtendedControlCssRuleBuilder<ValidationLabel, ValidationLabelStyle> {
	
	//method
	@Override
	protected void fillUpCSSPropertiesForControlAndAllStatesIntoList(
		final ValidationLabel control,
		final LinkedList<CSSProperty> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndStateIntoList(
		final ValidationLabel text,
		final ControlState state,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndAllStatesIntoList(
		final ValidationLabel text,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForExtendedControlAndStateIntoList(
		final ValidationLabel text,
		final ControlState state,
		final LinkedList<ICSSProperty> list
	) {
		//Does nothing.
	}
}
