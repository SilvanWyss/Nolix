//package declaration
package ch.nolix.systemtest.objectdatatest.dataadaptertest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.data.Schema;
import ch.nolix.system.objectdata.data.Value;
import ch.nolix.system.objectdata.dataadapter.NodeDatabaseDataAdapter;
import ch.nolix.system.objectdata.datahelper.EntityHelper;

//class
public final class NodeDatabaseDataAdapterTest extends Test {
	
	//static class
	private static final class EmptyThing extends Entity {}
	
	//static class
	private static final class Person extends Entity {
		
		//attribute
		private final Value<String> firstName = new Value<>();
		
		//attribute
		private final Value<String> lastName = new Value<>();
		
		//method
		public void setFirstNameAndLastName(final String firstName, final String lastName) {
			this.firstName.setValue(firstName);
			this.lastName.setValue(lastName);
		}
	}
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType();
		
		//execution
		final var result =
		NodeDatabaseDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		
		//verification
		expectNot(result.hasChanges());
	}
	
	//method
	@TestCase
	public void testCase_insertEntity_whenGivenEntityContainsEmptyButMandatoryProperties() {
		
		//setup part 1
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Person.class);
		final var testUnit =
		NodeDatabaseDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		
		//setup part 2
		final var person = new Person();
		
		//setup verification
		expect(new EntityHelper().containsMandatoryAndEmptyBaseValuesOrBaseReferences(person));
		
		//execution & verification
		expectRunning(() -> testUnit.insert(person)).throwsException().ofType(InvalidArgumentException.class);
		
		//verification
		expectNot(testUnit.hasChanges());
	}
	
	//method
	@TestCase
	public void testCase_saveChangesAndReset_whenDoesNotHaveChanges() {
		
		//setup
		final var nodeDatabase = new MutableNode();
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
		final var nodeDatabase = new MutableNode();
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
		
		//setup part 1
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Person.class);
		final var testUnit =
		NodeDatabaseDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		
		//setup part 2
		final var person = new Person();
		person.setFirstNameAndLastName("Donald", "Duck");
		testUnit.insert(person);
		
		//execution
		testUnit.saveChangesAndReset();
		
		//verification
		expectNot(testUnit.hasChanges());
	}
}
