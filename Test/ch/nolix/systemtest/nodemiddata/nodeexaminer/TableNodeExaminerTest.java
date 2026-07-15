/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.nodemiddata.nodeexaminer;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.document.node.MutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.nodemiddata.nodeexaminer.TableNodeExaminer;

/**
 * @author Silvan Wyss
 */
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
    // setup of tableNode
    final var tableNode = //
    MutableNode
      .createEmpty()
      .addChildNodes(
        ImmutableNode.withHeaderAndChildNodes(
          "Entity",
          ImmutableNode.withHeader("id1"),
          ImmutableNode.withHeader("save_stamp"),
          ImmutableNode.withHeader("Donald"),
          ImmutableNode.withHeader("Duck")),
        ImmutableNode.withHeaderAndChildNodes(
          "Entity",
          ImmutableNode.withHeader("id2"),
          ImmutableNode.withHeader("save_stamp"),
          ImmutableNode.withHeader("Daisy"),
          ImmutableNode.withHeader("Duck")),
        ImmutableNode.withHeaderAndChildNodes(
          "Entity",
          ImmutableNode.withHeader("id3"),
          ImmutableNode.withHeader("save_stamp"),
          ImmutableNode.withHeader("Dagobert"),
          ImmutableNode.withHeader("Duck")));

    // setup of testUnit
    final var testUnit = new TableNodeExaminer();

    // execution
    final var result = //
    testUnit.tableNodeContainsEntityNodeWithFieldAtGivenOneBasedIndexWithGivenValueIgnoringGivenEntities(
      tableNode,
      oneBasedColumnIndex,
      value,
      ImmutableList.withElements(ingoredEntityId));

    // verification
    expect(Boolean.valueOf(result)).isEqualTo(expectedResult);
  }
}
