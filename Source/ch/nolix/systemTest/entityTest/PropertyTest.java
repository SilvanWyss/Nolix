//package declaration
package ch.nolix.systemTest.entityTest;

//own imports
import ch.nolix.common.test.Test;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.ValueProperty;

//test class
public final class PropertyTest extends Test {
		
	//test case
	public void testCase_getSpecification() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final ValueProperty<String> testUnit = new ValueProperty<>();
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
	public void testCase_getCellSpecification() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final ValueProperty<String> testUnit = new ValueProperty<>();
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
