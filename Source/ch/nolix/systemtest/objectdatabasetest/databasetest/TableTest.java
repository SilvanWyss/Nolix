//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.Value;
import ch.nolix.system.objectdatabase.databaseadapter.NodeDatabaseAdapter;
import ch.nolix.system.objectdatabase.schema.Schema;

//class
public final class TableTest extends Test {
	
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
		
		//method
		public String getFirstName() {
			return firstName.getOriValue();
		}
		
		//method
		public String getLastName() {
			return lastName.getOriValue();
		}
	}
	
	//method
	@TestCase
	public void testCase_getOriAllEntities_whenIsEmpty() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Person.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);	
		final var testUnit = nodeDatabaseAdapter.getOriTableByEntityType(Person.class);
		
		//execution
		final var result = testUnit.getOriEntities();
		
		//verification
		expectNot(nodeDatabaseAdapter.hasChanges());
		expect(result).isEmpty();
	}
	
	//method
	@TestCase
	public void testCase_getOriAllEntities() {
		
		//setup part 1
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Person.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var person = new Person();
		person.setFirstNameAndLastName("Donald", "Duck");
		nodeDatabaseAdapter.insert(person);
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//setup part 2
		final var nodeDatabaseAdapter2 =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);		
		final var testUnit = nodeDatabaseAdapter2.getOriTableByEntityType(Person.class);
		
		//execution
		final var result = testUnit.getOriEntities();
		
		//verification
		expect(result.getElementCount()).isEqualTo(1);
		final var loadedPerson = result.getOriAt1BasedIndex(1);
		expect(loadedPerson.getId()).isEqualTo(person.getId());
		expect(loadedPerson.getFirstName()).isEqualTo("Donald");
		expect(loadedPerson.getLastName()).isEqualTo("Duck");
	}
}
