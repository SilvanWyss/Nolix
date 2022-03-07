//package declaration
package ch.nolix.systemtest.objectdatatest.dataadaptertest;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.data.Schema;
import ch.nolix.system.objectdata.data.Value;
import ch.nolix.system.objectdata.dataadapter.NodeDatabaseDataAdapter;

//class
public final class NodeDatabaseDataAdapterTest extends Test {
	
	//static class
	private static final class EmptyThing extends Entity {}
	
	//static class
	private static final class Person extends Entity {
		
		//attribute
		@SuppressWarnings("unused")
		private final Value<String> firstName = new Value<>();
		
		//attribute
		@SuppressWarnings("unused")
		private final Value<String> lastName = new Value<>();
		
		//attribute
		@SuppressWarnings("unused")
		private final Value<Integer> age = new Value<>();
	}
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//setup
		final var nodeDatabase = new Node();
		final var schema = Schema.withEntityType();
		
		//execution
		final var result =
		NodeDatabaseDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		
		//verification
		expectNot(result.hasChanges());
	}
	
	//method
	@TestCase
	public void testCase_saveChangesAndReset_whenDoesNotHaveChanges() {
		
		//setup
		final var nodeDatabase = new Node();
		final var schema = Schema.withEntityType(EmptyThing.class);
		final var testUnit =
		NodeDatabaseDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		
		//execution
		testUnit.saveChangesAndReset();
		
		//verification
		expectNot(testUnit.hasChanges());
	}
	
	//method
	@TestCase
	public void testCase_saveChangesAndReset_whenHasInsertedEmptyEntity() {
		
		//setup
		final var nodeDatabase = new Node();
		final var schema = Schema.withEntityType(EmptyThing.class);
		final var testUnit =
		NodeDatabaseDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		testUnit.insert(new EmptyThing());
		
		//execution
		testUnit.saveChangesAndReset();
		
		//verification
		expectNot(testUnit.hasChanges());
	}
	
	//method
	@TestCase
	public void testCase_saveChangesAndReset_whenHasInsertedEntity() {
		
		//setup
		final var nodeDatabase = new Node();
		final var schema = Schema.withEntityType(Person.class);
		final var testUnit =
		NodeDatabaseDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		testUnit.insert(new Person());
		
		//execution
		testUnit.saveChangesAndReset();
		
		//verification
		expectNot(testUnit.hasChanges());
	}
}
