//package declaration
package ch.nolix.systemTest.databaseAdapterTest;

//own import
import ch.nolix.common.baseTest.TestPool;

//class
public final class DatabaseAdapterTestPool extends TestPool {
	
	//constructor
	public DatabaseAdapterTestPool() {
		addTestClass(EntityTest.class, MultiPropertyTest.class, OptionalPropertyTest.class, PropertyTest.class);
	}
}
