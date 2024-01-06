//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;
import ch.nolix.system.objectdatabase.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.OptionalBackReference;
import ch.nolix.system.objectdatabase.database.OptionalReference;
import ch.nolix.system.objectdatabase.schema.Schema;

//class
public final class OptionalBackReferenceOnDatabaseTest extends Test {

  //constant
  private static final class Person extends Entity {

    //attribute
    public final OptionalReference<Pet> pet = OptionalReference.forEntity(Pet.class);

    //constructor
    public Person() {
      initialize();
    }
  }

  //constant
  private static final class Pet extends Entity {

    //attribute
    public final OptionalBackReference<Person> owner = OptionalBackReference
      .forEntityAndBackReferencedPropertyName(Person.class, "pet");

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

    //execution
    final var result = garfield.owner.containsAny();

    //verification
    expectNot(result);
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
    expectRunning(nodeDataAdapter::saveChanges).doesNotThrowException();
  }

  //method
  @TestCase
  public void testCase_isSaved_whenBackReferencedEntityIsDeleted() {

    //setup part 1:
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

    //verification
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());
    expect(loadedGarfield.owner.isEmpty());
    expectRunning(nodeDataAdapter::saveChanges).doesNotThrowException();
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

    //setup part 2 verification
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());
    expect(loadedGarfield.owner.isEmpty());

    //execution & verification
    expectRunning(nodeDataAdapter::saveChanges).doesNotThrowException();
  }

  //method
  @TestCase
  public void testCase_isChanged_whenIsLoaded() {

    //setup part 1
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insert(garfield);
    final var odie = new Pet();
    nodeDataAdapter.insert(odie);
    final var john = new Person();
    john.pet.setEntity(garfield);
    nodeDataAdapter.insert(john);

    //setup part 1 verification
    expect(john.pet.getReferencedEntity()).is(garfield);
    expect(garfield.owner.getBackReferencedEntity()).is(john);
    expect(odie.owner.isEmpty());

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
    expect(loadedJohn.pet.getReferencedEntity()).is(loadedOdie);
    expect(loadedGarfield.owner.isEmpty());
    expect(loadedOdie.owner.getBackReferencedEntity()).is(loadedJohn);
  }
}
