//package declaration
package ch.nolix.coreTest.databaseAdapterTest;

//own imports
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.Property;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.test2.Test;

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
	private static class Cat extends Entity {
		
		//attributes
		public final Property<String> Name = new Property<String>();
		public final Property<Integer> WeightInGram = new Property<Integer>();
	}
}
