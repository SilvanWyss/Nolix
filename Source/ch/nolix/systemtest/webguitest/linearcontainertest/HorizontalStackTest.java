//package declaration
package ch.nolix.systemtest.webguitest.linearcontainertest;

//own imports
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStack;
import ch.nolix.systemtest.webguitest.atomiccontroltest.ControlTest;

//class
public final class HorizontalStackTest extends ControlTest<IHorizontalStack> {
	
	//method
	@Override
	protected IHorizontalStack createTestUnit() {
		return new HorizontalStack();
	}
}
