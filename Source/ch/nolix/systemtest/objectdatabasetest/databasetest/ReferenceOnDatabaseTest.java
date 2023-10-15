//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.Reference;
import ch.nolix.system.objectdatabase.schema.Schema;

//class
public final class ReferenceOnDatabaseTest extends Test {

  // static class
  private static final class Pet extends Entity {
  }

  // static class
  private static final class Person extends Entity {

    // attribute
    public final Reference<Pet> pet = Reference.forEntity(Pet.class);

    // constructor
    public Person() {
      initialize();
    }
  }

  // method
  @TestCase
  public void testCase_getStoredEntity_whenIsNewAndContainsAny() {

    // setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insert(garfield);
    final var john = new Person();
    john.pet.setEntity(garfield);
    nodeDataAdapter.insert(john);

    // execution
    final var result = john.pet.getReferencedEntity();

    // verification
    expect(result).is(garfield);
  }

  // method
  @TestCase
  public void testCase_getStoredEntity_whenIsLoadedAndContainsAny() {

    // setup part 1
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insert(garfield);
    final var john = new Person();
    john.pet.setEntity(garfield);
    nodeDataAdapter.insert(john);
    nodeDataAdapter.saveChanges();

    // setup part 2
    final var loadedJohn = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());

    // execution
    final var result = loadedJohn.pet.getReferencedEntity();

    // verification
    expect(result.getId()).isEqualTo(garfield.getId());
  }

  // method
  @TestCase
  public void testCase_isSaved_whenIsNewAndEmpty() {

    // setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var john = new Person();
    nodeDataAdapter.insert(john);

    // execution & verification
    expectRunning(nodeDataAdapter::saveChanges).throwsException();
  }

  // method
  @TestCase
  public void testCase_isSaved_whenIsEditedAndReferencedEntityIsDeleted() {

    // setup part 1: Initializes database.
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insert(garfield);
    nodeDataAdapter.saveChanges();

    // setup part 2: Prepares a change.
    final var nodeDataAdapterB = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database")
        .andSchema(schema);
    final var loadedGarfieldB = nodeDataAdapterB.getStoredTableByEntityType(Pet.class)
        .getStoredEntityById(garfield.getId());
    final var johnB = new Person();
    johnB.pet.setEntity(loadedGarfieldB);
    nodeDataAdapterB.insert(johnB);

    // setup part 3: Deletes the referenced Entity.
    final var nodeDataAdapterC = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database")
        .andSchema(schema);
    final var loadedGarfieldC = nodeDataAdapterC.getStoredTableByEntityType(Pet.class)
        .getStoredEntityById(garfield.getId());
    loadedGarfieldC.delete();
    nodeDataAdapterC.saveChanges();

    // execution & verification: Tries to save when the referenced Entity is
    // deleted.
    expectRunning(nodeDataAdapterB::saveChanges).throwsException();
  }
}
