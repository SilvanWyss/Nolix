//package declaration
package ch.nolix.systemTest.entityTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.node.Node;
import ch.nolix.common.test.Test;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.Value;

//class
public final class EntityTest extends Test {
	
	//static class
	private static final class Cat extends Entity {
		
		//attributes
		public final Value<String> name = new Value<>();
		public final Value<Integer> weightInGram = new Value<>();
	}
	
	//method
	@TestCase
	public void testCase_getSpecification() {
		
		//setup
		final var testUnit = new Cat();
		testUnit.name.setValue("Garfield");
		testUnit.weightInGram.setValue(20000);
		
		//execution
		final var result = testUnit.getSpecification();
		
		//verification
		expect(result).isEqualTo(Node.fromString("Cat(name(Garfield),weightInGram(20000))"));
	}
}
