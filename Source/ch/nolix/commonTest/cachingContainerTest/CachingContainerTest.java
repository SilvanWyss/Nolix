//package declaration
package ch.nolix.commonTest.cachingContainerTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.caching.CachingContainer;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.simpleDemoObject.Cat;
import ch.nolix.common.test.Test;

//class
public final class CachingContainerTest extends Test {
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//execution
		final var result = new CachingContainer<Cat>();
		
		//verification
		expect(result.isEmpty());
	}
	
	//method
	@TestCase
	public void testCase_getRefById() {
		
		//setup
		final var testUnit = new CachingContainer<Cat>();
		final var garfield = new Cat("Garfield");
		final var garfieldId = testUnit.registerAndGetId(garfield);
		
		//execution
		final var result = testUnit.getRefById(garfieldId);
		
		//verification
		expect(result).isSameAs(garfield);
	}
	
	//method
	@TestCase
	public void testCase_getRefById_whenForTheGivenIdAnElementIsNotRegistered() {
		
		//setup
		final var testUnit = new CachingContainer<Cat>();
		
		//execution
		expect(() -> testUnit.getRefById("G")).throwsException().ofType(InvalidArgumentException.class);
	}
	
	//method
	@TestCase
	public void testCase_registerAndGetId() {
		
		//setup
		final var testUnit = new CachingContainer<Cat>();
		final var garfield = new Cat("Garfield");
		
		//execution
		final var result = testUnit.registerAndGetId(garfield);
		
		//verification
		expect(testUnit.getElementCount()).isEqualTo(1);
		expect(testUnit.containsWithId(result));
		expect(testUnit.contains(garfield));
	}
	
	//method
	@TestCase
	public void testCase_registerAndGetId_whenTheGivenElementIsAlreadyRegistered() {
		
		//setup
		final var testUnit = new CachingContainer<Cat>();
		final var garfield = new Cat("G");
		testUnit.registerAndGetId(garfield);
		
		//execution & verification
		expect(() -> testUnit.registerAndGetId(garfield)).throwsException();
	}
	
	//method
	@TestCase
	public void testCase_registerAt() {
		
		//setup
		final var testUnit = new CachingContainer<Cat>();
		final var garfieldId = "G";
		final var garfield = new Cat("Garfield");
		
		//execution
		testUnit.registerAtId(garfieldId, garfield);
		
		//verification
		expect(testUnit.getElementCount()).isEqualTo(1);
		expect(testUnit.containsWithId(garfieldId));
		expect(testUnit.contains(garfield));
	}
	
	//method
	@TestCase
	public void testCase_registerAt_whenTheGivenElementIsAlreadyRegistered() {
		
		//setup
		final var testUnit = new CachingContainer<Cat>();
		final var garfieldId = "G";
		final var garfield = new Cat("Garfield");
		testUnit.registerAtId(garfieldId, garfield);
		
		//execution & verification
		expect(() -> testUnit.registerAtId(garfieldId, garfield)).throwsException();
	}
	
	//method
	@TestCase
	public void testCase_registerIfNotRegisterAndGetId() {
		
		//setup
		final var testUnit = new CachingContainer<Cat>();
		final var garfield = new Cat("Garfield");
		
		//execution
		final var result = testUnit.registerIfNotRegisteredAndGetId(garfield);
		
		//verification
		expect(testUnit.getElementCount()).isEqualTo(1);
		expect(testUnit.containsWithId(result));
		expect(testUnit.contains(garfield));
	}
	
	//method
	@TestCase
	public void testCase_registerIfNotRegisterAndGetId_whenTheGivenElementIsAlreadyRegistered() {
		
		//setup
		final var testUnit = new CachingContainer<Cat>();
		final var garfieldId = "G";
		final var garfield = new Cat("Garfield");
		testUnit.registerAtId(garfieldId, garfield);
		
		//execution
		final var result = testUnit.registerIfNotRegisteredAndGetId(garfield);
		
		//verification
		expect(result).isEqualTo(garfieldId);
	}
		
	//method
	@TestCase
	public void testCase_toList_whenContainsElements() {
		
		//setup
		final var testUnit = new CachingContainer<>();
		final var garfield = new Cat("Garfield");
		final var simba = new Cat("Simba");
		testUnit.registerAndGetId(garfield);
		testUnit.registerAndGetId(simba);
		
		//execution
		final var result = testUnit.toList();
		
		//verification
		expect(result.getElementCount()).isEqualTo(2);
		expect(result.containsAll(garfield, simba));
	}
	
	//method
	@TestCase
	public void testCase_toList_whenIsEmpty() {
		
		//setup
		final var testUnit = new CachingContainer<>();
		
		//setup verification
		expect(testUnit.isEmpty());
		
		//execution
		final var result = testUnit.toList();
		
		//verification
		expect(result.isEmpty());
	}
}
