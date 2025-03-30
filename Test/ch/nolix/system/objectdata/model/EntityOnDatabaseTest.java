package ch.nolix.system.objectdata.model;

import org.junit.jupiter.api.Test;

import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.exception.ResourceWasChangedException;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.adapter.NodeDataAdapter;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;

final class EntityOnDatabaseTest extends StandardTest {

  private static final class Pet extends Entity {

    final Value<Integer> ageInYears = Value.withValueType(Integer.class);

    Pet() {
      initialize();
    }

    void setInsertAction_(final Runnable insertAction) {
      setInsertAction(insertAction);
    }
  }

  @Test
  void testCase_isInserted_whenHasInsertAction() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
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

  @Test
  void testCase_isLoaded() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
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

  @Test
  void testCase_isSaved() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    garfield.ageInYears.setValue(5);
    nodeDataAdapter.insertEntity(garfield);

    //execution
    nodeDataAdapter.saveChanges();

    //verification
    expect(garfield.isClosed()).isTrue();
  }

  @Test
  void testCase_isSaved_whenIsChangedInTheMeanwhile() {

    //setup part 1: Initializes database.
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
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
      .ofType(ResourceWasChangedException.class)
      .withMessage("The data was changed in the meanwhile.");
  }

  @Test
  void testCase_isSaved_whenIsDeletedInTheMeanwhile() {

    //setup part 1: Initializes database.
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
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
      .ofType(ResourceWasChangedException.class)
      .withMessage("The data was changed in the meanwhile.");
  }

  @Test
  void testCase_delete_whenIsLoaded() {

    //setup part 1
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
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
    expect(loadedGarfield.isDeleted()).isTrue();

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

  @Test
  void testCase_delete_whenIsClosed() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    garfield.ageInYears.setValue(5);
    nodeDataAdapter.insertEntity(garfield);
    nodeDataAdapter.saveChanges();

    //execution & verification
    expectRunning(garfield::delete).throwsException();
  }
}
