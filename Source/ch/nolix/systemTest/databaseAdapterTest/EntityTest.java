//package declaration
package ch.nolix.systemTest.databaseAdapterTest;

import ch.nolix.common.node.Node;
import ch.nolix.common.test.Test;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.Property;

//test class
public final class EntityTest extends Test {
	
	//test case
	public void testCase_getSpecification() {
		
		//setup
		final var cat = new Cat();
		cat.Name.setValue("Garfield");
		cat.WeightInGram.setValue(20000);
		
		//execution
		final var specification = cat.getSpecification();
		
		//verification
		expect(specification)
		.isEqualTo(
			Node.fromString(
				"Cat(Name(Garfield),WeightInGram(20000))"
			)
		);
	}
	
	//inner class
	private static final class Cat extends Entity {
		
		//attributes
		public final Property<String> Name = new Property<>();
		public final Property<Integer> WeightInGram = new Property<>();
	}
}
