/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.commontype.iterableexaminer;

import org.junit.jupiter.api.Test;

import ch.nolix.base.commontype.iterableexaminer.IterableExaminer;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.base.util.VoidObject;

/**
 * @author Silvan Wyss
 */
final class ContainsTest extends StandardTest {
  @Test
  void testCase_contains_whenGivenIterableIsNull() {
    // setup
    final var object = new VoidObject();
    final Iterable<Object> iterable = null;
    final var testUnit = new IterableExaminer();

    // execute
    final var result = testUnit.contains(iterable, object);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_contains_whenGivenIterableIsEmpty() {
    // setup
    final var object = new VoidObject();
    final Iterable<Object> iterable = ImmutableList.createEmpty();
    final var testUnit = new IterableExaminer();

    // execute
    final var result = testUnit.contains(iterable, object);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_contains_whenGivenIterableContainsGivenObject() {
    // setup
    final var object = new VoidObject();
    final Iterable<Object> iterable = ImmutableList.withElement(object);
    final var testUnit = new IterableExaminer();

    // execute
    final var result = testUnit.contains(iterable, object);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_contains_whenGivenIterableContainsGivenObjectAndOtherObjects() {
    // setup
    final var object1 = new VoidObject();
    final var object2 = new VoidObject();
    final var object3 = new VoidObject();
    final var object4 = new VoidObject();
    final var object5 = new VoidObject();
    final var object6 = new VoidObject();
    final Iterable<Object> iterable = ImmutableList.withElements(object1, object2, object3, object4, object5, object6);
    final var testUnit = new IterableExaminer();

    // execute
    final var result = testUnit.contains(iterable, object4);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_contains_whenGivenIterableDoesNotContainsGivenObjectButOtherObjects() {
    // setup
    final var object1 = new VoidObject();
    final var object2 = new VoidObject();
    final var object3 = new VoidObject();
    final var object4 = new VoidObject();
    final var object5 = new VoidObject();
    final var object6 = new VoidObject();
    final Iterable<Object> iterable = ImmutableList.withElements(object1, object2, object3, object5, object6);
    final var testUnit = new IterableExaminer();

    // execute
    final var result = testUnit.contains(iterable, object4);

    // verify
    expect(result).isFalse();
  }
}
