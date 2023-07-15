//package declaration
package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.webapi.cssapi.CssFloatCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CssUnitCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCssRuleBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class FloatContainerCssRuleBuilder
extends ExtendedControlCssRuleBuilder<FloatContainer, FloatContainerStyle> {
	
	//method
	@Override
	protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
		final FloatContainer floatContainer,
		final LinkedList<? super ICssRule<?>> list
	) {
		list.addAtEnd(
			CssRule.withSelectorAndProperties(
				getCssSelectorForControlAndAllStates(floatContainer)
				+ " ."
				+ FloatContainerHtmlBuilder.CHILD_CONTROL_CSS_CLASS_NAME,
				LinkedList.withElements(
					CssProperty.withNameAndValue(
						CssPropertyNameCatalogue.FLOAT,
						CssFloatCatalogue.LEFT
					)
				)
			)
		);
	}
	
	//method
	@Override
	protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
		final FloatContainer floatContainer,
		final ControlState state,
		final LinkedList<? super ICssRule<?>> list
	) {
		list.addAtEnd(
			CssRule.withSelectorAndProperties(
				getCssSelectorForControlAndAllStates(floatContainer)
				+ " ."
				+ FloatContainerHtmlBuilder.CHILD_CONTROL_CSS_CLASS_NAME,
				LinkedList.withElements(
					CssProperty.withNameAndValue(
						CssPropertyNameCatalogue.MARGIN,
						floatContainer.getStoredStyle().getChildControlMarginWhenHasState(state)
						+ CssUnitCatalogue.PX
					)
				)
			)
		);
	}
	
	//method
	@Override
	protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
		final FloatContainer control,
		final LinkedList<CssProperty> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCssPropertiesForExtendedControlAndStateIntoList(
		final FloatContainer floatContainer,
		final ControlState state,
		final LinkedList<ICssProperty> list
	) {
		//Does nothing.
	}
}
