//package declaration
package ch.nolix.systemtest.objectdatatest.datatest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.data.Value;
import ch.nolix.system.objectdata.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdata.schema.Schema;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;

//class
final class ValueOnDatabaseTest extends StandardTest {

  //constant
  private static final class Pet extends Entity {

    //attribute
    final Value<String> name = Value.withValueType(String.class);

    //constructor
    Pet() {
      initialize();
    }
  }

  //method
  @Test
  void testCase_isSaved_whenIsEmpty() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = Schema.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);

    //execution & verification
    expectRunning(nodeDataAdapter::saveChanges).throwsException();
  }

  //method
  @Test
  void testCase_getStoredValue_whenContainsAnyAndIsNotSaved() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = Schema.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    garfield.name.setValue("Garfield");
    nodeDataAdapter.insertEntity(garfield);

    //execution
    final var result = garfield.name.getStoredValue();

    //verification
    expect(result).isEqualTo("Garfield");
  }

  //method
  @Test
  void testCase_getStoredValue_whenContainsAnyAndIsSaved() {

    //setup part 1
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = Schema.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    garfield.name.setValue("Garfield");
    nodeDataAdapter.insertEntity(garfield);
    nodeDataAdapter.saveChanges();

    //setup part 2
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());

    //execution
    final var result = loadedGarfield.name.getStoredValue();

    //verification
    expect(result).isEqualTo("Garfield");
  }

  //method
  @Test
  void testCase_getState_whenIsNewAndNotEdited() {

    //setup
    final var garfield = new Pet();

    //setup verification
    expect(garfield.getState()).is(DatabaseObjectState.NEW);

    //execution
    final var result = garfield.name.getState();

    //verification
    expect(result).is(DatabaseObjectState.NEW);
  }

  //method
  @Test
  void testCase_getState_whenIsNewAndEdited() {

    //setup
    final var garfield = new Pet();
    garfield.name.setValue("Garfield");

    //setup verification
    expect(garfield.getState()).is(DatabaseObjectState.NEW);

    //execution
    final var result = garfield.name.getState();

    //verification
    expect(result).is(DatabaseObjectState.NEW);
  }

  //method
  @Test
  void testCase_getState_whenIsClosed() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = Schema.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    garfield.name.setValue("Garfield");
    nodeDataAdapter.insertEntity(garfield);
    nodeDataAdapter.saveChanges();

    //setup verification
    expect(garfield.getState()).is(DatabaseObjectState.CLOSED);

    //execution
    final var result = garfield.name.getState();

    //verification
    expect(result).is(DatabaseObjectState.CLOSED);
  }

  //method
  @Test
  void testCase_getState_whenIsLoaded() {

    //setup part 1
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = Schema.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    garfield.name.setValue("Garfield");
    nodeDataAdapter.insertEntity(garfield);
    nodeDataAdapter.saveChanges();

    //setup part 2
    final var loaedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());

    //setup verification
    expect(loaedGarfield.getState()).is(DatabaseObjectState.LOADED);

    //execution
    final var result = loaedGarfield.name.getState();

    //verification
    expect(result).is(DatabaseObjectState.LOADED);
  }
}
