//package declaration
package ch.nolix.systemtest.webguitest.controltest;

//own imports
import ch.nolix.system.webgui.control.Button;

//class
public final class ButtonTest extends ControlTest<Button> {
	
	//method
	@Override
	protected Button createTestUnit() {
		return new Button();
	}
}
