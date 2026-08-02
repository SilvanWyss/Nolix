/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.commontype.iterablesearcher.iterablesearcher;

import org.junit.jupiter.api.Test;

import ch.nolix.base.commontype.iterablesearcher.IterableSearcher;
import ch.nolix.base.foundation.linkedlist.SimpleLinkedList;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class GetCountTest extends StandardTest {
  @Test
  void testCase_getCount_whenGivenIterableIsNull() {
    // setup
    final Iterable<Object> iterable = null;
    final var testUnit = new IterableSearcher();

    // execute & verify
    expectRunning(() -> testUnit.getCount(iterable)).throwsException();
  }

  @Test
  void testCase_getCount_whenGivenIterableIsEmpty() {
    // setup
    final Iterable<Object> iterable = SimpleLinkedList.createEmpty();
    final var testUnit = new IterableSearcher();

    // execute
    final var result = testUnit.getCount(iterable);

    // verify
    expect(result).isEqualTo(0);
  }

  @Test
  void testCase_getCount_whenGivenIterableContains1Element() {
    // setup
    final Iterable<Object> iterable = SimpleLinkedList.fromArray(new String[] { "antelope" });
    final var testUnit = new IterableSearcher();

    // execute
    final var result = testUnit.getCount(iterable);

    // verify
    expect(result).isEqualTo(1);
  }

  @Test
  void testCase_getCount_whenGivenIterableContains2Elements() {
    // setup
    final Iterable<Object> iterable = SimpleLinkedList.fromArray(new String[] { "antelope", "elephant" });
    final var testUnit = new IterableSearcher();

    // execute
    final var result = testUnit.getCount(iterable);

    // verify
    expect(result).isEqualTo(2);
  }
}
