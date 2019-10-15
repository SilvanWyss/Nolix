//package declaration
package ch.nolix.systemTest.databaseAdapterTest;

//own imports
import ch.nolix.common.test.Test;
import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.Property;

//test class
public final class PropertyTest extends Test {
		
	//test case
	public void testCase_getSpecification() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final Property<String> testUnit = new Property<>();
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
			public final Property<String> testUnit = new Property<>();
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
