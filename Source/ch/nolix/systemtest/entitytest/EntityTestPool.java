//package declaration
package ch.nolix.systemtest.entitytest;

//own imports
import ch.nolix.common.basetest.TestPool;

//class
public final class EntityTestPool extends TestPool {
	
	//constructor
	public EntityTestPool() {
		super(
			EntityWithBackReferenceTest.class,
			MultiValueTest.class,
			OptionalValuePropertyTest.class,
			ValuePropertyTest.class
		);
	}
}
