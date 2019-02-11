//package declaration
package ch.nolix.systemTest.databaseAdapterTest;

import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.test.Test;
import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.Property;

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
			new DocumentNode(
				"Cat(Name(Garfield),WeightInGram(20000))"
			)
		);
	}
	
	//inner class
	private static final class Cat extends Entity {
		
		//attributes
		public final Property<String> Name = new Property<String>();
		public final Property<Integer> WeightInGram = new Property<Integer>();
	}
}
