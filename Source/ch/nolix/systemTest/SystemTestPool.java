//package declaration
package ch.nolix.systemTest;

//own imports
import ch.nolix.core.testoid.TestPool;
import ch.nolix.systemTest.neuronTest.NeuronTestPool;

//class
public final class SystemTestPool extends TestPool {
	
	//constructor
	public SystemTestPool() {
		addTestPool(new NeuronTestPool());
	}
}
