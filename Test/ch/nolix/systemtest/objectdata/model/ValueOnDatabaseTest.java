/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.objectdata.model;

import org.junit.jupiter.api.Test;

import ch.nolix.base.document.node.MutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.adapter.NodeDataAdapter;
import ch.nolix.system.objectdata.model.Entity;
import ch.nolix.system.objectdata.model.EntityTypeSet;
import ch.nolix.system.objectdata.model.ValueField;
import ch.nolix.systemapi.database.databaseobject.DatabaseObjectState;

/**
 * @author Silvan Wyss
 */
final class ValueOnDatabaseTest extends StandardTest {
  private static final class Pet extends Entity {
    final ValueField<String> name = ValueField.withValueType(String.class);

    Pet() {
      initialize();
    }
  }

  @Test
  void testCase_isSaved_whenIsEmpty() {
    // setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);

    // execute & verify
    expectRunning(nodeDataAdapter::saveChanges).throwsException();
  }

  @Test
  void testCase_getStoredValue_whenContainsAnyAndIsNotSaved() {
    // setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    garfield.name.setValue("Garfield");
    nodeDataAdapter.insertEntity(garfield);

    // execute
    final var result = garfield.name.getStoredValue();

    // verify
    expect(result).isEqualTo("Garfield");
  }

  @Test
  void testCase_getStoredValue_whenContainsAnyAndIsSaved() {
    // setup step 1
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    garfield.name.setValue("Garfield");
    nodeDataAdapter.insertEntity(garfield);
    nodeDataAdapter.saveChanges();

    // setup step 2
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());

    // execute
    final var result = loadedGarfield.name.getStoredValue();

    // verify
    expect(result).isEqualTo("Garfield");
  }

  @Test
  void testCase_getState_whenIsNewAndNotEdited() {
    // setup
    final var garfield = new Pet();

    // setup verification
    expect(garfield.getState()).is(DatabaseObjectState.NEW);

    // execute
    final var result = garfield.name.getState();

    // verify
    expect(result).is(DatabaseObjectState.NEW);
  }

  @Test
  void testCase_getState_whenIsNewAndEdited() {
    // setup
    final var garfield = new Pet();
    garfield.name.setValue("Garfield");

    // setup verification
    expect(garfield.getState()).is(DatabaseObjectState.NEW);

    // execute
    final var result = garfield.name.getState();

    // verify
    expect(result).is(DatabaseObjectState.NEW);
  }

  @Test
  void testCase_getState_whenIsClosed() {
    // setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    garfield.name.setValue("Garfield");
    nodeDataAdapter.insertEntity(garfield);
    nodeDataAdapter.saveChanges();

    // setup verification
    expect(garfield.getState()).is(DatabaseObjectState.CLOSED);

    // execute
    final var result = garfield.name.getState();

    // verify
    expect(result).is(DatabaseObjectState.CLOSED);
  }

  @Test
  void testCase_getState_whenIsLoaded() {
    // setup step 1
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    garfield.name.setValue("Garfield");
    nodeDataAdapter.insertEntity(garfield);
    nodeDataAdapter.saveChanges();

    // setup step 2
    final var loaedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());

    // setup verification
    expect(loaedGarfield.getState()).is(DatabaseObjectState.UNEDITED);

    // execute
    final var result = loaedGarfield.name.getState();

    // verify
    expect(result).is(DatabaseObjectState.UNEDITED);
  }
}
