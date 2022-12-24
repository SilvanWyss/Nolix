//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.database.BackReference;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.MultiReference;
import ch.nolix.system.objectdatabase.database.Schema;
import ch.nolix.system.objectdatabase.databaseadapter.NodeDatabaseAdapter;

//class
public final class MultiReferenceWithBackReferenceTest extends Test {

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
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
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
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//execution
		final var loadedJohn =
		nodeDatabaseAdapter.getRefTableByEntityType(Person.class).getRefEntityById(john.getId());
		
		//verification
		final var loadedGarfield =
		nodeDatabaseAdapter.getRefTableByEntityType(Pet.class).getRefEntityById(garfield.getId());
		final var loadedSimba =
		nodeDatabaseAdapter.getRefTableByEntityType(Pet.class).getRefEntityById(simba.getId());
		final var loadedOdie =
		nodeDatabaseAdapter.getRefTableByEntityType(Pet.class).getRefEntityById(odie.getId());
		expect(loadedJohn.pets.getReferencedEntities().getElementCount()).isEqualTo(3);
		expect(loadedJohn.pets.getReferencedEntities().contains(loadedGarfield));
		expect(loadedJohn.pets.getReferencedEntities().contains(loadedSimba));
		expect(loadedJohn.pets.getReferencedEntities().contains(loadedOdie));
		expect(loadedGarfield.owner.getBackReferencedEntity()).is(loadedJohn);
		expect(loadedSimba.owner.getBackReferencedEntity()).is(loadedJohn);
		expect(loadedOdie.owner.getBackReferencedEntity()).is(loadedJohn);
	}
}
