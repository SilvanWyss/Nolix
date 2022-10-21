//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.core.web.css.CSSRule;
import ch.nolix.coreapi.webapi.cssapi.CSSPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CSSUnitCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class VerticalStackCSSRuleBuilder
extends ExtendedControlCSSRuleBuilder<VerticalStack, VerticalStackStyle> {
	
	//static attribute
	public static final VerticalStackCSSRuleBuilder INSTANCE = new VerticalStackCSSRuleBuilder();
	
	//constructor
	private VerticalStackCSSRuleBuilder() {}

	//method
	@Override
	protected void fillUpAdditionalCSSRulesForStateIntoList(
		final VerticalStack verticalStack,
		final ControlState state,
		final LinkedList<? super ICSSRule<?>> list
	) {
		list.addAtEnd(
			CSSRule.withSelectorAndProperties(
				getCSSSelectorForBaseState(verticalStack) + " ." + VerticalStackHTMLCreator.CHILD_CONTROL_CSS_CLASS_NAME,
				LinkedList.withElements(
					CSSProperty.withNameAndValue(
						CSSPropertyNameCatalogue.MARGIN,
						verticalStack.getRefStyle().getChildControlMarginWhenHasState(state)
						+ CSSUnitCatalogue.PX
					)
				)
			)
		);
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForBaseStateIntoList(
		final VerticalStack verticalStack,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void fillUpExtendedControlCSSPropertiesForStateIntoList(
		final VerticalStack verticalStack,
		final ControlState state,
		final LinkedList<CSSProperty> list
	) {
		//Does nothing.
	}
}
