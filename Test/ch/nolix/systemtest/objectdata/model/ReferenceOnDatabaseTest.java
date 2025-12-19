package ch.nolix.systemtest.objectdata.model;

import org.junit.jupiter.api.Test;

import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.adapter.NodeDataAdapter;
import ch.nolix.system.objectdata.model.Entity;
import ch.nolix.system.objectdata.model.EntityTypeSet;
import ch.nolix.system.objectdata.model.Reference;

/**
 * @author Silvan Wyss
 */
final class ReferenceOnDatabaseTest extends StandardTest {
  private static final class Pet extends Entity {
    //This class is just a sub class without additional methods.
  }

  private static final class Person extends Entity {
    final Reference<Pet> pet = Reference.forEntityTypes(Pet.class);

    Person() {
      initialize();
    }
  }

  @Test
  void testCase_getStoredEntity_whenIsNewAndContainsAny() {
    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);
    final var john = new Person();
    john.pet.setEntity(garfield);
    nodeDataAdapter.insertEntity(john);

    //execution
    final var result = john.pet.getStoredReferencedEntity();

    //verification
    expect(result).is(garfield);
  }

  @Test
  void testCase_getStoredEntity_whenIsLoadedAndContainsAny() {
    //setup part 1
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);
    final var john = new Person();
    john.pet.setEntity(garfield);
    nodeDataAdapter.insertEntity(john);
    nodeDataAdapter.saveChanges();

    //setup part 2
    final var loadedJohn = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());

    //execution
    final var result = loadedJohn.pet.getStoredReferencedEntity();

    //verification
    expect(result.getId()).isEqualTo(garfield.getId());
  }

  @Test
  void testCase_isSaved_whenIsNewAndEmpty() {
    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var john = new Person();
    nodeDataAdapter.insertEntity(john);

    //execution & verification
    expectRunning(nodeDataAdapter::saveChanges).throwsException();
  }

  @Test
  void testCase_isSaved_whenIsEditedAndReferencedEntityIsDeleted() {
    //setup part 1: Initializes database.
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);
    nodeDataAdapter.saveChanges();

    //setup part 2: Prepares a change.
    final var nodeDataAdapterB = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database")
      .andSchema(schema);
    final var loadedGarfieldB = nodeDataAdapterB.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());
    final var johnB = new Person();
    johnB.pet.setEntity(loadedGarfieldB);
    nodeDataAdapterB.insertEntity(johnB);

    //setup part 3: Deletes the referenced Entity.
    final var nodeDataAdapterC = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database")
      .andSchema(schema);
    final var loadedGarfieldC = nodeDataAdapterC.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());
    loadedGarfieldC.delete();
    nodeDataAdapterC.saveChanges();

    //execution & verification: Tries to save when the referenced Entity is deleted.
    expectRunning(nodeDataAdapterB::saveChanges).throwsException();
  }

  @Test
  void testCase_setEntity_whenParentEntityBelongsToTableAndSetEntityDoesNot() {
    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var john = new Person();
    nodeDataAdapter.insertEntity(john);
    final var garfield = new Pet();

    //execution
    john.pet.setEntity(garfield);

    //verification
    expect(garfield.belongsToTable()).isTrue();
  }

  @Test
  void testCase_setEntity_whenParentEntityBelongsToTableAndSetEntityDoesNot_andIsSaved() {
    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var john = new Person();
    nodeDataAdapter.insertEntity(john);
    final var garfield = new Pet();

    //execution
    john.pet.setEntity(garfield);
    nodeDataAdapter.saveChanges();

    //verification
    final var loadedJohn = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());
    final var loadedGarfield = //
    nodeDataAdapter.getStoredTableByEntityType(Pet.class).getStoredEntityById(garfield.getId());
    expect(loadedJohn.pet.getStoredReferencedEntity()).is(loadedGarfield);
  }
}
