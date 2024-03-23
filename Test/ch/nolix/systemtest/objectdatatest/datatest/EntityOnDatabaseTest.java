//package declaration
package ch.nolix.systemtest.objectdatatest.datatest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.exception.ResourceWasChangedInTheMeanwhileException;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.data.Value;
import ch.nolix.system.objectdata.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdata.schema.Schema;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;

//class
final class EntityOnDatabaseTest extends StandardTest {

  //constant
  private static final class Pet extends Entity {

    //attribute
    final Value<Integer> ageInYears = Value.withValueType(Integer.class);

    //constructor
    Pet() {
      initialize();
    }

    void setInsertAction_(final Runnable insertAction) {
      setInsertAction(insertAction);
    }
  }

  //method
  @Test
  void testCase_isInserted_whenHasInsertAction() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var testUnit = new Pet();
    testUnit.ageInYears.setValue(0);
    testUnit.setInsertAction_(() -> testUnit.ageInYears.setValue(1));

    //setup verification
    expect(testUnit.ageInYears.getStoredValue()).isEqualTo(0);

    //execution
    nodeDataAdapter.insertEntity(testUnit);

    //verification
    expect(testUnit.ageInYears.getStoredValue()).isEqualTo(1);
  }

  //method
  @Test
  void testCase_isLoaded() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("my_database").andSchema(schema);
    final var garfield = new Pet();
    garfield.ageInYears.setValue(5);
    nodeDataAdapter.insertEntity(garfield);
    nodeDataAdapter.saveChanges();

    //execution
    final var loadedGarfield = //
    nodeDataAdapter.getStoredTableByEntityType(Pet.class).getStoredEntityById(garfield.getId());

    //verification
    expect(loadedGarfield.getState()).is(DatabaseObjectState.LOADED);
    expect(loadedGarfield.getSaveStamp()).isNotEmpty();
  }

  //method
  @Test
  void testCase_isSaved() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    garfield.ageInYears.setValue(5);
    nodeDataAdapter.insertEntity(garfield);

    //execution
    nodeDataAdapter.saveChanges();

    //verification
    expect(garfield.isClosed());
  }

  //method
  @Test
  void testCase_isSaved_whenIsChangedInTheMeanwhile() {

    //setup part 1: Initializes database.
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class);
    final var nodeDataAdapterA = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfieldA = new Pet();
    garfieldA.ageInYears.setValue(5);
    nodeDataAdapterA.insertEntity(garfieldA);
    nodeDataAdapterA.saveChanges();

    //setup part 2: Prepares a change.
    final var nodeDataAdapterB = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfieldB = nodeDataAdapterB.getStoredTableByEntityType(Pet.class).getStoredEntityById(garfieldA.getId());
    garfieldB.ageInYears.setValue(6);

    //setup part 3: Makes a change.
    final var nodeDataAdapterC = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfieldC = nodeDataAdapterC.getStoredTableByEntityType(Pet.class).getStoredEntityById(garfieldA.getId());
    garfieldC.ageInYears.setValue(6);
    nodeDataAdapterC.saveChanges();

    //execution: Tries to save changes.
    expectRunning(nodeDataAdapterB::saveChanges)
      .throwsException()
      .ofType(ResourceWasChangedInTheMeanwhileException.class)
      .withMessage("The data was changed in the meanwhile.");
  }

  //method
  @Test
  void testCase_isSaved_whenIsDeletedInTheMeanwhile() {

    //setup part 1: Initializes database.
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class);
    final var nodeDataAdapterA = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfieldA = new Pet();
    garfieldA.ageInYears.setValue(5);
    nodeDataAdapterA.insertEntity(garfieldA);
    nodeDataAdapterA.saveChanges();

    //setup part 2: Prepares a change.
    final var nodeDataAdapterB = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfieldB = nodeDataAdapterB.getStoredTableByEntityType(Pet.class).getStoredEntityById(garfieldA.getId());
    garfieldB.ageInYears.setValue(6);

    //setup part 3: Deletes the Entity.
    final var nodeDataAdapterC = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfieldC = nodeDataAdapterC.getStoredTableByEntityType(Pet.class).getStoredEntityById(garfieldA.getId());
    garfieldC.delete();
    nodeDataAdapterC.saveChanges();

    //execution & verification: Tries to save changes.
    expectRunning(nodeDataAdapterB::saveChanges)
      .throwsException()
      .ofType(ResourceWasChangedInTheMeanwhileException.class)
      .withMessage("The data was changed in the meanwhile.");
  }

  //method
  @Test
  void testCase_delete_whenIsLoaded() {

    //setup part 1
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    garfield.ageInYears.setValue(5);
    nodeDataAdapter.insertEntity(garfield);
    nodeDataAdapter.saveChanges();

    //setup part 2
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());

    //execution part 1
    loadedGarfield.delete();

    //verification part 1
    expect(loadedGarfield.isDeleted());

    //execution part 2
    nodeDataAdapter.saveChanges();

    //verification part 2
    expect(loadedGarfield.isClosed());
    expect(
      nodeDataAdapter
        .getStoredTableByEntityType(Pet.class)
        .getStoredEntities()
        .containsNone(e -> e.hasId(garfield.getId())));
  }

  //method
  @Test
  void testCase_delete_whenIsClosed() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    garfield.ageInYears.setValue(5);
    nodeDataAdapter.insertEntity(garfield);
    nodeDataAdapter.saveChanges();

    //execution & verification
    expectRunning(garfield::delete).throwsException();
  }
}
