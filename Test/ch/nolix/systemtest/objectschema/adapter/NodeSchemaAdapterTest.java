/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.objectschema.adapter;

import org.junit.jupiter.api.Test;

import ch.nolix.base.document.node.MutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.objectschema.adapter.NodeSchemaAdapter;
import ch.nolix.system.objectschema.model.Table;

/**
 * @author Silvan Wyss
 */
final class NodeSchemaAdapterTest extends StandardTest {
  @Test
  void test_creation() {
    // setup
    final var database = MutableNode.createEmpty();

    // setup verification
    expect(database.isBlank()).isTrue();

   // execute
    final var testUnit = NodeSchemaAdapter.forNodeDatabase("MyDatabase", database);

   // verify part 1
    expect(testUnit.isChangeFree());

   // verify part 2
    expect(database.getHeader()).isEqualTo("Database");
    expect(database.getChildNodeCount()).isEqualTo(2);
    expect(database.getStoredChildNodeAtOneBasedIndex(1).getHeader()).isEqualTo("DatabaseProperties");
    expect(database.getStoredChildNodeAtOneBasedIndex(2).getHeader()).isEqualTo("EntityIndexes");
  }

  @Test
  void test_addTable_whenSavesChangesAndResets() {
    // setup
    final var database = MutableNode.createEmpty();
    final var testUnit = NodeSchemaAdapter.forNodeDatabase("MyDatabase", database);

   // execute
    testUnit.addTable(Table.withName("MyTable")).saveChanges();

   // verify part 1
    expect(testUnit.isChangeFree()).isTrue();
    expect(testUnit.getStoredTables().getCount()).isEqualTo(1);
    expect(testUnit.getStoredTables().containsOneMatching(t -> t.hasName("MyTable")));

   // verify part 2
    final var tableNodes = database.getStoredChildNodesWithHeader("Table");
    expect(tableNodes.containsOne()).isTrue();
    final var tableNode = tableNodes.getStoredFirstNonNull();
    final var nameNode = tableNode.getStoredFirstChildNodeWithHeader("Name");
    expect(nameNode.getSingleChildNodeHeader()).isEqualTo("MyTable");
  }

  @Test
  void test_getSaveCount_whenIsNew() {
    // setup
    final var testUnit = NodeSchemaAdapter.forNodeDatabase("MyDatabase", MutableNode.createEmpty());

   // execute
    final var result = testUnit.getSaveCount();

   // verify
    expect(result).isEqualTo(0);
  }

  @Test
  void test_getSaveCount_whenSavesChangesAndResetsFor1Times() {
    // setup
    final var testUnit = NodeSchemaAdapter.forNodeDatabase("MyDatabase", MutableNode.createEmpty());
    testUnit.addTable(Table.withName("MyTable1"));
    testUnit.saveChanges();

   // execute
    final var result = testUnit.getSaveCount();

   // verify
    expect(result).isEqualTo(1);
  }

  @Test
  void test_getSaveCount_whenSavesChangesAndResetsFor2Times() {
    // setup
    final var testUnit = NodeSchemaAdapter.forNodeDatabase("MyDatabase", MutableNode.createEmpty());
    testUnit.addTable(Table.withName("MyTable1"));
    testUnit.saveChanges();
    testUnit.addTable(Table.withName("MyTable2"));
    testUnit.saveChanges();

   // execute
    final var result = testUnit.getSaveCount();

   // verify
    expect(result).isEqualTo(2);
  }
}
