//package declaration
package ch.nolix.systemTest.neuronTest;

import ch.nolix.core.baseTest.TestPool;

//class
public final class NeuronTestPool extends TestPool {
	
	//constructor
	public NeuronTestPool() {
		addTestClass(SourceNeuronTest.class);
	}
}
