/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.foundation.linkedlist;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.foundation.linkedlist.SimpleLinkedList;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class SimpleLinkedListTest extends StandardTest {
  @Test
  void testCase_withElements() {
    // setup
    final var elements = ImmutableList.withElements("elephant", "lion", "zebra");

    // execute
    final var result = SimpleLinkedList.withElements(elements);

    // verify
    expect(result).containsExactlyInSameOrder(elements);
  }
}
