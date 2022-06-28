//package declaration
package ch.nolix.systemtest.objectschematest.schemaadaptertest;

//own imports
import ch.nolix.core.document.node.MutableNode;
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
		final var database = new MutableNode();
		
		//setup verification
		expect(database.isBlank());
		
		//execution
		final var testUnit = NodeSchemaAdapter.forDatabaseNode("MyDatabase", database);
		
		//verification part 1
		expect(testUnit.isChangeFree());
		
		//verification part 2
		expect(database.getHeader()).isEqualTo("Database");
		expect(database.getChildNodeCount()).isEqualTo(1);
		expect(database.getRefChildNodeAt1BasedIndex(1).getHeader()).isEqualTo("DatabaseProperties");
	}
	
	//method
	@TestCase
	public void test_addTable_whenSavesChangesAndResets() {
		
		//setup
		final var database = new MutableNode();
		final var testUnit = NodeSchemaAdapter.forDatabaseNode("MyDatabase", database);
		
		//execution
		testUnit.addTable(new Table("MyTable")).saveChangesAndReset();
		
		//verification part 1
		expect(testUnit.isChangeFree());
		expect(testUnit.getRefTables().getElementCount()).isEqualTo(1);
		expect(testUnit.getRefTables().containsOne(t -> t.hasName("MyTable")));
		
		//verification part 2
		final var tableNodes = database.getRefChildNodesWithHeader("Table");
		expect(tableNodes.containsOne());
		final var tableNode  = tableNodes.getRefFirst();
		final var nameNode = tableNode.getRefFirstChildNodeWithHeader("Name");
		expect(nameNode.getSingleChildNodeHeader()).isEqualTo("MyTable");
	}
	
	//method
	@TestCase
	public void test_getSaveCount_whenIsNew() {
		
		//setup
		final var testUnit = NodeSchemaAdapter.forDatabaseNode("MyDatabase", new MutableNode());
		
		//execution
		final var result = testUnit.getSaveCount();
		
		//verification
		expect(result).isEqualTo(0);
	}
	
	//method
	@TestCase
	public void test_getSaveCount_whenSavesChangesAndResetsFor1Times() {
		
		//setup
		final var testUnit = NodeSchemaAdapter.forDatabaseNode("MyDatabase", new MutableNode());
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
		final var testUnit = NodeSchemaAdapter.forDatabaseNode("MyDatabase", new MutableNode());
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
