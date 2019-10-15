//package declaration
package ch.nolix.systemTest.databaseAdapterTest;

//own imports
import ch.nolix.common.test.Test;
import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.OptionalProperty;

//test class
public final class OptionalPropertyTest extends Test {
	
	//test case
	public void testCase_getSpecification_whenOptionalPropertyIsEmpty() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final OptionalProperty<String> testUnit = new OptionalProperty<>();
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
			public final OptionalProperty<String> testUnit = new OptionalProperty<>();
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
			public final OptionalProperty<String> testUnit = new OptionalProperty<>();
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
			public final OptionalProperty<String> testUnit = new OptionalProperty<>();
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
