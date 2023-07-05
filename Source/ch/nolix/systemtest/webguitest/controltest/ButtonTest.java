//package declaration
package ch.nolix.systemtest.webguitest.controltest;

//own imports
import ch.nolix.system.webgui.control.Button;
import ch.nolix.systemapi.webguiapi.controlapi.IButton;

//class
public final class ButtonTest extends ControlTest<IButton> {
	
	//method
	@Override
	protected Button createTestUnit() {
		return new Button();
	}
}
