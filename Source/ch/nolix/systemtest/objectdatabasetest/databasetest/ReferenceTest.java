//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.Reference;
import ch.nolix.system.objectdatabase.database.Schema;
import ch.nolix.system.objectdatabase.databaseadapter.NodeDatabaseAdapter;

//class
public final class ReferenceTest extends Test {
	
	//static class
	private static final class Pet extends Entity {}
	
	//static class
	private static final class Person extends Entity {
		
		//attribute
		private final Reference<Pet> pet = Reference.forEntity(Pet.class);
		
		//constructor
		public Person() {
			initialize();
		}
		
		//method
		public Pet getRefPet() {
			return pet.getRefEntity();
		}
		
		//method
		public void setPet(final Pet pet) {
			this.pet.setEntity(pet);
		}
	}
	
	//method
	@TestCase
	public void testCase_whenIsEmpty() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class, Person.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var john = new Person();
		
		//execution & verification
		expectRunning(() -> nodeDatabaseAdapter.insert(john))
		.throwsException()
		.ofType(InvalidArgumentException.class);
	}
	
	//method
	@TestCase
	public void testCase_getRefEntity_whenIsNotSaved() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class, Person.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		final var john = new Person();
		john.setPet(garfield);
		nodeDatabaseAdapter.insert(john);
		
		//execution
		final var result = john.getRefPet();
		
		//verification
		expect(result).is(garfield);
		expectRunning(nodeDatabaseAdapter::saveChangesAndReset).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_getRefEntity_whenIsSaved() {
		
		//setup part 1
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class, Person.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		final var john = new Person();
		john.setPet(garfield);
		nodeDatabaseAdapter.insert(john);
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//setup part 2
		final var loadedJohn =
		nodeDatabaseAdapter.getRefTableByEntityType(Person.class).getRefEntityById(john.getId());
		
		//execution
		final var result = loadedJohn.getRefPet();
		
		//verification
		expect(result.getId()).isEqualTo(garfield.getId());
	}
}
