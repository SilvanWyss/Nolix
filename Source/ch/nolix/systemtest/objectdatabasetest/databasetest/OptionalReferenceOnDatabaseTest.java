//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.OptionalReference;
import ch.nolix.system.objectdatabase.databaseadapter.NodeDatabaseAdapter;
import ch.nolix.system.objectdatabase.schema.Schema;

//class
public final class OptionalReferenceOnDatabaseTest extends Test {
	
	//static class
	private static final class Pet extends Entity {}
	
	//static class
	private static final class Person extends Entity {
		
		//attribute
		public final OptionalReference<Pet> pet = OptionalReference.forEntity(Pet.class);
		
		//constructor
		public Person() {
			initialize();
		}
	}
	
	//method
	@TestCase
	public void testCase_isSaved_whenIsNewAndEmpty() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class, Person.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var john = new Person();
		nodeDatabaseAdapter.insert(john);
		
		//execution
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//verification
		final var loadedJohn =
		nodeDatabaseAdapter.getRefTableByEntityType(Person.class).getRefEntityById(john.getId());
		expect(loadedJohn.pet.isEmpty());
	}
	
	//method
	@TestCase
	public void testCase_getRefEntity_whenIsNewAndNotEmpty() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class, Person.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		final var john = new Person();
		john.pet.setEntity(garfield);
		nodeDatabaseAdapter.insert(john);
		
		//execution
		final var result = john.pet.getReferencedEntity();
		
		//verification
		expect(result).is(garfield);
	}
	
	//method
	@TestCase
	public void testCase_getRefEntity_whenIsLoadedAndNotEmpty() {
		
		//setup part 1
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class, Person.class);
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
		nodeDatabaseAdapter.getRefTableByEntityType(Person.class).getRefEntityById(john.getId());
		
		//execution
		final var result = loadedJohn.pet.getReferencedEntity();
		
		//verification
		expect(result.getId()).isEqualTo(garfield.getId());
	}
	
	//method
	@TestCase
	public void testCase_isSaved_whenReferencedEntityIsDeleted() {
		
		//setup part 1: Initializes database.
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class, Person.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//setup part 2: Prepares a change.
		final var nodeDatabaseAdapterB =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var loadedGarfieldB =
		nodeDatabaseAdapterB.getRefTableByEntityType(Pet.class).getRefEntityById(garfield.getId());
		final var johnB = new Person();
		johnB.pet.setEntity(loadedGarfieldB);
		nodeDatabaseAdapterB.insert(johnB);
		
		//setup part 3: Deletes the referenced Entity.
		final var nodeDatabaseAdapterC =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var loadedGarfieldC =
		nodeDatabaseAdapterC.getRefTableByEntityType(Pet.class).getRefEntityById(garfield.getId());
		loadedGarfieldC.delete();
		nodeDatabaseAdapterC.saveChangesAndReset();
				
		//execution & verification: Tries to save when the referenced Entity was deleted.
		expectRunning(nodeDatabaseAdapterB::saveChangesAndReset).throwsException();
	}
}
