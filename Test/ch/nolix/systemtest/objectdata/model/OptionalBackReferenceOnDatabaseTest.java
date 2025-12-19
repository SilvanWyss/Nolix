package ch.nolix.systemtest.objectdata.model;

import org.junit.jupiter.api.Test;

import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.adapter.NodeDataAdapter;
import ch.nolix.system.objectdata.model.Entity;
import ch.nolix.system.objectdata.model.EntityTypeSet;
import ch.nolix.system.objectdata.model.OptionalBackReference;
import ch.nolix.system.objectdata.model.OptionalReference;

/**
 * @author Silvan Wyss
 */
final class OptionalBackReferenceOnDatabaseTest extends StandardTest {
  private static final class Person extends Entity {
    final OptionalReference<Pet> pet = OptionalReference.forEntityTypes(Pet.class);

    Person() {
      initialize();
    }
  }

  private static final class Pet extends Entity {
    final OptionalBackReference<Person> owner = //
    OptionalBackReference.forBackReferencedFieldNameAndBackReferenceableEntityTypes("pet", Person.class);

    Pet() {
      initialize();
    }
  }

  @Test
  void testCase_getStoredEntity_whenIsNewAndEmpty() {
    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);

    //execution
    final var result = garfield.owner.containsAny();

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_getStoredEntity_whenIsNewAndNotEmpty() {
    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Person.class, Pet.class);
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
    final var schema = EntityTypeSet.withEntityType(Person.class, Pet.class);
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
    final var schema = EntityTypeSet.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);

    //execution & verification
    expectRunning(nodeDataAdapter::saveChanges).doesNotThrowException();
  }

  @Test
  void testCase_isSaved_whenBackReferencedEntityIsDeleted() {
    //setup part 1:
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Person.class, Pet.class);
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

    //verification
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());
    expect(loadedGarfield.owner.isEmpty());
    expectRunning(nodeDataAdapter::saveChanges).doesNotThrowException();
  }

  @Test
  void testCase_isSaved_whenBackReferencedPropertyIsChanged() {
    //setup part 1
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Person.class, Pet.class);
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

    //setup part 2 verification
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());
    expect(loadedGarfield.owner.isEmpty()).isTrue();

    //execution & verification
    expectRunning(nodeDataAdapter::saveChanges).doesNotThrowException();
  }

  @Test
  void testCase_isChanged_whenIsLoaded() {
    //setup part 1
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);
    final var odie = new Pet();
    nodeDataAdapter.insertEntity(odie);
    final var john = new Person();
    john.pet.setEntity(garfield);
    nodeDataAdapter.insertEntity(john);

    //setup part 1 verification
    expect(john.pet.getStoredReferencedEntity()).is(garfield);
    expect(garfield.owner.getStoredBackReferencedEntity()).is(john);
    expect(odie.owner.isEmpty()).isTrue();

    //setup part 2
    nodeDataAdapter.saveChanges();

    //setup part 3
    final var loadedJohn = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());
    final var loadedOdie = nodeDataAdapter.getStoredTableByEntityType(Pet.class).getStoredEntityById(odie.getId());

    //execution
    loadedJohn.pet.setEntity(loadedOdie);

    //verification
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());
    expect(loadedJohn.pet.getStoredReferencedEntity()).is(loadedOdie);
    expect(loadedGarfield.owner.isEmpty());
    expect(loadedOdie.owner.getStoredBackReferencedEntity()).is(loadedJohn);
  }
}
