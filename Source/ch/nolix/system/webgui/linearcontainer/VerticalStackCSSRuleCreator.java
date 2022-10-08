//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCSSRuleCreator;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//class
public final class VerticalStackCSSRuleCreator
extends ExtendedControlCSSRuleCreator<VerticalStack, VerticalStackStyle> {
	
	//static method
	public static VerticalStackCSSRuleCreator forVerticalStack(final VerticalStack button) {
		return new VerticalStackCSSRuleCreator(button);
	}
	
	//constructor
	private VerticalStackCSSRuleCreator(final VerticalStack parentVerticalStack) {
		super(parentVerticalStack);
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
