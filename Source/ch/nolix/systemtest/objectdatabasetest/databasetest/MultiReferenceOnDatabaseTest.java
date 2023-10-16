//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.basetest.IgnoreTimeout;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.MultiReference;
import ch.nolix.system.objectdatabase.schema.Schema;

//class
public final class MultiReferenceOnDatabaseTest extends Test {

  //constant
  private static final class Person extends Entity {

    //attribute
    public final MultiReference<Pet> pets = MultiReference.forEntity(Pet.class);

    //constructor
    public Person() {
      initialize();
    }
  }

  //constant
  private static final class Pet extends Entity {

    //constructor
    public Pet() {
      initialize();
    }
  }

  //method
  @TestCase
  public void testCase_whenIsLoadedAndEmpty() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var john = new Person();
    nodeDataAdapter.insert(john);
    nodeDataAdapter.saveChanges();

    //execution
    final var loadedJohn = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());

    //verification
    expect(loadedJohn.pets.isEmpty());
  }

  //method
  @TestCase
  public void testCase_whenIsLoadedAndNotEmpty() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    final var odie = new Pet();
    nodeDataAdapter.insert(garfield);
    nodeDataAdapter.insert(odie);
    final var john = new Person();
    john.pets.addEntity(garfield);
    john.pets.addEntity(odie);
    nodeDataAdapter.insert(john);
    nodeDataAdapter.saveChanges();

    //execution
    final var loadedJohn = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());

    //verification
    expect(loadedJohn.pets.getReferencedEntities().getElementCount()).isEqualTo(2);
    expect(loadedJohn.pets.getReferencedEntities().containsAny(p -> p.hasId(garfield.getId())));
    expect(loadedJohn.pets.getReferencedEntities().containsAny(p -> p.hasId(odie.getId())));
  }

  //method
  @TestCase
  public void testCase_whenReferencedEntityIsLoadedAndDeleted() {

    //setup part 1: initialize database
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    final var odie = new Pet();
    nodeDataAdapter.insert(garfield);
    nodeDataAdapter.insert(odie);
    final var john = new Person();
    john.pets.addEntity(garfield);
    john.pets.addEntity(odie);
    nodeDataAdapter.insert(john);
    nodeDataAdapter.saveChanges();

    //setup part 2: prepare changes
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
        .getStoredEntityById(garfield.getId());

    //execution & verification
    expectRunning(loadedGarfield::delete).throwsException();
  }

  //method
  @TestCase
  @IgnoreTimeout
  public void testCase_whenReferencedEntityIsLoadedAndRemovedAndDeleted() {

    //setup part 1: initialize database
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    final var odie = new Pet();
    nodeDataAdapter.insert(garfield);
    nodeDataAdapter.insert(odie);
    final var john = new Person();
    john.pets.addEntity(garfield);
    john.pets.addEntity(odie);
    nodeDataAdapter.insert(john);
    nodeDataAdapter.saveChanges();

    //setup part 2: remove Entity from MultiReference
    final var loadedJohn = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
        .getStoredEntityById(garfield.getId());
    loadedJohn.pets.removeEntity(loadedGarfield);
    nodeDataAdapter.saveChanges();

    //setup part 3: prepare deleting Entity
    final var loadedGarfield2 = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
        .getStoredEntityById(garfield.getId());
    loadedGarfield2.delete();

    //execution & verification
    expectRunning(nodeDataAdapter::saveChanges).doesNotThrowException();
  }
}
