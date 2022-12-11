//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.exception.ResourceWasChangedInTheMeanwhileException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.Reference;
import ch.nolix.system.objectdatabase.database.Schema;
import ch.nolix.system.objectdatabase.databaseadapter.NodeDatabaseAdapter;

//class
public final class ReferenceOnDatabaseTest extends Test {
	
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
	public void testCase_isSaved_whenIsEmpty() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class, Person.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var john = new Person();
		nodeDatabaseAdapter.insert(john);
		
		//execution & verification
		expectRunning(nodeDatabaseAdapter::saveChangesAndReset).throwsException();
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
	}
	
	//method
	@TestCase
	public void testCase_whenIsTriedToBeSavedButReferencesDeletedEntity() {
		
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
		johnB.setPet(loadedGarfieldB);
		nodeDatabaseAdapterB.insert(johnB);
		
		//setup part 3: Deletes the referenced Entity.
		final var nodeDatabaseAdapterC =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var loadedGarfieldC =
		nodeDatabaseAdapterC.getRefTableByEntityType(Pet.class).getRefEntityById(garfield.getId());
		loadedGarfieldC.delete();
		nodeDatabaseAdapterC.saveChangesAndReset();
				
		//execution & verification: Tries to save when the referenced Entity was deleted.
		expectRunning(nodeDatabaseAdapterB::saveChangesAndReset)
		.throwsException()
		.ofType(ResourceWasChangedInTheMeanwhileException.class)
		.withMessage("The data was changed in the meanwhile.");
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
