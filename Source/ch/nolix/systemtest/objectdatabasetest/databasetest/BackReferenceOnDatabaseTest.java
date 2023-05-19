//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.database.BackReference;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.Reference;
import ch.nolix.system.objectdatabase.databaseadapter.NodeDatabaseAdapter;
import ch.nolix.system.objectdatabase.schema.Schema;

//class
public final class BackReferenceOnDatabaseTest extends Test {
		
	//static class
	private static final class Person extends Entity {
		
		//attribute
		public final Reference<Pet> pet = Reference.forEntity(Pet.class);
		
		//constructor
		public Person() {
			initialize();
		}
	}
	
	//static class
	private static final class Pet extends Entity {
		
		//attribute
		public final BackReference<Person> owner =
		BackReference.forEntityAndBackReferencedPropertyName(Person.class, "pet");
		
		//constructor
		public Pet() {
			initialize();
		}
	}
	
	//method
	@TestCase
	public void testCase_getOriEntity_whenIsNewAndEmpty() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Person.class, Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		
		//execution & verification
		expectRunning(garfield.owner::getBackReferencedEntity).throwsException();
	}
	
	//method
	@TestCase
	public void testCase_getOriEntity_whenIsNewAndNotEmpty() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Person.class, Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		final var john = new Person();
		john.pet.setEntity(garfield);
		nodeDatabaseAdapter.insert(john);
		
		//execution
		final var result = garfield.owner.getBackReferencedEntity();
		
		//verification
		expect(result).is(john);
	}
	
	//method
	@TestCase
	public void testCase_getOriEntity_whenIsLoaded() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Person.class, Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		final var john = new Person();
		john.pet.setEntity(garfield);
		nodeDatabaseAdapter.insert(john);
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//execution
		final var loadedGarfield =
		nodeDatabaseAdapter.getOriTableByEntityType(Pet.class).getOriEntityById(garfield.getId());
		final var result = loadedGarfield.owner.getBackReferencedEntity();
		
		//verification
		expect(result.getId()).isEqualTo(john.getId());
	}
	
	//method
	@TestCase
	public void testCase_isSaved_whenIsEmpty() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Person.class, Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		
		//execution & verification
		expectRunning(nodeDatabaseAdapter::saveChangesAndReset).throwsException();
	}
	
	//method
	@TestCase
	public void testCase_isSaved_whenBackReferencedEntityIsDeleted() {
		
		//setup part 1
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Person.class, Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		final var john = new Person();
		john.pet.setEntity(garfield);
		nodeDatabaseAdapter.insert(john);
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//setup part 2
		final var loadedJohn =
		nodeDatabaseAdapter.getOriTableByEntityType(Person.class).getOriEntityById(john.getId());
		loadedJohn.delete();
		
		//execution & verification
		expectRunning(nodeDatabaseAdapter::saveChangesAndReset).throwsException();
	}
	
	//method
	@TestCase
	public void testCase_isSaved_whenBackReferencedPropertyIsChanged() {
		
		//setup part 1
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Person.class, Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		final var john = new Person();
		john.pet.setEntity(garfield);
		nodeDatabaseAdapter.insert(john);
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//setup part 2
		final var bello = new Pet();
		nodeDatabaseAdapter.insert(bello);
		final var loadedJohn =
		nodeDatabaseAdapter.getOriTableByEntityType(Person.class).getOriEntityById(john.getId());
		loadedJohn.pet.setEntity(bello);
		
		//setup verification
		final var loadedGarfield =
		nodeDatabaseAdapter.getOriTableByEntityType(Pet.class).getOriEntityById(garfield.getId());
		expect(loadedGarfield.owner.isEmpty());
		
		//execution & verification
		expectRunning(nodeDatabaseAdapter::saveChangesAndReset).throwsException();
	}
}
