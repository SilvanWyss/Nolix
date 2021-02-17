//package declaration
package ch.nolix.systemtest.entitytest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.test.Test;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.EntityAccessor;
import ch.nolix.system.entity.OptionalValue;

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
