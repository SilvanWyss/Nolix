//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.system.webgui.controlstyle.ExtendedControlCSSRuleCreator;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//class
public final class ImageControlCSSRuleCreator extends ExtendedControlCSSRuleCreator<ImageControl, ImageControlStyle> {
	
	//static method
	public static ImageControlCSSRuleCreator forImageControl(final ImageControl imageControl) {
		return new ImageControlCSSRuleCreator(imageControl);
	}
	
	//constructor
	private ImageControlCSSRuleCreator(final ImageControl imageControl) {
		super(imageControl);
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
