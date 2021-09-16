//package declaration
package ch.nolix.systemtest.databaseschematest.nodedatabaseschemaadaptertest;

//own imports
import ch.nolix.common.document.node.Node;
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.Test;
import ch.nolix.system.databaseschema.schema.Table;
import ch.nolix.system.databaseschema.schemaadapter.NodeDatabaseSchemaAdapter;

//class
public final class NodeDatabaseSchemaAdapterTest extends Test {
	
	//method
	@TestCase
	public void test_creation() {
		
		//setup
		final var database = new Node();
		
		//setup verification
		expect(database.isBlank());
		
		//execution
		NodeDatabaseSchemaAdapter.forDatabaseNode("MyDatabase", database);
		
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
		final var testUnit = NodeDatabaseSchemaAdapter.forDatabaseNode("MyDatabase", database);
		
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
