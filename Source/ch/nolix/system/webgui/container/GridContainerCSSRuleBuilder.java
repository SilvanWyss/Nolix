//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class GridContainerCSSRuleBuilder
extends ExtendedControlCSSRuleBuilder<GridContainer, GridContainerStyle> {
	
	//static attribute
	public static final GridContainerCSSRuleBuilder INSTANCE = new GridContainerCSSRuleBuilder();
	
	//constructor
	private GridContainerCSSRuleBuilder() {}
	
	//method
	@Override
	protected void fillUpCSSPropertiesForAllStatesOfControlIntoList(
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
		final LinkedList<CSSProperty> list
	) {
		//TODO: Implement.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForControlAndStateIntoList(
		final GridContainer control,
		final ControlState state,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//TODO: Implement.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForAllStatesOfControlIntoList(
		final GridContainer control,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
}
