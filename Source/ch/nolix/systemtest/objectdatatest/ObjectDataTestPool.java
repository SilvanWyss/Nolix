//package declaration
package ch.nolix.systemtest.objectdatatest;

import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.objectdatatest.datatest.DataTestPool;

//class
public final class ObjectDataTestPool extends TestPool {
	
	//constructor
	public ObjectDataTestPool() {
		super(new DataTestPool());
	}
}
