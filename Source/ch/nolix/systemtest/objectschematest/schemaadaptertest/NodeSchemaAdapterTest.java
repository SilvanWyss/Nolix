//package declaration
package ch.nolix.systemtest.objectschematest.schemaadaptertest;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
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
		testUnit.addTable(new Table("MyTable")).saveChangesAndReset();
		
		//verification
		final var tableNodes = database.getRefAttributes("Table");
		expect(tableNodes.containsOne());
		final var tableNode = tableNodes.getRefFirst();
		final var nameNode = tableNode.getRefFirstAttribute("Name");
		expect(nameNode.getOneAttributeHeader()).isEqualTo("MyTable");
	}
	
	//method
	@TestCase
	public void test_getSaveCount_whenIsNew() {
		
		//setup
		final var testUnit = NodeSchemaAdapter.forDatabaseNode("MyDatabase", new Node());
		
		//execution
		final var result = testUnit.getSaveCount();
		
		//verification
		expect(result).isEqualTo(0);
	}
	
	//method
	@TestCase
	public void test_getSaveCount_whenSavesChangesAndResetsFor1Times() {
		
		//setup
		final var testUnit = NodeSchemaAdapter.forDatabaseNode("MyDatabase", new Node());
		testUnit.addTable(new Table("MyTable1"));
		testUnit.saveChangesAndReset();
		
		//execution
		final var result = testUnit.getSaveCount();
		
		//verification
		expect(result).isEqualTo(1);
	}
	
	//method
	@TestCase
	public void test_getSaveCount_whenSavesChangesAndResetsFor2Times() {
		
		//setup
		final var testUnit = NodeSchemaAdapter.forDatabaseNode("MyDatabase", new Node());
		testUnit.addTable(new Table("MyTable1"));
		testUnit.saveChangesAndReset();
		testUnit.addTable(new Table("MyTable2"));
		testUnit.saveChangesAndReset();
		
		//execution
		final var result = testUnit.getSaveCount();
		
		//verification
		expect(result).isEqualTo(2);
	}
}
