//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.webapi.cssapi.CssUnitCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.system.webgui.controlhelper.ControlCSSValueHelper;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCssRuleBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class GridContainerCssRuleBuilder
extends ExtendedControlCssRuleBuilder<GridContainer, GridContainerStyle> {
	
	//static attribute
	public static final GridContainerCssRuleBuilder INSTANCE = new GridContainerCssRuleBuilder();
	
	//constant
	private static final ControlCSSValueHelper CONTROL_CSS_VALUE_HELPER = new ControlCSSValueHelper(); 
	
	//constructor
	private GridContainerCssRuleBuilder() {}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForControlAndAllStatesIntoList(
		final GridContainer control,
		final LinkedList<CssProperty> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForExtendedControlAndStateIntoList(
		final GridContainer control,
		final ControlState state,
		final LinkedList<ICssProperty> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndStateIntoList(
		final GridContainer control,
		final ControlState state,
		final LinkedList<? super ICssRule<?>> list
	) {
		
		final var style = control.getOriStyle();
		final var gridThickness = style.getGridThicknessWhenHasState(state);
		final var gridcolor = style.getGridColorWhenHasState(state);
		final var childControlMargin = style.getChildControlMarginWhenHasState(state);
		
		list.addAtEnd(
			CssRule.withSelectorAndProperties(
				getCSSSelectorForControlAndState(control, state) + " table, th, td",
				ImmutableList.withElements(
					CssProperty.withNameAndValue("border-collapse", "collapse"),
					CssProperty.withNameAndValue("border", "solid " + gridThickness + CssUnitCatalogue.PX),
					CssProperty.withNameAndValue("border-color", CONTROL_CSS_VALUE_HELPER.getCSSValueFromColor(gridcolor))
				)
			)
		);
		
		list.addAtEnd(
			CssRule.withSelectorAndProperties(
				getCSSSelectorForControlAndAllStates(control)
				+ " "
				+ HtmlElementTypeCatalogue.TD,
				ImmutableList.withElements(
					CssProperty.withNameAndValue("padding", childControlMargin + CssUnitCatalogue.PX)
				)
			)
		);
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndAllStatesIntoList(
		final GridContainer control,
		final LinkedList<? super ICssRule<?>> list
	) {
		//Does nothing.
	}
}
