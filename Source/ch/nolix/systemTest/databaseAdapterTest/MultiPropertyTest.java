//package declaration
package ch.nolix.systemTest.databaseAdapterTest;

//own imports
import ch.nolix.common.test.Test;
import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.MultiProperty;

//test class
public final class MultiPropertyTest extends Test {
	
	//test case
	public void testCase_getSpecification_whenMultiPropertyIsEmpty() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final MultiProperty<String> testUnit = new MultiProperty<>();
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
	public void testCase_getSpecification_whenMultiPropertyContainsValues() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final MultiProperty<String> testUnit = new MultiProperty<>();
		};
		entity.extractPropertiesAndBackReferences();
		entity.testUnit.addValue("a", "b", "c");
		
		//execution
		final var result = entity.testUnit.getSpecification();
		
		//verification
		expect(result.hasHeader());
		expect(result.containsAttributes());
		expect(result.toString()).isEqualTo("testUnit(a,b,c)");
	}
	
	//test case
	public void testCase_getCellSpecification_whenMultiPropertyIsEmpty() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final MultiProperty<String> testUnit = new MultiProperty<>();
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
	public void testCase_getCellSpecification_whenMultiPropertyContainsValues() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final MultiProperty<String> testUnit = new MultiProperty<>();
		};
		entity.testUnit.addValue("a", "b", "c");
		
		//execution
		final var result = entity.testUnit.getCellSpecification();
		
		//verification
		expectNot(result.hasHeader());
		expect(result.containsAttributes());
		expect(result.toString()).isEqualTo("(a,b,c)");
	}
}
