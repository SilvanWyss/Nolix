//package declaration
package ch.nolix.systemtest.objectdatatest.datatest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.data.OptionalBackReference;
import ch.nolix.system.objectdata.data.OptionalReference;
import ch.nolix.system.objectdata.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdata.schema.Schema;

//class
public final class OptionalBackReferenceOnDatabaseTest extends StandardTest {

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
  @Test
  public void testCase_getStoredEntity_whenIsNewAndEmpty() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);

    //execution
    final var result = garfield.owner.containsAny();

    //verification
    expectNot(result);
  }

  //method
  @Test
  public void testCase_getStoredEntity_whenIsNewAndNotEmpty() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);
    final var john = new Person();
    john.pet.setEntity(garfield);
    nodeDataAdapter.insertEntity(john);

    //execution
    final var result = garfield.owner.getBackReferencedEntity();

    //verification
    expect(result).is(john);
  }

  //method
  @Test
  public void testCase_getStoredEntity_whenIsLoaded() {

    //setup
    final var nodeDatabase = new MutableNode();
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
    final var result = loadedGarfield.owner.getBackReferencedEntity();

    //verification
    expect(result.getId()).isEqualTo(john.getId());
  }

  //method
  @Test
  public void testCase_isSaved_whenIsEmpty() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);

    //execution & verification
    expectRunning(nodeDataAdapter::saveChanges).doesNotThrowException();
  }

  //method
  @Test
  public void testCase_isSaved_whenBackReferencedEntityIsDeleted() {

    //setup part 1:
    final var nodeDatabase = new MutableNode();
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

    //verification
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());
    expect(loadedGarfield.owner.isEmpty());
    expectRunning(nodeDataAdapter::saveChanges).doesNotThrowException();
  }

  //method
  @Test
  public void testCase_isSaved_whenBackReferencedPropertyIsChanged() {

    //setup part 1
    final var nodeDatabase = new MutableNode();
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

    //setup part 2 verification
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());
    expect(loadedGarfield.owner.isEmpty());

    //execution & verification
    expectRunning(nodeDataAdapter::saveChanges).doesNotThrowException();
  }

  //method
  @Test
  public void testCase_isChanged_whenIsLoaded() {

    //setup part 1
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);
    final var odie = new Pet();
    nodeDataAdapter.insertEntity(odie);
    final var john = new Person();
    john.pet.setEntity(garfield);
    nodeDataAdapter.insertEntity(john);

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
