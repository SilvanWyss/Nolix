//package declaration
package ch.nolix.systemtest.objectdatabasetest.databaseadaptertest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.databaseadapter.NodeDatabaseAdapter;
import ch.nolix.system.objectdatabase.schema.Schema;

//class
public final class NodeDatabaseAdapterTest extends Test {
	
	//static class
	private static final class Pet extends Entity {
		
		//constructor
		public Pet() {
			initialize();
		}
	}
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType();
		
		//execution
		final var result =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		
		//verification
		expect(result.getSaveCount()).isEqualTo(0);
		expectNot(result.hasChanges());
	}
	
	//method
	@TestCase
	public void testCase_saveChangesAndReset_whenDoesNotHaveChanges() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class);
		final var testUnit =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		
		//execution
		testUnit.saveChangesAndReset();
		
		//verification
		expect(testUnit.getSaveCount()).isEqualTo(1);
		expectNot(testUnit.hasChanges());
	}
	
	//method
	@TestCase
	public void testCase_saveChangesAndReset_whenHasChanges() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class);
		final var testUnit =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		testUnit.insert(new Pet());
		
		//execution
		testUnit.saveChangesAndReset();
		
		//verification
		expect(testUnit.getSaveCount()).isEqualTo(1);
		expectNot(testUnit.hasChanges());
	}
}
