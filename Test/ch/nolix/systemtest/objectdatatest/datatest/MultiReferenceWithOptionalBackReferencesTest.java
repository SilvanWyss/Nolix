//package declaration
package ch.nolix.systemtest.objectdatatest.datatest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.data.MultiReference;
import ch.nolix.system.objectdata.data.OptionalBackReference;
import ch.nolix.system.objectdata.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdata.schema.Schema;

//class
final class MultiReferenceWithOptionalBackReferencesTest extends StandardTest {

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

    //attribute
    final OptionalBackReference<Person> owner = OptionalBackReference
      .forEntityAndBackReferencedPropertyName(Person.class, "pets");

    //constructor
    Pet() {
      initialize();
    }
  }

  //method
  @Test
  void testCase_isSaved_whenContainsSeveral() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);
    final var simba = new Pet();
    nodeDataAdapter.insertEntity(simba);
    final var odie = new Pet();
    nodeDataAdapter.insertEntity(odie);
    final var john = new Person();
    john.pets.addEntity(garfield);
    john.pets.addEntity(simba);
    john.pets.addEntity(odie);
    nodeDataAdapter.insertEntity(john);
    nodeDataAdapter.saveChanges();

    //execution
    final var loadedJohn = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());

    //verification
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());
    final var loadedSimba = nodeDataAdapter.getStoredTableByEntityType(Pet.class).getStoredEntityById(simba.getId());
    final var loadedOdie = nodeDataAdapter.getStoredTableByEntityType(Pet.class).getStoredEntityById(odie.getId());
    expect(loadedJohn.pets.getAllStoredReferencedEntities())
      .containsExactlyInSameOrder(loadedGarfield, loadedSimba, loadedOdie);
    expect(loadedGarfield.owner.getBackReferencedEntity()).is(loadedJohn);
    expect(loadedSimba.owner.getBackReferencedEntity()).is(loadedJohn);
    expect(loadedOdie.owner.getBackReferencedEntity()).is(loadedJohn);
  }

  //TODO: Enable DataAdapter to remove relations and delete Entitis in 1 time.
  //method
  //@Test
  void testCase_removeEntity_whenContainsEntity() {

    //setup part 1
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Person.class, Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);
    final var simba = new Pet();
    nodeDataAdapter.insertEntity(simba);
    final var odie = new Pet();
    nodeDataAdapter.insertEntity(odie);
    final var john = new Person();
    john.pets.addEntity(garfield);
    john.pets.addEntity(simba);
    john.pets.addEntity(odie);
    nodeDataAdapter.insertEntity(john);
    nodeDataAdapter.saveChanges();

    //setup part 2
    final var loadedJohn1 = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());
    final var loadedOdie1 = nodeDataAdapter.getStoredTableByEntityType(Pet.class).getStoredEntityById(odie.getId());

    //execution
    loadedJohn1.pets.removeEntity(loadedOdie1);
    loadedOdie1.delete();
    nodeDataAdapter.saveChanges();

    //verification part 1
    expectNot(loadedJohn1.pets.referencesEntity(loadedOdie1));

    //verification part 2
    final var loadedJohn2 = nodeDataAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(john.getId());
    final var loadedGarfield2 = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());
    final var loadedSimba2 = nodeDataAdapter.getStoredTableByEntityType(Pet.class).getStoredEntityById(simba.getId());
    expect(loadedJohn2.pets.getAllStoredReferencedEntities()).containsExactlyInSameOrder(loadedGarfield2, loadedSimba2);
  }
}