//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.controlstyle.ExtendedControlStyle;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.webguiapi.controlapi.IValidationLabelStyle;

//class
public final class ValidationLabelStyle
extends ExtendedControlStyle<ValidationLabelStyle>
implements IValidationLabelStyle<ValidationLabelStyle> {
	
	//constant
	public static final Color DEFAULT_TEXT_COLOR = Color.RED;
	
	//constructor
	public ValidationLabelStyle() {
		initialize();
	}
	
	//method
	@Override
	public IColor getDefaultTextColor() {
		return DEFAULT_TEXT_COLOR;
	}
}
