//package declaration
package ch.nolix.systemtest.objectdatatest.datatest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;
import ch.nolix.system.objectdata.data.BackReference;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.data.Reference;
import ch.nolix.system.objectdata.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdata.schema.Schema;

//class
public final class BackReferenceOnDatabaseTest extends Test {

  //constant
  private static final class Person extends Entity {

    //attribute
    public final Reference<Pet> pet = Reference.forEntity(Pet.class);

    //constructor
    public Person() {
      initialize();
    }
  }

  //constant
  private static final class Pet extends Entity {

    //attribute
    public final BackReference<Person> owner = BackReference.forEntityAndBackReferencedPropertyName(Person.class,
      "pet");

    //constructor
    public Pet() {
      initialize();
    }
  }

  //method
  @TestCase
  public void testCase_getStoredEntity_whenIsNewAndEmpty() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insert(garfield);

    //execution & verification
    expectRunning(garfield.owner::getBackReferencedEntity).throwsException();
  }

  //method
  @TestCase
  public void testCase_getStoredEntity_whenIsNewAndNotEmpty() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insert(garfield);
    final var john = new Person();
    john.pet.setEntity(garfield);
    nodeDataAdapter.insert(john);

    //execution
    final var result = garfield.owner.getBackReferencedEntity();

    //verification
    expect(result).is(john);
  }

  //method
  @TestCase
  public void testCase_getStoredEntity_whenIsLoaded() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insert(garfield);
    final var john = new Person();
    john.pet.setEntity(garfield);
    nodeDataAdapter.insert(john);
    nodeDataAdapter.saveChanges();

    //execution
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());
    final var result = loadedGarfield.owner.getBackReferencedEntity();

    //verification
    expect(result.getId()).isEqualTo(john.getId());
  }

  //method
  @TestCase
  public void testCase_isSaved_whenIsEmpty() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insert(garfield);

    //execution & verification
    expectRunning(nodeDataAdapter::saveChanges).throwsException();
  }

  //method
  @TestCase
  public void testCase_isSaved_whenBackReferencedEntityIsDeleted() {

    //setup part 1
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insert(garfield);
    final var john = new Person();
    john.pet.setEntity(garfield);
    nodeDataAdapter.insert(john);
    nodeDataAdapter.saveChanges();

    //setup part 2
    final var loadedJohn = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());
    loadedJohn.delete();

    //execution & verification
    expectRunning(nodeDataAdapter::saveChanges).throwsException();
  }

  //method
  @TestCase
  public void testCase_isSaved_whenBackReferencedPropertyIsChanged() {

    //setup part 1
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insert(garfield);
    final var john = new Person();
    john.pet.setEntity(garfield);
    nodeDataAdapter.insert(john);
    nodeDataAdapter.saveChanges();

    //setup part 2
    final var bello = new Pet();
    nodeDataAdapter.insert(bello);
    final var loadedJohn = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());
    loadedJohn.pet.setEntity(bello);

    //setup verification
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());
    expect(loadedGarfield.owner.isEmpty());

    //execution & verification
    expectRunning(nodeDataAdapter::saveChanges).throwsException();
  }
}
