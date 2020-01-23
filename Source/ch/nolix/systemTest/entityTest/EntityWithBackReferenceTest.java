//package declaration
package ch.nolix.systemTest.entityTest;

//own imports
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.test.Test;
import ch.nolix.system.entity.BackReference;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.EntityAccessor;
import ch.nolix.system.entity.Reference;

//test class
public final class EntityWithBackReferenceTest extends Test {
	
	//static class
	@SuppressWarnings("unused")
	private static final class Entity1A extends Entity {
		
		//attribute
		private final Reference<Entity1B> reference = new Reference<>();
	}
	
	//static class
	private static final class Entity1B extends Entity {
		
		//attribute
		@SuppressWarnings("unused")
		private final BackReference<Entity1A> backReference = new BackReference<>("reference");
	}
	
	//test case
	public void testCase_extractProperties_whenTheBackReferencedReferenceDoesNotExist() {
		
		//setup
		final var testUnit = new Entity1B();
		
		//execution & verification
		expect(() -> EntityAccessor.extractProperties(testUnit))
		.throwsException()
		.ofType(ArgumentDoesNotHaveAttributeException.class);
	}
}
