//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.core.web.css.CSSRule;
import ch.nolix.coreapi.webapi.cssapi.CSSFloat;
import ch.nolix.coreapi.webapi.cssapi.CSSPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CSSUnitCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCSSRuleCreator;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class FloatContainerCSSRuleCreator
extends ExtendedControlCSSRuleCreator<FloatContainer, FloatContainerStyle> {
	
	//static method
	public static FloatContainerCSSRuleCreator forFloatContainer(final FloatContainer button) {
		return new FloatContainerCSSRuleCreator(button);
	}
	
	//constructor
	private FloatContainerCSSRuleCreator(final FloatContainer parentFloatContainer) {
		super(parentFloatContainer);
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForBaseStateIntoList(final LinkedList<? super ICSSRule<?>> list) {
		list.addAtEnd(
			CSSRule.withSelectorAndProperties(
				getCSSSelectorForBaseState() + " ." + FloatContainerHTMLCreator.CHILD_CONTROL_CSS_CLASS_NAME,
				LinkedList.withElements(
					CSSProperty.withNameAndValue(
						CSSPropertyNameCatalogue.FLOAT,
						CSSFloat.LEFT
					)
				)
			)
		);
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForStateIntoList(
		final ControlState state,
		final LinkedList<? super ICSSRule<?>> list
	) {
		list.addAtEnd(
			CSSRule.withSelectorAndProperties(
				getCSSSelectorForBaseState() + " ." + FloatContainerHTMLCreator.CHILD_CONTROL_CSS_CLASS_NAME,
				LinkedList.withElements(
					CSSProperty.withNameAndValue(
						CSSPropertyNameCatalogue.MARGIN,
						getRefParentControl().getRefStyle().getChildControlMarginWhenHasState(state)
						+ CSSUnitCatalogue.PX
					)
				)
			)
		);
	}
	
	//method
	@Override
	protected void fillUpExtendedControlCSSPropertiesForStateIntoList(
		final ControlState state,
		final LinkedList<CSSProperty> list
	) {
		//Does nothing.
	}
}
