//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCSSRuleCreator;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//class
public final class LinearContainerCSSRuleCreator
extends ExtendedControlCSSRuleCreator<LinearContainer, LinearContainerStyle> {
	
	//static method
	public static LinearContainerCSSRuleCreator forLinearContainer(final LinearContainer button) {
		return new LinearContainerCSSRuleCreator(button);
	}
	
	//constructor
	private LinearContainerCSSRuleCreator(final LinearContainer parentLinearContainer) {
		super(parentLinearContainer);
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
