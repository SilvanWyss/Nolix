//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.Schema;
import ch.nolix.system.objectdatabase.database.Value;
import ch.nolix.system.objectdatabase.databaseadapter.NodeDatabaseAdapter;

//class
public final class ValueOnDatabaseTest extends Test {
	
	//static class
	private static final class Pet extends Entity {
		
		//attribute
		private final Value<String> name = new Value<>();
		
		//constructor
		public Pet() {
			initialize();
		}
		
		//method
		public String getName() {
			return name.getRefValue();
		}
		
		//method
		public void setName(final String name) {
			this.name.setValue(name);
		}
	}
	
	//method
	@TestCase
	public void testCase_whenIsEmpty() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		
		//execution & verification
		expectRunning(() -> nodeDatabaseAdapter.insert(garfield))
		.throwsException()
		.ofType(InvalidArgumentException.class);
	}
	
	//method
	@TestCase
	public void testCase_getRefValue_whenIsNotSaved() {
		
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
	public void testCase_getRefValue_whenIsSaved() {
		
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
		nodeDatabaseAdapter.getRefTableByEntityType(Pet.class).getRefEntityById(garfield.getId());
		
		//execution
		final var result = loadedGarfield.getName();
		
		//verification
		expect(result).isEqualTo("Garfield");
	}
}
