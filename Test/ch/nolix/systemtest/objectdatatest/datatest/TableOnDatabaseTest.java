//package declaration
package ch.nolix.systemtest.objectdatatest.datatest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdata.schema.Schema;

//class
final class TableOnDatabaseTest extends StandardTest {

  //constant
  private static final class Thing extends Entity {

    //constructor
    Thing() {
      initialize();
    }
  }

  //method
  @Test
  void testCase_containsEntityWithId_whenDoesNotContainEntityWithGivenId() {

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
  @Test
  void testCase_containsEntityWithId_whenContainsEntityWithGivenId() {

    //setup part 1: Initializes database.
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Thing.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var thing = new Thing();
    nodeDataAdapter.insertEntity(thing);
    nodeDataAdapter.saveChanges();

    //setup part 2: Gains test unit.
    final var testUnit = nodeDataAdapter.getStoredTableByEntityType(Thing.class);

    //execution
    final var result = testUnit.containsEntityWithId(thing.getId());

    //verification
    expect(result);
  }
}
