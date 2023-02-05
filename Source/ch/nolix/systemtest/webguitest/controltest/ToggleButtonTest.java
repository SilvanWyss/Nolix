//package declaration
package ch.nolix.systemtest.webguitest.controltest;

//own imports
import ch.nolix.system.webgui.control.ToggleButton;
import ch.nolix.systemapi.webguiapi.controlapi.IToggleButton;

//class
public final class ToggleButtonTest extends ControlTest<IToggleButton> {
	
	//method
	@Override
	protected IToggleButton createTestUnit() {
		return new ToggleButton();
	}
}
