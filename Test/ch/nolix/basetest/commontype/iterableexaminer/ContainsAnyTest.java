/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.commontype.iterableexaminer;

import org.junit.jupiter.api.Test;

import ch.nolix.base.commontype.iterableexaminer.IterableExaminer;
import ch.nolix.base.independent.linkedlist.LinkedList;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class ContainsAnyTest extends StandardTest {
  @Test
  void testCase_containsAny_whenGivenIterableIsNull() {
    // setup
    final Iterable<Object> iterable = null;
    final var testUnit = new IterableExaminer();

    // execute
    final var result = testUnit.containsAny(iterable);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_containsAny_whenGivenIterableIsEmpty() {
    // setup
    final Iterable<Object> iterable = LinkedList.createEmpty();
    final var testUnit = new IterableExaminer();

    // execute
    final var result = testUnit.containsAny(iterable);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_containsAny_whenGivenIterableContains1Element() {
    // setup
    final Iterable<Object> iterable = LinkedList.fromArray(new String[] { "antelope" });
    final var testUnit = new IterableExaminer();

    // execute
    final var result = testUnit.containsAny(iterable);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_containsAny_whenGivenIterableContains2Elements() {
    // setup
    final Iterable<Object> iterable = LinkedList.fromArray(new String[] { "antelope", "elephant" });
    final var testUnit = new IterableExaminer();

    // execute
    final var result = testUnit.containsAny(iterable);

    // verify
    expect(result).isTrue();
  }
}
