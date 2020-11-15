//package declaration
package ch.nolix.systemTest.entityTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.test.Test;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.EntityAccessor;
import ch.nolix.system.entity.OptionalValue;

//class
public final class OptionalValuePropertyTest extends Test {
	
	//method
	@TestCase
	public void testCase_getSpecification_whenOptionalPropertyIsEmpty() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final OptionalValue<String> testUnit = new OptionalValue<>();
		};
		EntityAccessor.extractProperties(entity);
		entity.testUnit.clear();
		
		//execution
		final var result = entity.testUnit.getSpecification();
		
		//verification
		expect(result.hasHeader());
		expectNot(result.containsAttributes());
		expect(result.toString()).isEqualTo("testUnit");
	}
	
	//method
	@TestCase
	public void testCase_getSpecification_whenOptionalPropertyHasAValue() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final OptionalValue<String> testUnit = new OptionalValue<>();
		};
		EntityAccessor.extractProperties(entity);
		entity.testUnit.setValue("x");
		
		//execution
		final var result = entity.testUnit.getSpecification();
		
		//verification
		expect(result.hasHeader());
		expect(result.containsAttributes());
		expect(result.toString()).isEqualTo("testUnit(x)");
	}
	
	//method
	@TestCase
	public void testCase_getCellSpecification_whenOptionalPropertyIsEmpty() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final OptionalValue<String> testUnit = new OptionalValue<>();
		};
		entity.testUnit.clear();
		
		//execution
		final var result = entity.testUnit.getCellSpecification();
		
		//verification
		expectNot(result.hasHeader());
		expectNot(result.containsAttributes());
		expect(result.toString()).isEqualTo("");
	}
	
	//method
	@TestCase
	public void testCase_getCellSpecification_whenOptioanlPropertyHasAValue() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final OptionalValue<String> testUnit = new OptionalValue<>();
		};
		entity.testUnit.setValue("x");
		
		//execution
		final var result = entity.testUnit.getCellSpecification();
		
		//verification
		expect(result.hasHeader());
		expectNot(result.containsAttributes());
		expect(result.toString()).isEqualTo("x");
	}
}
