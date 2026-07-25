/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.commontype.iterableexaminer;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ch.nolix.base.commontype.iterableexaminer.IterableExaminer;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class ContainsNonNullTest extends StandardTest {
  @Test
  void testCase_containsNonNull_whenGivenIterableIsNull() {
    // setup
    final Iterable<Object> iterable = null;
    final var testUnit = new IterableExaminer();

    // execute
    final var result = testUnit.containsNonNull(iterable);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_containsNonNull_whenGivenIterableIsEmpty() {
    // setup
    final Iterable<Object> iterable = List.of();
    final var testUnit = new IterableExaminer();

    // execute
    final var result = testUnit.containsNonNull(iterable);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_containsNonNull_whenGivenIterableContainsNullElement() {
    // setup
    final ArrayList<Object> iterable = new ArrayList<>();
    final String object = null;
    iterable.add(object);
    final var testUnit = new IterableExaminer();

    // execute
    final var result = testUnit.containsNonNull(iterable);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_containsNonNull_whenGivenIterableContainsNonNullElement() {
    // setup
    final ArrayList<Object> iterable = new ArrayList<>();
    iterable.add("antelope");
    final var testUnit = new IterableExaminer();

    // execute
    final var result = testUnit.containsNonNull(iterable);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_containsNonNull_whenGivenIterableContainsNullElementAndNonNullElement() {
    // setup
    final ArrayList<Object> iterable = new ArrayList<>();
    iterable.add(null);
    iterable.add("antelope");
    final var testUnit = new IterableExaminer();

    // execute
    final var result = testUnit.containsNonNull(iterable);

    // verify
    expect(result).isTrue();
  }
}
