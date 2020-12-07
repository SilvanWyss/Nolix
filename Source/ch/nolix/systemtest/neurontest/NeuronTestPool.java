//package declaration
package ch.nolix.systemtest.neurontest;

//own imports
import ch.nolix.common.basetest.TestPool;

//class
public final class NeuronTestPool extends TestPool {
	
	//constructor
	public NeuronTestPool() {
		super(SourceNeuronTest.class);
	}
}
