//package declaration
package ch.nolix.systemTest.entityTest;

//own imports
import ch.nolix.common.node.Node;
import ch.nolix.common.test.Test;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.ValueProperty;

//test class
public final class EntityTest extends Test {
	
	//static class
	private static final class Cat extends Entity {
		
		//attributes
		public final ValueProperty<String> name = new ValueProperty<>();
		public final ValueProperty<Integer> weightInGram = new ValueProperty<>();
	}
	
	//test case
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
