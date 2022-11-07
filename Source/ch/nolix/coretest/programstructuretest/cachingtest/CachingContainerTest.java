//package declaration
package ch.nolix.coretest.programstructuretest.cachingtest;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programstructure.caching.CachingContainer;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class CachingContainerTest extends Test {
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//execution
		final var result = new CachingContainer<String>();
		
		//verification
		expect(result.isEmpty());
	}
	
	//method
	@TestCase
	public void testCase_getRefById() {
		
		//setup
		final var testUnit = new CachingContainer<String>();
		final var garfield = "Garfield";
		final var garfieldId = testUnit.registerAndGetId(garfield);
		
		//execution
		final var result = testUnit.getRefById(garfieldId);
		
		//verification
		expect(result).is(garfield);
	}
	
	//method
	@TestCase
	public void testCase_getRefById_whenForTheGivenIdAnElementIsNotRegistered() {
		
		//setup
		final var testUnit = new CachingContainer<String>();
		
		//execution
		expectRunning(() -> testUnit.getRefById("G")).throwsException().ofType(InvalidArgumentException.class);
	}
	
	//method
	@TestCase
	public void testCase_registerAndGetId() {
		
		//setup
		final var testUnit = new CachingContainer<String>();
		final var garfield = "Garfield";
		
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
		final var testUnit = new CachingContainer<String>();
		final var garfield = "Garfield";
		testUnit.registerAndGetId(garfield);
		
		//execution & verification
		expectRunning(() -> testUnit.registerAndGetId(garfield)).throwsException();
	}
	
	//method
	@TestCase
	public void testCase_registerAt() {
		
		//setup
		final var testUnit = new CachingContainer<String>();
		final var garfieldId = "G";
		final var garfield = "Garfield";
		
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
		final var testUnit = new CachingContainer<String>();
		final var garfieldId = "G";
		final var garfield = "Garfield";
		testUnit.registerAtId(garfieldId, garfield);
		
		//execution & verification
		expectRunning(() -> testUnit.registerAtId(garfieldId, garfield)).throwsException();
	}
	
	//method
	@TestCase
	public void testCase_registerIfNotRegisterAndGetId() {
		
		//setup
		final var testUnit = new CachingContainer<String>();
		final var garfield = "Garfield";
		
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
		final var testUnit = new CachingContainer<String>();
		final var garfieldId = "G";
		final var garfield = "Garfield";
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
		final var garfield = "Garfield";
		final var simba = "Simba";
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
