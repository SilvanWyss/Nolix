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
import ch.nolix.system.objectdata.model.Reference;

/**
 * @author Silvan Wyss
 */
final class ReferenceForMultipleTypesOnDatabaseTest extends StandardTest {
  private abstract static class Pet extends Entity {
    // This class is a sub class without additional methods.
  }

  private static final class Cat extends Pet {
    // This class is a sub class without additional methods.
  }

  private static final class Dog extends Pet {
    // This class is a sub class without additional methods.
  }

  private static final class Person extends Entity {
    public final Reference<Pet> pet = Reference.forEntityTypes(Cat.class, Dog.class);

    public Person() {
      initialize();
    }
  }

  @Test
  void testCase_getStoredEntity_whenIsNewAndContainsAny() {
    // setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var entityTypeSet = EntityTypeSet.withEntityType(Person.class, Cat.class, Dog.class);
    final var nodeDataAdapter = //
    NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("database").andSchema(entityTypeSet);
    final var garfield = new Cat();
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
  void testCase_isSaved_whenIsEdited() {
    // setup step 1: create and initialize node database
    final var nodeDatabase = MutableNode.createEmpty();
    final var entityTypeSet = EntityTypeSet.withEntityType(Person.class, Cat.class, Dog.class);
    final var step1NodeDataAdapter = //
    NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("database").andSchema(entityTypeSet);
    final var step1Garfield = new Cat();
    step1NodeDataAdapter.insertEntity(step1Garfield);
    final var step1John = new Person();
    step1John.pet.setEntity(step1Garfield);
    step1NodeDataAdapter.insertEntity(step1John);
    step1NodeDataAdapter.saveChanges();

    // setup step 2: load and edit Entity
    final var step2NodeDataAdapter = //
    NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("database").andSchema(entityTypeSet);
    final var step2John = step2NodeDataAdapter.getStoredEntityByTypeAndId(Person.class, step1John.getId());
    final var step2Odie = new Dog();
    step2NodeDataAdapter.insertEntity(step2Odie);
    step2John.pet.setEntity(step2Odie);

    // execute
    step2NodeDataAdapter.saveChanges();

    // verify
    final var step3NodeDataAdapter = //
    NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("database").andSchema(entityTypeSet);
    final var step3John = step3NodeDataAdapter.getStoredEntityByTypeAndId(Person.class, step1John.getId());
    final var step3Odie = step3John.pet.getStoredReferencedEntity();
    expect(step3Odie.getId()).isEqualTo(step2Odie.getId());
  }
}
