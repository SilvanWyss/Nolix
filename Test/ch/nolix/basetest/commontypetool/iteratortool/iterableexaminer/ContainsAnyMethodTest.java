/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.commontypetool.iteratortool.iterableexaminer;

import org.junit.jupiter.api.Test;

import ch.nolix.base.commontypetool.iteratortool.IterableExaminer;
import ch.nolix.base.independent.list.List;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class ContainsAnyMethodTest extends StandardTest {
  @Test
  void testCase_containsAny_whenGivenIterableIsNull() {
    //setup
    final Iterable<Object> iterable = null;
    final var testUnit = new IterableExaminer();

    //execution
    final var result = testUnit.containsAny(iterable);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_containsAny_whenGivenIterableIsEmpty() {
    //setup
    final Iterable<Object> iterable = List.createEmpty();
    final var testUnit = new IterableExaminer();

    //execution
    final var result = testUnit.containsAny(iterable);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_containsAny_whenGivenIterableContains1Element() {
    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "antelope" });
    final var testUnit = new IterableExaminer();

    //execution
    final var result = testUnit.containsAny(iterable);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_containsAny_whenGivenIterableContains2Elements() {
    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "antelope", "elephant" });
    final var testUnit = new IterableExaminer();

    //execution
    final var result = testUnit.containsAny(iterable);

    //verification
    expect(result).isTrue();
  }
}
