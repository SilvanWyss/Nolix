//package declaration
package ch.nolix.systemtest.databasetest.entitytest;

import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.entity.EntityAccessor;
import ch.nolix.system.database.entity.OptionalValue;

//class
public final class OptionalValueTest extends Test {
	
	//method
	@TestCase
	public void testCase_getSpecificationAsAttribute_whenHasAValue() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final OptionalValue<String> testUnit = new OptionalValue<>();
		};
		EntityAccessor.extractProperties(entity);
		entity.testUnit.setValue("x");
		
		//execution
		final var result = entity.testUnit.getSpecificationAsAttribute();
		
		//verification
		expect(result.toString()).isEqualTo("testUnit(x)");
	}

	//method
	@TestCase
	public void testCase_getSpecificationAsAttribute_whenIsEmpty() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final OptionalValue<String> testUnit = new OptionalValue<>();
		};
		EntityAccessor.extractProperties(entity);
		entity.testUnit.clear();
		
		//execution
		final var result = entity.testUnit.getSpecificationAsAttribute();
		
		//verification
		expect(result.toString()).isEqualTo("testUnit");
	}
	
	//method
	@TestCase
	public void testCase_getSpecificationWithoutHeader_whenHasAValue() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final OptionalValue<String> testUnit = new OptionalValue<>();
		};
		entity.testUnit.setValue("x");
		
		//execution
		final var result = entity.testUnit.getSpecificationWithoutHeader();
		
		//verification
		expect(result.toString()).isEqualTo("(x)");
	}

	//method
	@TestCase
	public void testCase_getSpecificationWithoutHeader_whenIsEmpty() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final OptionalValue<String> testUnit = new OptionalValue<>();
		};
		entity.testUnit.clear();
		
		//execution
		final var result = entity.testUnit.getSpecificationWithoutHeader();
		
		//verification
		expect(result.toString()).isEqualTo("");
	}
}
