//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCSSRuleCreator;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//class
public final class HorizontalStackCSSRuleCreator
extends ExtendedControlCSSRuleCreator<HorizontalStack, HorizontalStackStyle> {
	
	//static method
	public static HorizontalStackCSSRuleCreator forHorizontalStack(final HorizontalStack button) {
		return new HorizontalStackCSSRuleCreator(button);
	}
	
	//constructor
	private HorizontalStackCSSRuleCreator(final HorizontalStack parentHorizontalStack) {
		super(parentHorizontalStack);
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesForStateIntoList(
		final ControlState state,
		final LinkedList<? super ICSSRule<?>> list
	) {
		//TODO: Implement.
	}
	
	//method
	@Override
	protected void fillUpAdditionalCSSRulesIntoList(final LinkedList<? super ICSSRule<?>> list) {
		//TODO: Implement.
	}
	
	//method
	@Override
	protected void fillUpExtendedControlCSSPropertiesForStateIntoList(
		final ControlState state,
		final LinkedList<CSSProperty> list
	) {
		//TODO: Implement.
	}
}
