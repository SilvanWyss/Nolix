//package declaration
package ch.nolix.systemtest.databasetest.entitytest;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.database.entity.BackReference;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.entity.Reference;

//class
public final class EntityWithBackReferenceTest extends Test {
	
	//static class
	@SuppressWarnings("unused")
	private static final class Entity1A extends Entity {
		
		//attribute
		@SuppressWarnings("unused")
		private final Reference<Entity1B> reference = new Reference<>();
	}
	
	//static class
	private static final class Entity1B extends Entity {
		
		//attribute
		@SuppressWarnings("unused")
		private final BackReference<Entity1A> backReference = new BackReference<>("reference");
	}
	
	//static class
	private static final class Entity1C extends Entity {
		
		//attribute
		@SuppressWarnings("unused")
		private final BackReference<Entity1A> backReference = new BackReference<>("reference2");
	}
	
	//method
	@TestCase
	public void testCase_extractProperties_whenTheBackReferencedReferenceDoesNotExist() {
		
		//setup
		final var testUnit = new Entity1C();
		
		//execution & verification
		expectRunning(testUnit::extractProperties)
		.throwsException()
		.ofType(ArgumentDoesNotHaveAttributeException.class)
		.withMessage("The given Class '" + Entity1A.class + "' does not have a reference2.");
	}
	
	//method
	@TestCase
	public void testCase_extractProperties_whenTheBackReferencedReferenceExists() {
		
		//setup
		final var testUnit = new Entity1B();
		
		//execution & verification
		expectRunning(testUnit::extractProperties).doesNotThrowException();
	}
}
