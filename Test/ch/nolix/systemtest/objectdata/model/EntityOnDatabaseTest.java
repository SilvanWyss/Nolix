/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.objectdata.model;

import org.junit.jupiter.api.Test;

import ch.nolix.base.document.node.MutableNode;
import ch.nolix.base.errorcontrol.generalexception.ChangedResourceException;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.adapter.NodeDataAdapter;
import ch.nolix.system.objectdata.model.Entity;
import ch.nolix.system.objectdata.model.EntityTypeSet;
import ch.nolix.system.objectdata.model.ValueField;
import ch.nolix.systemapi.databaseobject.model.DatabaseObjectState;

/**
 * @author Silvan Wyss
 */
final class EntityOnDatabaseTest extends StandardTest {
  private static final class Pet extends Entity {
    final ValueField<Integer> ageInYears = ValueField.withValueType(Integer.class);

    Pet() {
      initialize();
    }

    void setInsertAction_(final Runnable insertAction) {
      setInsertAction(insertAction);
    }
  }

  @Test
  void testCase_isInserted_whenHasInsertAction() {
    // setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var testUnit = new Pet();
    testUnit.ageInYears.setValue(0);
    testUnit.setInsertAction_(() -> testUnit.ageInYears.setValue(1));

    // setup verification
    expect(testUnit.ageInYears.getStoredValue()).isEqualTo(0);

    // execute
    nodeDataAdapter.insertEntity(testUnit);

    // verify
    expect(testUnit.ageInYears.getStoredValue()).isEqualTo(1);
  }

  @Test
  void testCase_isLoaded() {
    // setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var garfield = new Pet();
    garfield.ageInYears.setValue(5);
    nodeDataAdapter.insertEntity(garfield);
    nodeDataAdapter.saveChanges();

    // execute
    final var loadedGarfield = //
    nodeDataAdapter.getStoredTableByEntityType(Pet.class).getStoredEntityById(garfield.getId());

    // verify
    expect(loadedGarfield.getState()).is(DatabaseObjectState.UNEDITED);
    expect(loadedGarfield.getSaveStamp()).isNotEmpty();
  }

  @Test
  void testCase_isSaved() {
    // setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    garfield.ageInYears.setValue(5);
    nodeDataAdapter.insertEntity(garfield);

    // execute
    nodeDataAdapter.saveChanges();

    // verify
    expect(garfield.isClosed()).isTrue();
  }

  @Test
  void testCase_isSaved_whenIsChangedInTheMeanwhile() {
    // setup step 1: initialize database
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
    final var nodeDataAdapterA = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfieldA = new Pet();
    garfieldA.ageInYears.setValue(5);
    nodeDataAdapterA.insertEntity(garfieldA);
    nodeDataAdapterA.saveChanges();

    // setup step 2: prepare change
    final var nodeDataAdapterB = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfieldB = nodeDataAdapterB.getStoredTableByEntityType(Pet.class).getStoredEntityById(garfieldA.getId());
    garfieldB.ageInYears.setValue(6);

    // setup step 3: apply change
    final var nodeDataAdapterC = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfieldC = nodeDataAdapterC.getStoredTableByEntityType(Pet.class).getStoredEntityById(garfieldA.getId());
    garfieldC.ageInYears.setValue(6);
    nodeDataAdapterC.saveChanges();

    // execute: try to save change
    expectRunning(nodeDataAdapterB::saveChanges)
      .throwsException()
      .ofType(ChangedResourceException.class)
      .withMessage("The data was changed in the meanwhile.");
  }

  @Test
  void testCase_isSaved_whenIsDeletedInTheMeanwhile() {
    // setup step 1: initialize database
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
    final var nodeDataAdapterA = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfieldA = new Pet();
    garfieldA.ageInYears.setValue(5);
    nodeDataAdapterA.insertEntity(garfieldA);
    nodeDataAdapterA.saveChanges();

    // setup step 2: prepare change
    final var nodeDataAdapterB = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfieldB = nodeDataAdapterB.getStoredTableByEntityType(Pet.class).getStoredEntityById(garfieldA.getId());
    garfieldB.ageInYears.setValue(6);

    // setup step 3: delete the Entity
    final var nodeDataAdapterC = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfieldC = nodeDataAdapterC.getStoredTableByEntityType(Pet.class).getStoredEntityById(garfieldA.getId());
    garfieldC.delete();
    nodeDataAdapterC.saveChanges();

    // execute & verify: try to save changes
    expectRunning(nodeDataAdapterB::saveChanges)
      .throwsException()
      .ofType(ChangedResourceException.class)
      .withMessage("The data was changed in the meanwhile.");
  }

  @Test
  void testCase_delete_whenIsLoaded() {
    // setup step 1
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    garfield.ageInYears.setValue(5);
    nodeDataAdapter.insertEntity(garfield);
    nodeDataAdapter.saveChanges();

    // setup step 2
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());

    // execute part 1
    loadedGarfield.delete();

    // verify part 1
    expect(loadedGarfield.isDeleted()).isTrue();

    // execute part 2
    nodeDataAdapter.saveChanges();

    // verify part 2
    expect(loadedGarfield.isClosed());
    expect(
      nodeDataAdapter
        .getStoredTableByEntityType(Pet.class)
        .getStoredEntities()
        .containsNoMatching(e -> e.hasId(garfield.getId())));
  }

  @Test
  void testCase_delete_whenIsClosed() {
    // setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    garfield.ageInYears.setValue(5);
    nodeDataAdapter.insertEntity(garfield);
    nodeDataAdapter.saveChanges();

    // execute & verify
    expectRunning(garfield::delete).throwsException();
  }
}
