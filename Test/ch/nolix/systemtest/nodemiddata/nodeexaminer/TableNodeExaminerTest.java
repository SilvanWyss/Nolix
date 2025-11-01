package ch.nolix.systemtest.nodemiddata.nodeexaminer;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.nodemiddata.nodeexaminer.TableNodeExaminer;

final class TableNodeExaminerTest extends StandardTest {
  @ParameterizedTest
  @CsvSource({
  "3, Donald, id1, false",
  "3, Donald, id2, true",
  "3, Donald, id3, true",
  "3, Donald, id4, true",
  "3, Daisy, id1, true",
  "3, Daisy, id2, false",
  "3, Daisy, id3, true",
  "3, Daisy, id4, true",
  "4, Duck, id1, true",
  "4, Duck, id2, true",
  "4, Duck, id3, true"
  })
  void testCase_tableNodeContainsEntityNodeWithFieldAtGivenOneBasedIndexWithGivenValueIgnoringGivenEntities(
    final int oneBasedColumnIndex,
    final String value,
    final String ingoredEntityId,
    final boolean expectedResult) {
    //setup of tableNode
    final var tableNode = //
    MutableNode
      .createEmpty()
      .addChildNode(
        Node.withHeaderAndChildNode("Entity",
          Node.withHeader("id1"),
          Node.withHeader("save_stamp"),
          Node.withHeader("Donald"),
          Node.withHeader("Duck")),
        Node.withHeaderAndChildNode("Entity",
          Node.withHeader("id2"),
          Node.withHeader("save_stamp"),
          Node.withHeader("Daisy"),
          Node.withHeader("Duck")),
        Node.withHeaderAndChildNode("Entity",
          Node.withHeader("id3"),
          Node.withHeader("save_stamp"),
          Node.withHeader("Dagobert"),
          Node.withHeader("Duck")));

    //setup of testUnit
    final var testUnit = new TableNodeExaminer();

    //execution
    final var result = //
    testUnit.tableNodeContainsEntityNodeWithFieldAtGivenOneBasedIndexWithGivenValueIgnoringGivenEntities(
      tableNode,
      oneBasedColumnIndex,
      value,
      ImmutableList.withElements(ingoredEntityId));

    //verification
    expect(Boolean.valueOf(result)).isEqualTo(expectedResult);
  }
}
