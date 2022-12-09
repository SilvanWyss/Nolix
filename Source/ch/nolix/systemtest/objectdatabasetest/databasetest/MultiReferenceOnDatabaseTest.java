//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.MultiReference;
import ch.nolix.system.objectdatabase.database.Schema;
import ch.nolix.system.objectdatabase.databaseadapter.NodeDatabaseAdapter;

//class
public final class MultiReferenceOnDatabaseTest extends Test {
	
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
		
		//constructor
		public Pet() {
			initialize();
		}
	}
	
	//method
	@TestCase
	public void testCase_whenIsLoadedAndEmpty() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class, Person.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var john = new Person();
		nodeDatabaseAdapter.insert(john);
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//execution
		final var loadedJohn =
		nodeDatabaseAdapter.getRefTableByEntityType(Person.class).getRefEntityById(john.getId());
		
		//verification
		System.out.print(nodeDatabase.toFormattedString());
		expect(loadedJohn.pets.isEmpty());
	}
	
	//method
	@TestCase
	public void testCase_whenIsLoadedAndNotEmpty() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class, Person.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		final var odie = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		nodeDatabaseAdapter.insert(odie);
		final var john = new Person();
		john.pets.addEntity(garfield);
		john.pets.addEntity(odie);
		nodeDatabaseAdapter.insert(john);
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//execution
		final var loadedJohn =
		nodeDatabaseAdapter.getRefTableByEntityType(Person.class).getRefEntityById(john.getId());
		
		//verification
		expect(loadedJohn.pets.getReferencedEntities().getElementCount()).isEqualTo(2);
		expect(loadedJohn.pets.getReferencedEntities().containsAny(p -> p.hasId(garfield.getId())));
		expect(loadedJohn.pets.getReferencedEntities().containsAny(p -> p.hasId(odie.getId())));
	}
}
