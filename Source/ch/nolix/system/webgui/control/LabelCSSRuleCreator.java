//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCSSRuleCreator;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//class
public final class LabelCSSRuleCreator extends ExtendedControlCSSRuleCreator<Label, LabelStyle> {
	
	//static method
	public static LabelCSSRuleCreator forLabel(final Label button) {
		return new LabelCSSRuleCreator(button);
	}
	
	//constructor
	private LabelCSSRuleCreator(final Label parentLabel) {
		super(parentLabel);
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
