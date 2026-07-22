/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.datastructure.pair;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datastructure.pair.KeyValuePair;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;

/**
 * @author Silvan Wyss
 */
final class KeyValuePairTest extends StandardTest {
  @Test
  void testCase_toString() {
    // test parameters
    final var key = "France";
    final var value = "Paris";

   // execute
    final var result = KeyValuePair.withKeyAndValue(key, value);

   // verify
    expect(result).hasStringRepresentation("(France;Paris)");
  }

  @Test
  void testCase_withKeyAndValue() {
    // test parameters
    final var key = "France";
    final var value = "Paris";

   // execute
    final var result = KeyValuePair.withKeyAndValue(key, value);

   // verify
    expect(result.getKey()).is(key);
    expect(result.getStoredValue()).is(value);
  }

  @Test
  void testCase_withKeyAndValue_whenTheGivenKeyIsNull() {
    // test parameters
    final String key = null;
    final String value = "Paris";

   // execute & verification
    expectRunning(() -> KeyValuePair.withKeyAndValue(key, value))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given key is null.");
  }

  @Test
  void testCase_withKeyAndValue_whenTheGivenValueIsNull() {
    // test parameters
    final String key = "France";
    final String value = null;

   // execute & verification
    expectRunning(() -> KeyValuePair.withKeyAndValue(key, value))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given value is null.");
  }
}
