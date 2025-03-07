package ch.nolix.system.objectdata.model;

import org.junit.jupiter.api.Test;

import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.adapter.NodeDataAdapter;
import ch.nolix.system.objectdata.field.MultiValue;

final class MultiValueOnDatabaseTest extends StandardTest {

  private static final class Round extends Entity {

    final MultiValue<Integer> amounts = MultiValue.withValueType(Integer.class);

    Round() {
      initialize();
    }
  }

  @Test
  void testCase_isSaved_whenIsNewAndEmpty() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Round.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var round = new Round();
    nodeDataAdapter.insertEntity(round);
    nodeDataAdapter.saveChanges();

    //execution
    final var loadedRound = nodeDataAdapter.getStoredTableByEntityType(Round.class).getStoredEntityById(round.getId());

    //verification
    expect(loadedRound.amounts.isEmpty()).isTrue();
  }

  @Test
  void testCase_isSaved_whenIsNewAndContainsValue() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Round.class);
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
    expect(loadedValues.getCount()).isEqualTo(4);
    expect(loadedValues.containsAll(10, 20, 30, 40)).isTrue();
  }

  @Test
  void testCase_removeValue_whenIsLoadedAndContainsValue() {

    //setup part 1
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Round.class);
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
    expect(loadedValues.getCount()).isEqualTo(3);
    expect(loadedValues.containsAll(10, 20, 30)).isTrue();
  }
}
