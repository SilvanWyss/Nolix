//package declaration
package ch.nolix.coreTest.databaseAdapterTest;

//own import
import ch.nolix.core.testoid.TestPool;

//class
public final class DatabaseAdapterTestPool extends TestPool {
	
	//constructor
	public DatabaseAdapterTestPool() {
		addTestClass(EntityTest.class);
	}
}
