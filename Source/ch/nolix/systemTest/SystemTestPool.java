//package declaration
package ch.nolix.systemTest;

import ch.nolix.common.baseTest.TestPool;
import ch.nolix.systemTest.neuronTest.NeuronTestPool;

//class
public final class SystemTestPool extends TestPool {
	
	//constructor
	public SystemTestPool() {
		addTestPool(new NeuronTestPool());
	}
}
