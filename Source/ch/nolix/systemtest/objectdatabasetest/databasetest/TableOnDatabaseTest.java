//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.databaseadapter.NodeDatabaseAdapter;
import ch.nolix.system.objectdatabase.schema.Schema;

//class
public final class TableOnDatabaseTest extends Test {
	
	//static class
	private static final class Thing extends Entity {
		
		//constructor
		public Thing() {
			initialize();
		}
	}
	
	//method
	@TestCase
	public void testCase_containsEntityWithId_whenDoesNotContainEntityWithGivenId() {
		
		//setup part 1: Initializes database.
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Thing.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var thing = new Thing();
		
		//setup part 2: Gains test unit.
		final var testUnit = nodeDatabaseAdapter.getOriTableByEntityType(Thing.class);
		
		//execution
		final var result = testUnit.containsEntityWithId(thing.getId());
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public void testCase_containsEntityWithId_whenContainsEntityWithGivenId() {
		
		//setup part 1: Initializes database.
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Thing.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var thing = new Thing();
		nodeDatabaseAdapter.insert(thing);
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//setup part 2: Gains test unit.
		final var testUnit = nodeDatabaseAdapter.getOriTableByEntityType(Thing.class);
		
		//execution
		final var result = testUnit.containsEntityWithId(thing.getId());
		
		//verification
		expect(result);
	}
}
