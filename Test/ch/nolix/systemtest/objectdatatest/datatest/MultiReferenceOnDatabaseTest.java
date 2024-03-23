//package declaration
package ch.nolix.systemtest.objectdatatest.datatest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.data.MultiReference;
import ch.nolix.system.objectdata.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdata.schema.Schema;

//class
final class MultiReferenceOnDatabaseTest extends StandardTest {

  //constant
  private static final class Person extends Entity {

    //attribute
    final MultiReference<Pet> pets = MultiReference.forReferencedEntityType(Pet.class);

    //constructor
    Person() {
      initialize();
    }
  }

  //constant
  private static final class Pet extends Entity {

    //constructor
    Pet() {
      initialize();
    }
  }

  //method
  @Test
  void testCase_whenIsLoadedAndEmpty() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var john = new Person();
    nodeDataAdapter.insertEntity(john);
    nodeDataAdapter.saveChanges();

    //execution
    final var loadedJohn = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());

    //verification
    expect(loadedJohn.pets.isEmpty());
  }

  //method
  @Test
  void testCase_whenIsLoadedAndNotEmpty() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    final var odie = new Pet();
    nodeDataAdapter.insertEntity(garfield);
    nodeDataAdapter.insertEntity(odie);
    final var john = new Person();
    john.pets.addEntity(garfield);
    john.pets.addEntity(odie);
    nodeDataAdapter.insertEntity(john);
    nodeDataAdapter.saveChanges();

    //execution
    final var loadedJohn = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());

    //verification
    expect(loadedJohn.pets.getAllStoredReferencedEntities().getElementCount()).isEqualTo(2);
    expect(loadedJohn.pets.getAllStoredReferencedEntities().containsAny(p -> p.hasId(garfield.getId())));
    expect(loadedJohn.pets.getAllStoredReferencedEntities().containsAny(p -> p.hasId(odie.getId())));
  }

  //method
  @Test
  void testCase_whenReferencedEntityIsLoadedAndDeleted() {

    //setup part 1: initialize database
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    final var odie = new Pet();
    nodeDataAdapter.insertEntity(garfield);
    nodeDataAdapter.insertEntity(odie);
    final var john = new Person();
    john.pets.addEntity(garfield);
    john.pets.addEntity(odie);
    nodeDataAdapter.insertEntity(john);
    nodeDataAdapter.saveChanges();

    //setup part 2: prepare changes
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());

    //execution & verification
    expectRunning(loadedGarfield::delete).throwsException();
  }

  //method
  @Test
  void testCase_whenReferencedEntityIsLoadedAndRemovedAndDeleted() {

    //setup part 1: initialize database
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    final var odie = new Pet();
    nodeDataAdapter.insertEntity(garfield);
    nodeDataAdapter.insertEntity(odie);
    final var john = new Person();
    john.pets.addEntity(garfield);
    john.pets.addEntity(odie);
    nodeDataAdapter.insertEntity(john);
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

  //method
  @Test
  void testCase_addEntity_whenParentEntityBelongsToTableAndAddedEntityDoesNot() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var john = new Person();
    nodeDataAdapter.insertEntity(john);
    final var garfield = new Pet();

    //execution
    john.pets.addEntity(garfield);

    //verification
    expect(garfield.belongsToTable());
  }

  //method
  @Test
  void testCase_addEntity_whenParentEntityBelongsToTableAndAddedEntityDoesNot_andIsSaved() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class, Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var john = new Person();
    nodeDataAdapter.insertEntity(john);
    final var garfield = new Pet();

    //execution
    john.pets.addEntity(garfield);
    nodeDataAdapter.saveChanges();

    //verification
    final var loadedJohn = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());
    final var loadedGarfield = //
    nodeDataAdapter.getStoredTableByEntityType(Pet.class).getStoredEntityById(garfield.getId());
    expect(loadedJohn.pets.getAllStoredReferencedEntities().getStoredOne()).is(loadedGarfield);
  }
}
