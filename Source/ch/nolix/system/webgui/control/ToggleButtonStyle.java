//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.system.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.webguiapi.controlapi.IToggleButtonStyle;

//class
public final class ToggleButtonStyle extends ControlStyle<IToggleButtonStyle> implements IToggleButtonStyle {
	
	//constructor
	public ToggleButtonStyle() {
		initialize();
	}
}
