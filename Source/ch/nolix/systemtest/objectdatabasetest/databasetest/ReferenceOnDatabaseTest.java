//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.Reference;
import ch.nolix.system.objectdatabase.schema.Schema;

//class
public final class ReferenceOnDatabaseTest extends Test {
	
	//static class
	private static final class Pet extends Entity {}
	
	//static class
	private static final class Person extends Entity {
		
		//attribute
		public final Reference<Pet> pet = Reference.forEntity(Pet.class);
		
		//constructor
		public Person() {
			initialize();
		}
	}
	
	//method
	@TestCase
	public void testCase_getStoredEntity_whenIsNewAndContainsAny() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class, Person.class);
		final var nodeDatabaseAdapter =
		NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").usingSchema(schema);
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
	public void testCase_getStoredEntity_whenIsLoadedAndContainsAny() {
		
		//setup part 1
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class, Person.class);
		final var nodeDatabaseAdapter =
		NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").usingSchema(schema);
		final var garfield = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		final var john = new Person();
		john.pet.setEntity(garfield);
		nodeDatabaseAdapter.insert(john);
		nodeDatabaseAdapter.saveChanges();
		
		//setup part 2
		final var loadedJohn =
		nodeDatabaseAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());
		
		//execution
		final var result = loadedJohn.pet.getReferencedEntity();
		
		//verification
		expect(result.getId()).isEqualTo(garfield.getId());
	}
	
	//method
	@TestCase
	public void testCase_isSaved_whenIsNewAndEmpty() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class, Person.class);
		final var nodeDatabaseAdapter =
		NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").usingSchema(schema);
		final var john = new Person();
		nodeDatabaseAdapter.insert(john);
		
		//execution & verification
		expectRunning(nodeDatabaseAdapter::saveChanges).throwsException();
	}
	
	//method
	@TestCase
	public void testCase_isSaved_whenIsEditedAndReferencedEntityIsDeleted() {
		
		//setup part 1: Initializes database.
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class, Person.class);
		final var nodeDatabaseAdapter =
		NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").usingSchema(schema);
		final var garfield = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		nodeDatabaseAdapter.saveChanges();
		
		//setup part 2: Prepares a change.
		final var nodeDatabaseAdapterB =
		NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").usingSchema(schema);
		final var loadedGarfieldB =
		nodeDatabaseAdapterB.getStoredTableByEntityType(Pet.class).getStoredEntityById(garfield.getId());
		final var johnB = new Person();
		johnB.pet.setEntity(loadedGarfieldB);
		nodeDatabaseAdapterB.insert(johnB);
		
		//setup part 3: Deletes the referenced Entity.
		final var nodeDatabaseAdapterC =
		NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").usingSchema(schema);
		final var loadedGarfieldC =
		nodeDatabaseAdapterC.getStoredTableByEntityType(Pet.class).getStoredEntityById(garfield.getId());
		loadedGarfieldC.delete();
		nodeDatabaseAdapterC.saveChanges();
		
		//execution & verification: Tries to save when the referenced Entity is deleted.
		expectRunning(nodeDatabaseAdapterB::saveChanges).throwsException();
	}
}
