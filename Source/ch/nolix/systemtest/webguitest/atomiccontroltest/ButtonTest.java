//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IButton;

//class
public final class ButtonTest extends ControlTest<IButton> {
	
	//method
	@Override
	protected Button createTestUnit() {
		return new Button();
	}
}
