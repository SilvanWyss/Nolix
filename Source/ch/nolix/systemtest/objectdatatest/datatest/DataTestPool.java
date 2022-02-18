//package declaration
package ch.nolix.systemtest.objectdatatest.datatest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class DataTestPool extends TestPool {
	
	//constructor
	public DataTestPool() {
		super(EntityTest.class, ValueTest.class);
	}
}
