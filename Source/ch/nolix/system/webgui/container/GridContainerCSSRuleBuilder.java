//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.core.web.css.CSSRule;
import ch.nolix.coreapi.webapi.cssapi.CSSUnitCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.coreapi.webapi.htmlapi.HTMLElementTypeCatalogue;
import ch.nolix.system.webgui.controlhelper.ControlCSSValueHelper;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class GridContainerCSSRuleBuilder
extends ExtendedControlCSSRuleBuilder<GridContainer, GridContainerStyle> {
	
	//static attribute
	public static final GridContainerCSSRuleBuilder INSTANCE = new GridContainerCSSRuleBuilder();
	
	//constant
	private static final ControlCSSValueHelper CONTROL_CSS_VALUE_HELPER = new ControlCSSValueHelper(); 
	
	//constructor
	private GridContainerCSSRuleBuilder() {}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForControlAndAllStatesIntoList(
		final GridContainer control,
		final LinkedList<CSSProperty> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForExtendedControlAndStateIntoList(
		final GridContainer control,
		final ControlState state,
		final LinkedList<ICSSProperty> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndStateIntoList(
		final GridContainer control,
		final ControlState state,
		final LinkedList<? super ICSSRule<?>> list
	) {
		
		final var style = control.getOriStyle();
		final var gridThickness = style.getGridThicknessWhenHasState(state);
		final var gridcolor = style.getGridColorWhenHasState(state);
		final var childControlMargin = style.getChildControlMarginWhenHasState(state);
		
		list.addAtEnd(
			CSSRule.withSelectorAndProperties(
				getCSSSelectorForControlAndState(control, state) + " table, th, td",
				ImmutableList.withElements(
					CSSProperty.withNameAndValue("border-collapse", "collapse"),
					CSSProperty.withNameAndValue("border", "solid " + gridThickness + CSSUnitCatalogue.PX),
					CSSProperty.withNameAndValue("border-color", CONTROL_CSS_VALUE_HELPER.getCSSValueFromColor(gridcolor))
				)
			)
		);
		
		list.addAtEnd(
			CSSRule.withSelectorAndProperties(
				getCSSSelectorForControlAndAllStates(control)
				+ " "
				+ HTMLElementTypeCatalogue.TD,
				ImmutableList.withElements(
					CSSProperty.withNameAndValue("padding", childControlMargin + CSSUnitCatalogue.PX)
				)
			)
		);
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndAllStatesIntoList(
		final GridContainer control,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
}
