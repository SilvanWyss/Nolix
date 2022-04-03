//package declaration
package ch.nolix.systemtest;

import ch.nolix.businesstest.dynamicmathtest.DynamicMathTestPool;
//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.objectdatatest.ObjectDataTestPool;
import ch.nolix.systemtest.objectdatatest.datatest.DataTestPool;
import ch.nolix.systemtest.objectschematest.ObjectSchemaTestPool;
import ch.nolix.systemtest.texturetest.TextureTestPool;

//class
public final class SystemTestPool extends TestPool {
	
	//constructor
	public SystemTestPool() {
		super(
			new DataTestPool(),
			new ObjectDataTestPool(),
			new ObjectSchemaTestPool(),
			new TextureTestPool()
		);
	}
}
