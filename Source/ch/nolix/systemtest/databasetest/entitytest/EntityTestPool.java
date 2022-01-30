//package declaration
package ch.nolix.systemtest.databasetest.entitytest;

import ch.nolix.core.testing.basetest.TestPool;

//class
public final class EntityTestPool extends TestPool {
	
	//constructor
	public EntityTestPool() {
		super(
			EntityWithBackReferenceTest.class,
			MultiValueTest.class,
			OptionalValueTest.class,
			ValueTest.class
		);
	}
}
