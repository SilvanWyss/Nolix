/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.commontype.iterableexaminer;

import org.junit.jupiter.api.Test;

import ch.nolix.base.commontype.iterableexaminer.IterableExaminer;
import ch.nolix.base.independent.list.List;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class IsEmptyTest extends StandardTest {
  @Test
  void testCase_isEmpty_whenGivenIterableIsNull() {
    // setup
    final Iterable<Object> iterable = null;
    final var testUnit = new IterableExaminer();

    // execute
    final var result = testUnit.isEmpty(iterable);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isEmpty_whenGivenIterableIsEmpty() {
    // setup
    final Iterable<Object> iterable = List.createEmpty();
    final var testUnit = new IterableExaminer();

    // execute
    final var result = testUnit.isEmpty(iterable);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isEmpty_whenGivenIterableContains1Element() {
    // setup
    final Iterable<Object> iterable = List.fromArray(new String[] { "antelope" });
    final var testUnit = new IterableExaminer();

    // execute
    final var result = testUnit.isEmpty(iterable);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isEmpty_whenGivenIterableContains2Elements() {
    // setup
    final Iterable<Object> iterable = List.fromArray(new String[] { "antelope", "elephant" });
    final var testUnit = new IterableExaminer();

    // execute
    final var result = testUnit.isEmpty(iterable);

    // verify
    expect(result).isFalse();
  }
}
