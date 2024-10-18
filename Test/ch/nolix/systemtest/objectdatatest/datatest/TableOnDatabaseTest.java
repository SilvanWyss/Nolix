package ch.nolix.systemtest.objectdatatest.datatest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdata.schema.Schema;

final class TableOnDatabaseTest extends StandardTest {

  private static final class Thing extends Entity {

    Thing() {
      initialize();
    }
  }

  @Test
  void testCase_containsEntityWithId_whenDoesNotContainEntityWithGivenId() {

    //setup part 1: Initializes database.
    final var nodeDatabase = MutableNode.createEmpty();
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

  @Test
  void testCase_containsEntityWithId_whenContainsEntityWithGivenId() {

    //setup part 1: Initializes database.
    final var nodeDatabase = MutableNode.createEmpty();
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
