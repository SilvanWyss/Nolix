//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.OptionalBackReference;
import ch.nolix.system.objectdatabase.database.OptionalReference;
import ch.nolix.system.objectdatabase.database.Schema;
import ch.nolix.system.objectdatabase.databaseadapter.NodeDatabaseAdapter;

//class
public final class OptionalBackReferenceOnDatabaseTest extends Test {
	
	//static class
	private static final class Person extends Entity {
		
		//attribute
		public final OptionalReference<Pet> pet = OptionalReference.forEntity(Pet.class);
		
		//constructor
		public Person() {
			initialize();
		}
	}
	
	//static class
	private static final class Pet extends Entity {
		
		//attribute
		public final OptionalBackReference<Person> owner =
		OptionalBackReference.forEntityAndBackReferencedPropertyName(Person.class, "pet");
		
		//constructor
		public Pet() {
			initialize();
		}
	}
	
	//method
	@TestCase
	public void testCase_getRefEntity_whenIsNewAndEmpty() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Person.class, Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		
		//execution
		final var result = garfield.owner.containsAny();
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public void testCase_getRefEntity_whenIsNewAndNotEmpty() {
		
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
		final var result = garfield.owner.getRefEntity();
		
		//verification
		expect(result).is(john);
	}
	
	//method
	@TestCase
	public void testCase_getRefEntity_whenIsLoaded() {
		
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
		nodeDatabaseAdapter.getRefTableByEntityType(Pet.class).getRefEntityById(garfield.getId());
		final var result = loadedGarfield.owner.getRefEntity();
		
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
		expectRunning(nodeDatabaseAdapter::saveChangesAndReset).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isSaved_whenBackReferencedEntityIsDeleted() {
		
		//setup part 1:
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
		final var loadedJohn = nodeDatabaseAdapter.getRefTableByEntityType(Person.class).getRefEntityById(john.getId());
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
		nodeDatabaseAdapter.getRefTableByEntityType(Person.class).getRefEntityById(john.getId());
		loadedJohn.pet.setEntity(bello);
		
		//setup part 2 verification
		final var loadedGarfield =
		nodeDatabaseAdapter.getRefTableByEntityType(Pet.class).getRefEntityById(garfield.getId());
		expect(loadedGarfield.owner.isEmpty());
		
		//execution & verification
		expectRunning(nodeDatabaseAdapter::saveChangesAndReset).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isChanged_whenIsLoaded() {
		
		//setup part 1
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Person.class, Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		final var odie = new Pet();
		nodeDatabaseAdapter.insert(odie);
		final var john = new Person();
		john.pet.setEntity(garfield);
		nodeDatabaseAdapter.insert(john);
		
		//setup part 1 verification
		expect(john.pet.getRefEntity()).is(garfield);
		expect(garfield.owner.getRefEntity()).is(john);
		expect(odie.owner.isEmpty());
		
		//setup part 2
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//setup part 3
		final var loadedJohn =
		nodeDatabaseAdapter.getRefTableByEntityType(Person.class).getRefEntityById(john.getId());
		final var loadedOdie =
		nodeDatabaseAdapter.getRefTableByEntityType(Pet.class).getRefEntityById(odie.getId());
		
		//execution
		loadedJohn.pet.setEntity(loadedOdie);
		
		//verification
		final var loadedGarfield =
		nodeDatabaseAdapter.getRefTableByEntityType(Pet.class).getRefEntityById(garfield.getId());
		expect(loadedJohn.pet.getRefEntity()).is(loadedOdie);
		expect(loadedGarfield.owner.isEmpty());
		expect(loadedOdie.owner.getRefEntity()).is(loadedJohn);
	}
}
