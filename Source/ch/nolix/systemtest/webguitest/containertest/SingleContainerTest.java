//package declaration
package ch.nolix.systemtest.webguitest.containertest;

//own imports
import ch.nolix.system.webgui.container.SingleContainer;

//class
public final class SingleContainerTest extends ContainerTest<SingleContainer> {
	
	//method
	@Override
	protected SingleContainer createTestUnit() {
		return new SingleContainer();
	}
}
