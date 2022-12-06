//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.database.BackReference;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.OptionalReference;
import ch.nolix.system.objectdatabase.database.Schema;
import ch.nolix.system.objectdatabase.databaseadapter.NodeDatabaseAdapter;

//class
public final class OptionalBackReferenceOnDatabaseTest extends Test {
	
	//static class
	private static final class Person extends Entity {
		
		//attribute
		private final OptionalReference<Pet> pet = OptionalReference.forEntity(Pet.class);
		
		//constructor
		public Person() {
			initialize();
		}
		
		//method
		public void setPet(final Pet pet) {
			this.pet.setEntity(pet);
		}
	}
	
	//static class
	private static final class Pet extends Entity {
		
		//attribute
		private final BackReference<Person> owner =
		BackReference.forEntityAndBackReferencedPropertyName(Person.class, "pet");
		
		//constructor
		public Pet() {
			initialize();
		}
		
		//method
		public Person getRefOwner() {
			return owner.getRefEntity();
		}
	}
	
	//method
	@TestCase
	public void testCase_getRefEntity_whenIsNotSaved() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Person.class, Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		final var john = new Person();
		john.setPet(garfield);
		nodeDatabaseAdapter.insert(john);
		
		//execution
		final var result = garfield.getRefOwner();
		
		//verification
		expect(result).is(john);
	}
	
	//method
	@TestCase
	public void testCase_getRefEntity_whenIsSaved() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Person.class, Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		final var john = new Person();
		john.setPet(garfield);
		nodeDatabaseAdapter.insert(john);
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//execution
		final var loadedGarfield =
		nodeDatabaseAdapter.getRefTableByEntityType(Pet.class).getRefEntityById(garfield.getId());
		final var result = loadedGarfield.getRefOwner();
		
		//verification
		expect(result.getId()).isEqualTo(john.getId());
	}
	
	//method
	@TestCase
	public void testCase_isSaved_whenReferencesBackDeletedEntity() {
		
		//setup part 1
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Person.class, Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		final var john = new Person();
		john.setPet(garfield);
		nodeDatabaseAdapter.insert(john);
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//setup part 2
		final var loadedJohn = nodeDatabaseAdapter.getRefTableByEntityType(Person.class).getRefEntityById(john.getId());
		loadedJohn.delete();
		
		//execution & verification
		expectRunning(nodeDatabaseAdapter::saveChangesAndReset).throwsException();
	}
}
