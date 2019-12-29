//package declaration
package ch.nolix.systemTest.entityTest;

//own import
import ch.nolix.common.baseTest.TestPool;

//class
public final class EntityTestPool extends TestPool {
	
	//constructor
	public EntityTestPool() {
		addTestClass(
			EntityTest.class,
			EntityWithBackReferenceTest.class,
			MultiValuePropertyTest.class,
			OptionalValuePropertyTest.class,
			ValuePropertyTest.class
		);
	}
}
