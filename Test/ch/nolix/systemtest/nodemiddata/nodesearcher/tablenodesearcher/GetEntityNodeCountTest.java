/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.nodemiddata.nodesearcher.tablenodesearcher;

import org.junit.jupiter.api.Test;

import ch.nolix.base.document.node.MutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.nodemiddata.nodesearcher.TableNodeSearcher;

/**
 * @author Silvan Wyss
 */
final class GetEntityNodeCountTest extends StandardTest {
  @Test
  void testCase_getEntityNodeCount_whenGivenTableNodeIsNull() {
    // setup
    final var testUnit = new TableNodeSearcher();

    // execute & verify
    expectRunning(() -> testUnit.getEntityNodeCount(null)).throwsException();
  }

  @Test
  void testCase_getEntityNodeCount_whenGivenTableNodeContainsSeveralEntityNodes() {
    // setup
    final var tableNode = MutableNode.fromString("Table(Name(Piece),Column(),Column(),Entity(),Entity(),Entity())");
    final var testUnit = new TableNodeSearcher();

    // execute
    final var result = testUnit.getEntityNodeCount(tableNode);

    // verify
    expect(result).isEqualTo(3);
  }
}
