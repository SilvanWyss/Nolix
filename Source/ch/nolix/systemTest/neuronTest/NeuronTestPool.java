//package declaration
package ch.nolix.systemTest.neuronTest;

import ch.nolix.common.baseTest.TestPool;

//class
public final class NeuronTestPool extends TestPool {
	
	//constructor
	public NeuronTestPool() {
		addTestClass(SourceNeuronTest.class);
	}
}
