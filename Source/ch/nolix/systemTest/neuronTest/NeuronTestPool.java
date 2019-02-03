//package declaration
package ch.nolix.systemTest.neuronTest;

//own import
import ch.nolix.core.testoid.TestPool;

//class
public final class NeuronTestPool extends TestPool {
	
	//constructor
	public NeuronTestPool() {
		addTestClass(SourceNeuronTest.class);
	}
}
