//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class DatabaseTestPool extends TestPool {
	
	//constructor
	public DatabaseTestPool() {
		super(EntityTest.class, OptionalValueTest.class, ValueTest.class);
	}
}
