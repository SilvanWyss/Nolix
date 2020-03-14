//package declaration
package ch.nolix.systemTest;

//own imports
import ch.nolix.common.baseTest.TestPool;
import ch.nolix.systemTest.dataTypesTest.DataTypesTestPool;
import ch.nolix.systemTest.neuronTest.NeuronTestPool;
import ch.nolix.systemTest.textureTest.TextureTestPool;

//class
public final class SystemTestPool extends TestPool {
	
	//constructor
	public SystemTestPool() {
		super(
			new DataTypesTestPool(),
			new NeuronTestPool(),
			new TextureTestPool()
		);
	}
}
