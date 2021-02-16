//package declaration
package ch.nolix.systemtest.entitytest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.test.Test;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.MultiValue;

//class
public final class MultiValueTest extends Test {
	
	//method
	@TestCase
	public void testCase_getCellSpecification_whenContainsValues() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final MultiValue<String> testUnit = new MultiValue<>();
		};
		entity.testUnit.addValue("a", "b", "c");
		
		//execution
		final var result = entity.testUnit.getCellSpecification();
		
		//verification
		expect(result.toString()).isEqualTo("(a,b,c)");
	}
	
	//method
	@TestCase
	public void testCase_getCellSpecification_whenIsEmpty() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final MultiValue<String> testUnit = new MultiValue<>();
		};
		entity.testUnit.clear();
		
		//execution
		final var result = entity.testUnit.getCellSpecification();
		
		//verification
		expect(result.toString()).isEqualTo("");
	}
	
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
}
