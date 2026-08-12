/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.objectdata.adapter;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.document.node.MutableNode;
import ch.nolix.base.errorcontrol.generalexception.ChangedResourceException;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.adapter.NodeDataAdapter;
import ch.nolix.system.objectdata.model.Entity;
import ch.nolix.system.objectdata.model.EntityTypeSet;
import ch.nolix.system.objectschema.adapter.NodeSchemaAdapter;
import ch.nolix.system.objectschema.model.Column;
import ch.nolix.systemapi.database.databaseproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

/**
 * @author Silvan Wyss
 */
final class NodeDataAdapterTest extends StandardTest {
  private static final class Pet extends Entity {
    Pet() {
      initialize();
    }
  }

  @Test
  void testCase_close_whenIsOpen() {
    // setup
    final var nodeDatabase = MutableNode.createEmpty();
    try ( //
    final var testUnit = //
    NodeDataAdapter
      .forNodeDatabase(nodeDatabase)
      .withName("MyDatabase")
      .andSchema(EntityTypeSet.EMPTY_SCHEMA)) {
      // setup verification
      expect(testUnit.isOpen()).isTrue();

      // execute
      testUnit.close();

      // verify
      expect(testUnit.isClosed()).isTrue();
    }
  }

  @Test
  void testCase_close_whenIsClosed() {
    // setup
    final var nodeDatabase = MutableNode.createEmpty();
    try ( //
    final var testUnit = //
    NodeDataAdapter
      .forNodeDatabase(nodeDatabase)
      .withName("MyDatabase")
      .andSchema(EntityTypeSet.EMPTY_SCHEMA)) {
      testUnit.close();

      // setup verification
      expect(testUnit.isClosed()).isTrue();

      // execute
      testUnit.close();

      // verify
      expect(testUnit.isClosed()).isTrue();
    }
  }

  @Test
  void testCase_constructor_whenTheGivenDatabaseIsEmpty() {
    // setup
    final var nodeDatabase = MutableNode.createEmpty();

    // execute
    final var result = //
    NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(EntityTypeSet.EMPTY_SCHEMA);

    // verify
    expect(result.getSaveCount()).isEqualTo(0);
    expect(result.isChangeFree()).isTrue();
  }

  @Test
  void testCase_constructor_whenTheGivenDatabaseIsNotValid() {
    // setup
    final var nodeDatabase = MutableNode.fromString("x(y,z)");

    // execute & verify
    expectRunning(
      () -> NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(EntityTypeSet.EMPTY_SCHEMA) //
    )
      .throwsException()
      .withMessage("The database has a schema that does not suit.");
  }

  @Test
  void testCase_getEmptyCopy_whenHasChanges() {
    // setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
    final var testUnit = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    testUnit.insertEntity(new Pet());

    // setup verification
    expect(testUnit.hasChanges()).isTrue();

    // execute
    final var result = testUnit.createEmptyCopy();

    // verify
    expect(testUnit.hasChanges()).isTrue();
    expect(result.isChangeFree()).isTrue();
  }

  @Test
  void testCase_saveChangesAndReset_whenDoesNotHaveChanges() {
    // setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
    final var testUnit = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);

    // execute
    testUnit.saveChanges();

    // verify
    expect(testUnit.getSaveCount()).isEqualTo(1);
    expect(testUnit.isChangeFree()).isTrue();
  }

  @Test
  void testCase_saveChangesAndReset_whenHasChanges() {
    // setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
    final var testUnit = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    testUnit.insertEntity(new Pet());

    // execute
    testUnit.saveChanges();

    // verify
    expect(testUnit.getSaveCount()).isEqualTo(1);
    expect(testUnit.isChangeFree()).isTrue();
  }

  @Test
  void testCase_saveChangesAndReset_whenHasChangesAndSchemaWasChangedInTheMeanwhile() {
    // setup step 1: create a database
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
    NodeDataAdapter
      .forNodeDatabase(nodeDatabase)
      .withName("my_database")
      .andSchema(schema)
      .saveChanges();

    // setup step 2: Prepare changes for the database.
    final var testUnit = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    testUnit.insertEntity(new Pet());

    // setup step 4: Edit the schema of the database.
    final var schemaAdapter = NodeSchemaAdapter.forNodeDatabase("MyDatabase", nodeDatabase);
    schemaAdapter
      .getStoredTableByName("Pet")
      .addColumn(
        Column.withIdAndNameAndContentModel(
          "id",
          "name",
          FieldType.VALUE_FIELD, DataType.STRING,
          ImmutableList.createEmpty(),
          ImmutableList.createEmpty()));
    schemaAdapter.saveChanges();

    // execute & verify: Try to save the the changes to the database.
    expectRunning(testUnit::saveChanges)
      .throwsException()
      .ofType(ChangedResourceException.class)
      .withMessage("The schema was changed in the meanwhile.");
  }
}
