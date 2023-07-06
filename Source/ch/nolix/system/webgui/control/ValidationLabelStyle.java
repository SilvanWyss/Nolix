//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.system.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.webguiapi.controlapi.IValidationLabelStyle;

//class
public final class ValidationLabelStyle
extends ControlStyle<IValidationLabelStyle>
implements IValidationLabelStyle {
	
	//constructor
	public ValidationLabelStyle() {
		initialize();
	}
}
