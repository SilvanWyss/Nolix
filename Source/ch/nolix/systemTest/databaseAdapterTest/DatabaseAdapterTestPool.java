//package declaration
package ch.nolix.systemTest.databaseAdapterTest;

import ch.nolix.core.baseTest.TestPool;

//class
public final class DatabaseAdapterTestPool extends TestPool {
	
	//constructor
	public DatabaseAdapterTestPool() {
		addTestClass(EntityTest.class);
	}
}
