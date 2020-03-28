//package declaration
package ch.nolix.systemTest.entityTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.test.Test;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.EntityAccessor;
import ch.nolix.system.entity.ValueProperty;

//class
public final class ValuePropertyTest extends Test {
		
	//method
	@TestCase
	public void testCase_getSpecification() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final ValueProperty<String> testUnit = new ValueProperty<>();
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
