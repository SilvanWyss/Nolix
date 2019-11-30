//package declaration
package ch.nolix.systemTest.entityTest;

//own imports
import ch.nolix.common.test.Test;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.OptionalValueProperty;

//test class
public final class OptionalValuePropertyTest extends Test {
	
	//test case
	public void testCase_getSpecification_whenOptionalPropertyIsEmpty() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final OptionalValueProperty<String> testUnit = new OptionalValueProperty<>();
		};
		entity.extractPropertiesAndBackReferences();
		entity.testUnit.clear();
		
		//execution
		final var result = entity.testUnit.getSpecification();
		
		//verification
		expect(result.hasHeader());
		expectNot(result.containsAttributes());
		expect(result.toString()).isEqualTo("testUnit");
	}
	
	//test case
	public void testCase_getSpecification_whenOptionalPropertyHasAValue() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final OptionalValueProperty<String> testUnit = new OptionalValueProperty<>();
		};
		entity.extractPropertiesAndBackReferences();
		entity.testUnit.setValue("x");
		
		//execution
		final var result = entity.testUnit.getSpecification();
		
		//verification
		expect(result.hasHeader());
		expect(result.containsAttributes());
		expect(result.toString()).isEqualTo("testUnit(x)");
	}
	
	//test case
	public void testCase_getCellSpecification_whenOptionalPropertyIsEmpty() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final OptionalValueProperty<String> testUnit = new OptionalValueProperty<>();
		};
		entity.testUnit.clear();
		
		//execution
		final var result = entity.testUnit.getCellSpecification();
		
		//verification
		expectNot(result.hasHeader());
		expectNot(result.containsAttributes());
		expect(result.toString()).isEqualTo("");
	}
	
	//test case
	public void testCase_getCellSpecification_whenOptioanlPropertyHasAValue() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final OptionalValueProperty<String> testUnit = new OptionalValueProperty<>();
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
