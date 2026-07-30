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
import ch.nolix.system.objectdata.model.OptionalReference;

/**
 * @author Silvan Wyss
 */
final class OptionalReferenceOnDatabaseTest extends StandardTest {
  private static final class Pet extends Entity {
    // This class is a sub class without additional methods.
  }

  private static final class Person extends Entity {
    final OptionalReference<Pet> pet = OptionalReference.forEntityTypes(Pet.class);

    Person() {
      initialize();
    }
  }

  @Test
  void testCase_isSaved_whenIsNewAndEmpty() {
    // setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var john = new Person();
    nodeDataAdapter.insertEntity(john);

    // execute
    nodeDataAdapter.saveChanges();

    // verify
    final var loadedJohn = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());
    expect(loadedJohn.pet.isEmpty()).isTrue();
  }

  @Test
  void testCase_getStoredEntity_whenIsNewAndNotEmpty() {
    // setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);
    final var john = new Person();
    john.pet.setEntity(garfield);
    nodeDataAdapter.insertEntity(john);

    // execute
    final var result = john.pet.getStoredReferencedEntity();

    // verify
    expect(result).is(garfield);
  }

  @Test
  void testCase_getStoredEntity_whenIsLoadedAndNotEmpty() {
    // setup step 1
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);
    final var john = new Person();
    john.pet.setEntity(garfield);
    nodeDataAdapter.insertEntity(john);
    nodeDataAdapter.saveChanges();

    // setup step 2
    final var loadedJohn = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());

    // execute
    final var result = loadedJohn.pet.getStoredReferencedEntity();

    // verify
    expect(result.getId()).isEqualTo(garfield.getId());
  }

  @Test
  void testCase_isSaved_whenReferencedEntityIsDeleted() {
    // setup step 1: initialize database
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);
    nodeDataAdapter.saveChanges();

    // setup step 2: prepare change
    final var nodeDataAdapterB = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var loadedGarfieldB = nodeDataAdapterB.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());
    final var johnB = new Person();
    johnB.pet.setEntity(loadedGarfieldB);
    nodeDataAdapterB.insertEntity(johnB);

    // setup step 3: delete referenced Entity
    final var nodeDataAdapterC = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var loadedGarfieldC = nodeDataAdapterC.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());
    loadedGarfieldC.delete();
    nodeDataAdapterC.saveChanges();

    // execute & verify: try to save changes
    expectRunning(nodeDataAdapterB::saveChanges).throwsException();
  }

  @Test
  void testCase_setEntity_whenParentEntityBelongsToTableAndSetEntityDoesNot() {
    // setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var john = new Person();
    nodeDataAdapter.insertEntity(john);
    final var garfield = new Pet();

    // execute
    john.pet.setEntity(garfield);

    // verify
    expect(garfield.belongsToTable()).isTrue();
  }

  @Test
  void testCase_setEntity_whenParentEntityBelongsToTableAndSetEntityDoesNot_andIsSaved() {
    // setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var john = new Person();
    nodeDataAdapter.insertEntity(john);
    final var garfield = new Pet();

    // execute
    john.pet.setEntity(garfield);
    nodeDataAdapter.saveChanges();

    // verify
    final var loadedJohn = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());
    final var loadedGarfield = //
    nodeDataAdapter.getStoredTableByEntityType(Pet.class).getStoredEntityById(garfield.getId());
    expect(loadedJohn.pet.getStoredReferencedEntity()).is(loadedGarfield);
  }
}
