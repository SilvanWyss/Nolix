package ch.nolix.systemtest.objectdatatest.datatest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.data.BackReference;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.data.Reference;
import ch.nolix.system.objectdata.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdata.schema.Schema;

final class BackReferenceOnDatabaseTest extends StandardTest {

  private static final class Person extends Entity {

    final Reference<Pet> pet = Reference.forEntity(Pet.class);

    Person() {
      initialize();
    }
  }

  private static final class Pet extends Entity {

    final BackReference<Person> owner = BackReference.forEntityAndBackReferencedFieldName(Person.class,
      "pet");

    Pet() {
      initialize();
    }
  }

  @Test
  void testCase_getStoredEntity_whenIsNewAndEmpty() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = Schema.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);

    //execution & verification
    expectRunning(garfield.owner::getStoredBackReferencedEntity).throwsException();
  }

  @Test
  void testCase_getStoredEntity_whenIsNewAndNotEmpty() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = Schema.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);
    final var john = new Person();
    john.pet.setEntity(garfield);
    nodeDataAdapter.insertEntity(john);

    //execution
    final var result = garfield.owner.getStoredBackReferencedEntity();

    //verification
    expect(result).is(john);
  }

  @Test
  void testCase_getStoredEntity_whenIsLoaded() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = Schema.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);
    final var john = new Person();
    john.pet.setEntity(garfield);
    nodeDataAdapter.insertEntity(john);
    nodeDataAdapter.saveChanges();

    //execution
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());
    final var result = loadedGarfield.owner.getStoredBackReferencedEntity();

    //verification
    expect(result.getId()).isEqualTo(john.getId());
  }

  @Test
  void testCase_isSaved_whenIsEmpty() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = Schema.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);

    //execution & verification
    expectRunning(nodeDataAdapter::saveChanges).throwsException();
  }

  @Test
  void testCase_isSaved_whenBackReferencedEntityIsDeleted() {

    //setup part 1
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = Schema.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);
    final var john = new Person();
    john.pet.setEntity(garfield);
    nodeDataAdapter.insertEntity(john);
    nodeDataAdapter.saveChanges();

    //setup part 2
    final var loadedJohn = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());
    loadedJohn.delete();

    //execution & verification
    expectRunning(nodeDataAdapter::saveChanges).throwsException();
  }

  @Test
  void testCase_isSaved_whenBackReferencedPropertyIsChanged() {

    //setup part 1
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = Schema.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);
    final var john = new Person();
    john.pet.setEntity(garfield);
    nodeDataAdapter.insertEntity(john);
    nodeDataAdapter.saveChanges();

    //setup part 2
    final var bello = new Pet();
    nodeDataAdapter.insertEntity(bello);
    final var loadedJohn = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());
    loadedJohn.pet.setEntity(bello);

    //setup verification
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());
    expect(loadedGarfield.owner.isEmpty()).isTrue();

    //execution & verification
    expectRunning(nodeDataAdapter::saveChanges).throwsException();
  }
}
