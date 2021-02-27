//package declaration
package ch.nolix.systemtest;

//own imports
import ch.nolix.common.basetest.TestPool;
import ch.nolix.systemtest.databasetest.DatabaseTestPool;
import ch.nolix.systemtest.dynamicmathtest.DynamicMathTestPool;
import ch.nolix.systemtest.neurontest.NeuronTestPool;
import ch.nolix.systemtest.texturetest.TextureTestPool;

//class
public final class SystemTestPool extends TestPool {
	
	//constructor
	public SystemTestPool() {
		super(
			new DatabaseTestPool(),
			new DynamicMathTestPool(),
			new NeuronTestPool(),
			new TextureTestPool()
		);
	}
}
