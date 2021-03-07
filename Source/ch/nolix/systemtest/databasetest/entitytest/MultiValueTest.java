//package declaration
package ch.nolix.systemtest.databasetest.entitytest;

import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.Test;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.entity.MultiValue;

//class
public final class MultiValueTest extends Test {
	
	//method
	@TestCase
	public void testCase_getSpecificationAsAttribute_whenContainsValues() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final MultiValue<String> testUnit = new MultiValue<>();
		};
		entity.extractProperties();
		entity.testUnit.addValue("a", "b", "c");
		
		//execution
		final var result = entity.testUnit.getSpecificationAsAttribute();
		
		//verification
		expect(result.toString()).isEqualTo("testUnit(a,b,c)");
	}
	
	//method
	@TestCase
	public void testCase_getSpecificationAsAttribute_whenIsEmpty() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final MultiValue<String> testUnit = new MultiValue<>();
		};
		entity.extractProperties();
		entity.testUnit.clear();
		
		//execution
		final var result = entity.testUnit.getSpecificationAsAttribute();
		
		//verification
		expect(result.toString()).isEqualTo("testUnit");
	}
	
	//method
	@TestCase
	public void testCase_getSpecificationWithoutHeadern_whenContainsValues() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final MultiValue<String> testUnit = new MultiValue<>();
		};
		entity.testUnit.addValue("a", "b", "c");
		
		//execution
		final var result = entity.testUnit.getSpecificationWithoutHeader();
		
		//verification
		expect(result.toString()).isEqualTo("(a,b,c)");
	}
	
	//method
	@TestCase
	public void testCase_getSpecificationWithoutHeader_whenIsEmpty() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final MultiValue<String> testUnit = new MultiValue<>();
		};
		entity.testUnit.clear();
		
		//execution
		final var result = entity.testUnit.getSpecificationWithoutHeader();
		
		//verification
		expect(result.toString()).isEqualTo("");
	}
}
