//package declaration
package ch.nolix.systemtest;

import ch.nolix.common.testing.basetest.TestPool;
import ch.nolix.systemtest.databasetest.DatabaseTestPool;
import ch.nolix.systemtest.dynamicmathtest.DynamicMathTestPool;
import ch.nolix.systemtest.texturetest.TextureTestPool;

//class
public final class SystemTestPool extends TestPool {
	
	//constructor
	public SystemTestPool() {
		super(
			new DatabaseTestPool(),
			new DynamicMathTestPool(),
			new TextureTestPool()
		);
	}
}
