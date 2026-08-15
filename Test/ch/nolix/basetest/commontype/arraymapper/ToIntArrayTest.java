/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.commontype.arraymapper;

import org.junit.jupiter.api.Test;

import ch.nolix.base.commontype.arraymapper.ArrayMapper;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.foundation.linkedlist.SimpleLinkedList;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.UnequalArgumentException;

/**
 * @author Silvan Wyss
 */
final class ToIntArrayTest extends StandardTest {
  @Test
  void testCase_toIntArray_whenGivenIterableIsNullAndGivenNIs0() {
    // setup
    final Iterable<String> iterable = null;
    final var testUnit = new ArrayMapper();

    // execute
    final var result = testUnit.toIntArray(iterable, 0, String::length);

    // verify
    expect(result.length).isEqualTo(0);
  }

  @Test
  void testCase_toIntArray_whenGivenIterableIsNullAndGivenNIs10() {
    // setup
    final Iterable<String> iterable = null;
    final var testUnit = new ArrayMapper();

    // execute & verify
    expectRunning(() -> testUnit.toIntArray(iterable, 10, String::length))
      .throwsException()
      .ofType(UnequalArgumentException.class);
  }

  @Test
  void testCase_toIntArray_whenGivenIntMapperIsNull() {
    // setup
    final Iterable<String> iterable = ImmutableList.createEmpty();
    final var testUnit = new ArrayMapper();

    // execute & verify
    expectRunning(() -> testUnit.toIntArray(iterable, 0, null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessageThatMatches("The given int mapper is null.");
  }

  @Test
  void testCase_toIntArray_whenGivenIterableContainsNonNullAndNullElements() {
    // setup
    final Iterable<String> iterable = SimpleLinkedList.withElements("x", "xx", "xxx", null, null, null);
    final var testUnit = new ArrayMapper();

    // execute
    final var result = testUnit.toIntArray(iterable, 6, String::length);

    // verify
    expect(result.length).isEqualTo(6);
    expect(result[0]).isEqualTo(1);
    expect(result[1]).isEqualTo(2);
    expect(result[2]).isEqualTo(3);
    expect(result[3]).isEqualTo(0);
    expect(result[4]).isEqualTo(0);
    expect(result[5]).isEqualTo(0);
  }
}
