//package declaration
package ch.nolix.systemtest.databasetest.entitytest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.test.Test;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.entity.EntityAccessor;
import ch.nolix.system.database.entity.Value;

//class
public final class ValueTest extends Test {
		
	//method
	@TestCase
	public void testCase_getSpecificationAsAttribute() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final Value<String> testUnit = new Value<>();
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
	public void testCase_getSpecificationWithoutHeader() {
		
		//setup
		final var entity = new Entity() {
			
			//attribute
			public final Value<String> testUnit = new Value<>();
		};
		entity.testUnit.setValue("x");
		
		//execution
		final var result = entity.testUnit.getSpecificationWithoutHeader();
		
		//verification
		expect(result.toString()).isEqualTo("(x)");
	}
}
