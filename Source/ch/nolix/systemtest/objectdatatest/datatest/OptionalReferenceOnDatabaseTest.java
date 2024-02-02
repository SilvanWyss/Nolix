//package declaration
package ch.nolix.systemtest.objectdatatest.datatest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.data.OptionalReference;
import ch.nolix.system.objectdata.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdata.schema.Schema;

//class
public final class OptionalReferenceOnDatabaseTest extends Test {

  //constant
  private static final class Pet extends Entity {
  }

  //constant
  private static final class Person extends Entity {

    //attribute
    public final OptionalReference<Pet> pet = OptionalReference.forEntity(Pet.class);

    //constructor
    public Person() {
      initialize();
    }
  }

  //method
  @TestCase
  public void testCase_isSaved_whenIsNewAndEmpty() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var john = new Person();
    nodeDataAdapter.insert(john);

    //execution
    nodeDataAdapter.saveChanges();

    //verification
    final var loadedJohn = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());
    expect(loadedJohn.pet.isEmpty());
  }

  //method
  @TestCase
  public void testCase_getStoredEntity_whenIsNewAndNotEmpty() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insert(garfield);
    final var john = new Person();
    john.pet.setEntity(garfield);
    nodeDataAdapter.insert(john);

    //execution
    final var result = john.pet.getReferencedEntity();

    //verification
    expect(result).is(garfield);
  }

  //method
  @TestCase
  public void testCase_getStoredEntity_whenIsLoadedAndNotEmpty() {

    //setup part 1
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insert(garfield);
    final var john = new Person();
    john.pet.setEntity(garfield);
    nodeDataAdapter.insert(john);
    nodeDataAdapter.saveChanges();

    //setup part 2
    final var loadedJohn = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());

    //execution
    final var result = loadedJohn.pet.getReferencedEntity();

    //verification
    expect(result.getId()).isEqualTo(garfield.getId());
  }

  //method
  @TestCase
  public void testCase_isSaved_whenReferencedEntityIsDeleted() {

    //setup part 1: Initializes database.
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insert(garfield);
    nodeDataAdapter.saveChanges();

    //setup part 2: Prepares a change.
    final var nodeDataAdapterB = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var loadedGarfieldB = nodeDataAdapterB.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());
    final var johnB = new Person();
    johnB.pet.setEntity(loadedGarfieldB);
    nodeDataAdapterB.insert(johnB);

    //setup part 3: Deletes the referenced Entity.
    final var nodeDataAdapterC = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var loadedGarfieldC = nodeDataAdapterC.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());
    loadedGarfieldC.delete();
    nodeDataAdapterC.saveChanges();

    //execution & verification: Tries to save when the referenced Entity was deleted.
    expectRunning(nodeDataAdapterB::saveChanges).throwsException();
  }

  //method
  @TestCase
  public void testCase_setEntity_whenParentEntityBelongsToTableAndSetEntityDoesNot() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var john = new Person();
    nodeDataAdapter.insert(john);
    final var garfield = new Pet();

    //execution
    john.pet.setEntity(garfield);

    //verification
    expect(garfield.belongsToTable());
  }

  //method
  @TestCase
  public void testCase_setEntity_whenParentEntityBelongsToTableAndSetEntityDoesNot_andIsSaved() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var john = new Person();
    nodeDataAdapter.insert(john);
    final var garfield = new Pet();

    //execution
    john.pet.setEntity(garfield);
    nodeDataAdapter.saveChanges();

    //verification
    final var loadedJohn = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());
    final var loadedGarfield = //
    nodeDataAdapter.getStoredTableByEntityType(Pet.class).getStoredEntityById(garfield.getId());
    expect(loadedJohn.pet.getReferencedEntity()).is(loadedGarfield);
  }
}
