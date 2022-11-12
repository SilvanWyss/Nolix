//package declaration
package ch.nolix.systemtest.webguitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.webguitest.containertest.ContainerTestPool;
import ch.nolix.systemtest.webguitest.controltest.ControlTestPool;

//class
public final class WebGUITestPool extends TestPool {
	
	//constructor
	public WebGUITestPool() {
		super(
			new ContainerTestPool(),
			new ControlTestPool()
		);
	}
}
