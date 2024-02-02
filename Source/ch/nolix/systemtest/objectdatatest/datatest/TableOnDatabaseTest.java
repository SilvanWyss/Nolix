//package declaration
package ch.nolix.systemtest.objectdatatest.datatest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdata.schema.Schema;

//class
public final class TableOnDatabaseTest extends Test {

  //constant
  private static final class Thing extends Entity {

    //constructor
    public Thing() {
      initialize();
    }
  }

  //method
  @TestCase
  public void testCase_containsEntityWithId_whenDoesNotContainEntityWithGivenId() {

    //setup part 1: Initializes database.
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Thing.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var thing = new Thing();

    //setup part 2: Gains test unit.
    final var testUnit = nodeDataAdapter.getStoredTableByEntityType(Thing.class);

    //execution
    final var result = testUnit.containsEntityWithId(thing.getId());

    //verification
    expectNot(result);
  }

  //method
  @TestCase
  public void testCase_containsEntityWithId_whenContainsEntityWithGivenId() {

    //setup part 1: Initializes database.
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Thing.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var thing = new Thing();
    nodeDataAdapter.insert(thing);
    nodeDataAdapter.saveChanges();

    //setup part 2: Gains test unit.
    final var testUnit = nodeDataAdapter.getStoredTableByEntityType(Thing.class);

    //execution
    final var result = testUnit.containsEntityWithId(thing.getId());

    //verification
    expect(result);
  }
}
