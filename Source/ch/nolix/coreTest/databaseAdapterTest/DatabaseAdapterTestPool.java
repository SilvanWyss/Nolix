//package declaration
package ch.nolix.coreTest.databaseAdapterTest;

import ch.nolix.core.testoid.TestPool;

//class
public final class DatabaseAdapterTestPool extends TestPool {

	//constructor
	public DatabaseAdapterTestPool() {
		addTestClass(EntityTest.class);
	}
}
