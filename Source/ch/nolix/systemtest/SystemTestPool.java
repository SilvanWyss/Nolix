//package declaration
package ch.nolix.systemtest;

import ch.nolix.common.basetest.TestPool;
import ch.nolix.systemtest.datatypetest.DataTypesTestPool;
import ch.nolix.systemtest.neurontest.NeuronTestPool;
import ch.nolix.systemtest.texturetest.TextureTestPool;

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
