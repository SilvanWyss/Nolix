//package declaration
package ch.nolix.systemtest.entitytest;

//own import
import ch.nolix.common.baseTest.TestPool;

//class
public final class EntityTestPool extends TestPool {
	
	//constructor
	public EntityTestPool() {
		super(
			EntityWithBackReferenceTest.class,
			MultiValuePropertyTest.class,
			OptionalValuePropertyTest.class,
			ValuePropertyTest.class
		);
	}
}
