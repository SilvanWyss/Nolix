//package declaration
package ch.nolix.systemtest.objectschematest.schemaadaptertest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.system.objectschema.schema.Table;
import ch.nolix.system.objectschema.schemaadapter.NodeSchemaAdapter;

//class
public final class NodeSchemaAdapterTest extends StandardTest {

  //method
  @Test
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
    expect(database.getChildNodeCount()).isEqualTo(2);
    expect(database.getStoredChildNodeAt1BasedIndex(1).getHeader()).isEqualTo("DatabaseProperties");
    expect(database.getStoredChildNodeAt1BasedIndex(2).getHeader()).isEqualTo("EntityHeads");
  }

  //method
  @Test
  public void test_addTable_whenSavesChangesAndResets() {

    //setup
    final var database = new MutableNode();
    final var testUnit = NodeSchemaAdapter.forDatabaseNode("MyDatabase", database);

    //execution
    testUnit.addTable(new Table("MyTable")).saveChanges();

    //verification part 1
    expect(testUnit.isChangeFree());
    expect(testUnit.getStoredTables().getElementCount()).isEqualTo(1);
    expect(testUnit.getStoredTables().containsOne(t -> t.hasName("MyTable")));

    //verification part 2
    final var tableNodes = database.getStoredChildNodesWithHeader("Table");
    expect(tableNodes.containsOne());
    final var tableNode = tableNodes.getStoredFirst();
    final var nameNode = tableNode.getStoredFirstChildNodeWithHeader("Name");
    expect(nameNode.getSingleChildNodeHeader()).isEqualTo("MyTable");
  }

  //method
  @Test
  public void test_getSaveCount_whenIsNew() {

    //setup
    final var testUnit = NodeSchemaAdapter.forDatabaseNode("MyDatabase", new MutableNode());

    //execution
    final var result = testUnit.getSaveCount();

    //verification
    expect(result).isEqualTo(0);
  }

  //method
  @Test
  public void test_getSaveCount_whenSavesChangesAndResetsFor1Times() {

    //setup
    final var testUnit = NodeSchemaAdapter.forDatabaseNode("MyDatabase", new MutableNode());
    testUnit.addTable(new Table("MyTable1"));
    testUnit.saveChanges();

    //execution
    final var result = testUnit.getSaveCount();

    //verification
    expect(result).isEqualTo(1);
  }

  //method
  @Test
  public void test_getSaveCount_whenSavesChangesAndResetsFor2Times() {

    //setup
    final var testUnit = NodeSchemaAdapter.forDatabaseNode("MyDatabase", new MutableNode());
    testUnit.addTable(new Table("MyTable1"));
    testUnit.saveChanges();
    testUnit.addTable(new Table("MyTable2"));
    testUnit.saveChanges();

    //execution
    final var result = testUnit.getSaveCount();

    //verification
    expect(result).isEqualTo(2);
  }
}
