//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdatabase.database.BackReference;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.MultiReference;
import ch.nolix.system.objectdatabase.schema.Schema;

//class
public final class MultiReferenceWithBackReferencesTest extends Test {
	
	//static class
	private static final class Person extends Entity {
		
		//attribute
		public final MultiReference<Pet> pets = MultiReference.forEntity(Pet.class);
		
		//constructor
		public Person() {
			initialize();
		}
	}
	
	//static class
	private static final class Pet extends Entity {
		
		//attribute
		public final BackReference<Person> owner =
		BackReference.forEntityAndBackReferencedPropertyName(Person.class, "pets");
		
		//constructor
		public Pet() {
			initialize();
		}
	}
	
	//method
	@TestCase
	public void testCase_isSaved_whenContainsSeveral() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Person.class, Pet.class);
		final var nodeDatabaseAdapter =
		NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").usingSchema(schema);
		final var garfield = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		final var simba = new Pet();
		nodeDatabaseAdapter.insert(simba);
		final var odie = new Pet();
		nodeDatabaseAdapter.insert(odie);
		final var john = new Person();
		john.pets.addEntity(garfield);
		john.pets.addEntity(simba);
		john.pets.addEntity(odie);
		nodeDatabaseAdapter.insert(john);
		nodeDatabaseAdapter.saveChanges();
		
		//execution
		final var loadedJohn =
		nodeDatabaseAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());
		
		//verification
		final var loadedGarfield =
		nodeDatabaseAdapter.getStoredTableByEntityType(Pet.class).getStoredEntityById(garfield.getId());
		final var loadedSimba =
		nodeDatabaseAdapter.getStoredTableByEntityType(Pet.class).getStoredEntityById(simba.getId());
		final var loadedOdie =
		nodeDatabaseAdapter.getStoredTableByEntityType(Pet.class).getStoredEntityById(odie.getId());
		expect(loadedJohn.pets.getReferencedEntities()).containsExactlyInSameOrder(loadedGarfield, loadedSimba, loadedOdie);
		expect(loadedGarfield.owner.getBackReferencedEntity()).is(loadedJohn);
		expect(loadedSimba.owner.getBackReferencedEntity()).is(loadedJohn);
		expect(loadedOdie.owner.getBackReferencedEntity()).is(loadedJohn);
	}
	
	//method
	//TODO: @TestCase
	public void testCase_removeEntity_whenContainsEntity() {
		
		//setup part 1
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Person.class, Pet.class);
		final var nodeDatabaseAdapter =
		NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").usingSchema(schema);
		final var garfield = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		final var simba = new Pet();
		nodeDatabaseAdapter.insert(simba);
		final var odie = new Pet();
		nodeDatabaseAdapter.insert(odie);
		final var john = new Person();
		john.pets.addEntity(garfield);
		john.pets.addEntity(simba);
		john.pets.addEntity(odie);
		nodeDatabaseAdapter.insert(john);
		nodeDatabaseAdapter.saveChanges();
		
		//setup part 2
		final var loadedJohn1 =
		nodeDatabaseAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());
		final var loadedOdie1 =
		nodeDatabaseAdapter.getStoredTableByEntityType(Pet.class).getStoredEntityById(odie.getId());
		
		//execution
		loadedJohn1.pets.removeEntity(loadedOdie1);
		loadedOdie1.delete();
		nodeDatabaseAdapter.saveChanges();
		
		//verification part 1
		expectNot(loadedJohn1.pets.referencesEntity(loadedOdie1));
		
		//verification part 2
		final var loadedJohn2 =
		nodeDatabaseAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());
		final var loadedGarfield2 =
		nodeDatabaseAdapter.getStoredTableByEntityType(Pet.class).getStoredEntityById(garfield.getId());
		final var loadedSimba2 =
		nodeDatabaseAdapter.getStoredTableByEntityType(Pet.class).getStoredEntityById(simba.getId());
		expect(loadedJohn2.pets.getReferencedEntities()).containsExactlyInSameOrder(loadedGarfield2, loadedSimba2);
	}
}
