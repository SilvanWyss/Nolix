//package declaration
package ch.nolix.systemtest.objectdatatest.datatest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.data.MultiValue;
import ch.nolix.system.objectdata.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdata.schema.Schema;

//class
final class MultiValueOnDatabaseTest extends StandardTest {

  //constant
  private static final class Round extends Entity {

    //attribute
    final MultiValue<Integer> amounts = MultiValue.withValueType(Integer.class);

    //constructor
    Round() {
      initialize();
    }
  }

  //method
  @Test
  void testCase_isSaved_whenIsNewAndEmpty() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Round.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var round = new Round();
    nodeDataAdapter.insertEntity(round);
    nodeDataAdapter.saveChanges();

    //execution
    final var loadedRound = nodeDataAdapter.getStoredTableByEntityType(Round.class).getStoredEntityById(round.getId());

    //verification
    expect(loadedRound.amounts.getAllStoredValues().isEmpty());
  }

  //method
  @Test
  void testCase_isSaved_whenIsNewAndContainsValue() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Round.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var round = new Round();
    round.amounts.addValue(10);
    round.amounts.addValue(20);
    round.amounts.addValue(30);
    round.amounts.addValue(40);
    nodeDataAdapter.insertEntity(round);
    nodeDataAdapter.saveChanges();

    //execution
    final var loadedRound = nodeDataAdapter.getStoredTableByEntityType(Round.class).getStoredEntityById(round.getId());

    //verification
    final var loadedValues = loadedRound.amounts.getAllStoredValues();
    expect(loadedValues.getElementCount()).isEqualTo(4);
    expect(loadedValues.containsAll(10, 20, 30, 40));
  }

  //method
  @Test
  void testCase_removeValue_whenIsLoadedAndContainsValue() {

    //setup part 1
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Round.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var round = new Round();
    round.amounts.addValue(10);
    round.amounts.addValue(20);
    round.amounts.addValue(30);
    round.amounts.addValue(40);
    nodeDataAdapter.insertEntity(round);
    nodeDataAdapter.saveChanges();

    //setup part 2
    final var loadedRound = nodeDataAdapter.getStoredTableByEntityType(Round.class).getStoredEntityById(round.getId());

    //execution
    loadedRound.amounts.removeValue(40);

    //verification
    final var loadedValues = loadedRound.amounts.getAllStoredValues();
    expect(loadedValues.getElementCount()).isEqualTo(3);
    expect(loadedValues.containsAll(10, 20, 30));
  }
}
