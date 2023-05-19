//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.OptionalValue;
import ch.nolix.system.objectdatabase.databaseadapter.NodeDatabaseAdapter;
import ch.nolix.system.objectdatabase.schema.Schema;

//class
public final class OptionalValueOnDatabaseTest extends Test {
	
	//static class
	private static final class Pet extends Entity {
		
		//attribute
		private final OptionalValue<String> name = new OptionalValue<>();
		
		//constructor
		public Pet() {
			initialize();
		}
		
		//method
		public String getName() {
			return name.getOriValue();
		}
		
		//method
		public boolean hasName() {
			return name.containsAny();
		}
		
		//method
		public void setName(final String name) {
			this.name.setValue(name);
		}
	}
	
	//method
	@TestCase
	public void testCase_whenIsEmptyAndSaved() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		
		//execution
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//verification
		final var loadedGarfield =
		nodeDatabaseAdapter.getOriTableByEntityType(Pet.class).getOriEntityById(garfield.getId());
		expectNot(loadedGarfield.hasName());
	}
	
	//method
	@TestCase
	public void testCase_getOriValue_whenContainsAnyAndIsNotSaved() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		garfield.setName("Garfield");
		nodeDatabaseAdapter.insert(garfield);
				
		//execution
		final var result = garfield.getName();
		
		//verification
		expect(result).isEqualTo("Garfield");
	}
	
	//method
	@TestCase
	public void testCase_getOriValue_whenContainsAnyAndIsSaved() {
		
		//setup part 1
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		garfield.setName("Garfield");
		nodeDatabaseAdapter.insert(garfield);
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//setup part 2
		final var loadedGarfield =
		nodeDatabaseAdapter.getOriTableByEntityType(Pet.class).getOriEntityById(garfield.getId());
		
		//execution
		final var result = loadedGarfield.getName();
		
		//verification
		expect(result).isEqualTo("Garfield");
	}
}
