//package declaration
package ch.nolix.systemtest.objectdatatest.dataadaptertest;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdata.data.Schema;
import ch.nolix.system.objectdata.dataadapter.NodeDatabaseDataAdapter;

//class
public final class NodeDatabaseDataAdapterTest extends Test {
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//setup
		final var nodeDatabase = new Node();
		@SuppressWarnings("unchecked")
		final var schema = Schema.withEntityType();
		
		//execution
		final var result = NodeDatabaseDataAdapter.forNodeDatabase(nodeDatabase).usingSchema(schema);
		
		//verification
		expect(result.isChangeFree());
	}
}
