//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

import ch.nolix.system.webgui.atomiccontrol.ToggleButton;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IToggleButton;

//class
public final class ToggleButtonTest extends ControlTest<IToggleButton> {
	
	//method
	@Override
	protected IToggleButton createTestUnit() {
		return new ToggleButton();
	}
}
