//package declaration
package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.core.web.css.CSSRule;
import ch.nolix.coreapi.webapi.cssapi.CSSPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CSSUnitCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IVerticalStack;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IVerticalStackStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class VerticalStackCSSRuleBuilder
extends ExtendedControlCSSRuleBuilder<IVerticalStack, IVerticalStackStyle> {
	
	//static attribute
	public static final VerticalStackCSSRuleBuilder INSTANCE = new VerticalStackCSSRuleBuilder();
	
	//constructor
	private VerticalStackCSSRuleBuilder() {}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndStateIntoList(
		final IVerticalStack verticalStack,
		final ControlState state,
		final LinkedList<? super ICSSRule<?>> list
	) {
		list.addAtEnd(
			CSSRule.withSelectorAndProperties(
				getCSSSelectorForControlAndAllStates(verticalStack) + " > div",
				LinkedList.withElements(
					CSSProperty.withNameAndValue(
						CSSPropertyNameCatalogue.MARGIN_BOTTOM,
						verticalStack.getOriStyle().getChildControlMarginWhenHasState(state)
						+ CSSUnitCatalogue.PX
					)
				)
			)
		);
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndAllStatesIntoList(
		final IVerticalStack verticalStack,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForControlAndAllStatesIntoList(
		final IVerticalStack control,
		final LinkedList<CSSProperty> list
	) {
		list.addAtEnd(CSSProperty.withNameAndValue("overflow", "auto"));
	}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForExtendedControlAndStateIntoList(
		final IVerticalStack verticalStack,
		final ControlState state,
		final LinkedList<ICSSProperty> list
	) {
		//Does nothing.
	}
}
