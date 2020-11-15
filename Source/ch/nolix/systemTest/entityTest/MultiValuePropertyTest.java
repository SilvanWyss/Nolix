//package declaration
package ch.nolix.systemTest.entityTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.test.Test;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.EntityAccessor;
import ch.nolix.system.entity.MultiValue;

//class
public final class MultiValuePropertyTest extends Test {
	
	//method
	@TestCase
	public void testCase_getSpecification_whenMultiPropertyIsEmpty() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final MultiValue<String> testUnit = new MultiValue<>();
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
	public void testCase_getSpecification_whenMultiPropertyContainsValues() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final MultiValue<String> testUnit = new MultiValue<>();
		};
		EntityAccessor.extractProperties(entity);
		entity.testUnit.addValue("a", "b", "c");
		
		//execution
		final var result = entity.testUnit.getSpecification();
		
		//verification
		expect(result.hasHeader());
		expect(result.containsAttributes());
		expect(result.toString()).isEqualTo("testUnit(a,b,c)");
	}
	
	//method
	@TestCase
	public void testCase_getCellSpecification_whenMultiPropertyIsEmpty() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final MultiValue<String> testUnit = new MultiValue<>();
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
	public void testCase_getCellSpecification_whenMultiPropertyContainsValues() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final MultiValue<String> testUnit = new MultiValue<>();
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
