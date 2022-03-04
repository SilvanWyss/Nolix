//package declaration
package ch.nolix.systemtest.objectdatatest.dataadaptertest;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.data.Schema;
import ch.nolix.system.objectdata.dataadapter.NodeDatabaseDataAdapter;

//class
public final class NodeDatabaseDataAdapterTest extends Test {
	
	//static class
	private static final class EmptyThing extends Entity {}
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//setup
		final var nodeDatabase = new Node();
		final var schema = Schema.withEntityType();
		
		//execution
		final var result = NodeDatabaseDataAdapter.forNodeDatabase(nodeDatabase).usingSchema(schema);
		
		//verification
		expect(result.isChangeFree());
	}
	
	//method
	@TestCase
	public void testCase_saveChangesAndReset_whenDoesNotHaveChanges() {
		
		//setup
		final var nodeDatabase = new Node();
		final var schema = Schema.withEntityType(EmptyThing.class);
		final var testUnit = NodeDatabaseDataAdapter.forNodeDatabase(nodeDatabase).usingSchema(schema);
		
		//execution
		testUnit.saveChangesAndReset();
		
		//verification
		expectNot(testUnit.hasChanges());
	}
	
	//method
	@TestCase
	public void testCase_saveChangesAndReset_whenHasInsertedEntity() {
		
		//setup
		final var nodeDatabase = new Node();
		final var schema = Schema.withEntityType(EmptyThing.class);
		final var testUnit = NodeDatabaseDataAdapter.forNodeDatabase(nodeDatabase).usingSchema(schema);
		testUnit.insert(new EmptyThing());
		
		//execution
		testUnit.saveChangesAndReset();
		
		//verification
		expectNot(testUnit.hasChanges());
	}
}
