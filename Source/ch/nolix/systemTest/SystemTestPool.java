//package declaration
package ch.nolix.systemTest;

import ch.nolix.core.baseTest.TestPool;
import ch.nolix.systemTest.neuronTest.NeuronTestPool;

//class
public final class SystemTestPool extends TestPool {
	
	//constructor
	public SystemTestPool() {
		addTestPool(new NeuronTestPool());
	}
}
