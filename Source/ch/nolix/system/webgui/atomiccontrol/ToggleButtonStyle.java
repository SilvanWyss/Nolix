//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.system.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IToggleButtonStyle;

//class
public final class ToggleButtonStyle extends ControlStyle<IToggleButtonStyle> implements IToggleButtonStyle {
	
	//constructor
	public ToggleButtonStyle() {
		initialize();
	}
}
