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
final class ToByteArrayTest extends StandardTest {
  @Test
  void testCase_toByteArray_whenGivenIterableIsNullAndGivenNIs0() {
    // setup
    final Iterable<String> iterable = null;
    final var testUnit = new ArrayMapper();

    // execute
    final var result = testUnit.toByteArray(iterable, 0, e -> (byte) e.length());

    // verify
    expect(result.length).isEqualTo(0);
  }

  @Test
  void testCase_toByteArray_whenGivenIterableIsNullAndGivenNIs10() {
    // setup
    final Iterable<String> iterable = null;
    final var testUnit = new ArrayMapper();

    // execute & verify
    expectRunning(() -> testUnit.toByteArray(iterable, 10, e -> (byte) e.length()))
      .throwsException()
      .ofType(UnequalArgumentException.class);
  }

  @Test
  void testCase_toByteArray_whenGivenByteMapperIsNull() {
    // setup
    final Iterable<String> iterable = ImmutableList.createEmpty();
    final var testUnit = new ArrayMapper();

    // execute & verify
    expectRunning(() -> testUnit.toByteArray(iterable, 0, null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessageThatMatches("The given byte mapper is null.");
  }

  @Test
  void testCase_toByteArray_whenGivenIterableContainsNonNullAndNullElements() {
    // setup
    final Iterable<String> iterable = SimpleLinkedList.withElements("x", "xx", "xxx", null, null, null);
    final var testUnit = new ArrayMapper();

    // execute
    final var result = testUnit.toByteArray(iterable, 6, e -> (byte) e.length());

    // verify
    expect(result.length).isEqualTo(6);
    expect(result[0]).isEqualTo((byte) 1);
    expect(result[1]).isEqualTo((byte) 2);
    expect(result[2]).isEqualTo((byte) 3);
    expect(result[3]).isEqualTo(0);
    expect(result[4]).isEqualTo(0);
    expect(result[5]).isEqualTo(0);
  }
}
