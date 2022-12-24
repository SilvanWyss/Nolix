//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class DatabaseTestPool extends TestPool {
	
	//constructor
	public DatabaseTestPool() {
		super(
			BackReferenceOnDatabaseTest.class,
			EntityTest.class,
			EntityOnDatabaseTest.class,
			MultiReferenceOnDatabaseTest.class,
			MultiReferenceWithBackReferenceTest.class,
			OptionalBackReferenceOnDatabaseTest.class,
			OptionalReferenceOnDatabaseTest.class,
			OptionalValueTest.class,
			OptionalValueOnDatabaseTest.class,
			ReferenceOnDatabaseTest.class,
			TableTest.class,
			ValueTest.class,
			ValueOnDatabaseTest.class
		);
	}
}
