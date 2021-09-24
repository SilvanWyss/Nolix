//package declaration
package ch.nolix.systemtest.databaseschematest.nodeschemaadaptertest;

//own imports
import ch.nolix.common.document.node.Node;
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.Test;
import ch.nolix.system.objectschema.schema.Table;
import ch.nolix.system.objectschema.schemaadapter.NodeSchemaAdapter;

//class
public final class NodeSchemaAdapterTest extends Test {
	
	//method
	@TestCase
	public void test_creation() {
		
		//setup
		final var database = new Node();
		
		//setup verification
		expect(database.isBlank());
		
		//execution
		NodeSchemaAdapter.forDatabaseNode("MyDatabase", database);
		
		//verification
		expect(database.getHeader()).isEqualTo("Database");
		expect(database.getAttributeCount()).isEqualTo(1);
		expect(database.getRefAttributeAt(1).getHeader()).isEqualTo("DatabaseProperties");
	}
	
	//method
	@TestCase
	public void test_addTable() {
		
		//setup
		final var database = new Node();
		final var testUnit = NodeSchemaAdapter.forDatabaseNode("MyDatabase", database);
		
		//execution
		testUnit.addTable(new Table("MyTable")).saveChanges();
		
		//verification
		final var tableNodes = database.getRefAttributes("Table");
		expect(tableNodes.containsOne());
		final var tableNode = tableNodes.getRefFirst();
		final var nameNode = tableNode.getRefFirstAttribute("Name");
		expect(nameNode.getOneAttributeHeader()).isEqualTo("MyTable");
	}
}
