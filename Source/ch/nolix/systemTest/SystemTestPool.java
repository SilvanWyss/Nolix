//package declaration
package ch.nolix.systemTest;

//own imports
import ch.nolix.common.baseTest.TestPool;
import ch.nolix.systemTest.dataTypesTest.DataTypesTestPool;
import ch.nolix.systemTest.neuronTest.NeuronTestPool;

//class
public final class SystemTestPool extends TestPool {
	
	//constructor
	public SystemTestPool() {
		addTestPool(new DataTypesTestPool(), new NeuronTestPool());
	}
}
