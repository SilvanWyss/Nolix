//package declaration
package ch.nolix.systemtest.webguitest.containertest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.systemapi.webguiapi.containerapi.IContainer;
import ch.nolix.systemtest.webguitest.atomiccontroltest.ControlTest;

//class
public abstract class ContainerTest<C extends IContainer<C, ?>> extends ControlTest<C> {
	
	//method
	@TestCase
	public final void testCase_constructor() {
		
		//execution
		final var testUnit = createTestUnit();
		
		//verification
		expect(testUnit.isEmpty());
	}
}
