package ch.nolix.coretest.container.pair;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.pair.KeyValuePair;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.testing.standardtest.StandardTest;

final class KeyValuePairTest extends StandardTest {

  @Test
  void testCase_toString() {

    //test parameters
    final var key = "France";
    final var value = "Paris";

    //execution
    final var result = KeyValuePair.withKeyAndValue(key, value);

    //verification
    expect(result).hasStringRepresentation("(France;Paris)");
  }

  @Test
  void testCase_withKeyAndValue() {

    //test parameters
    final var key = "France";
    final var value = "Paris";

    //execution
    final var result = KeyValuePair.withKeyAndValue(key, value);

    //verification
    expect(result.getKey()).is(key);
    expect(result.getStoredValue()).is(value);
  }

  @Test
  void testCase_withKeyAndValue_whenTheGivenKeyIsNull() {

    //test parameters
    final String key = null;
    final String value = "Paris";

    //execution & verification
    expectRunning(() -> KeyValuePair.withKeyAndValue(key, value))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given key is null.");
  }

  @Test
  void testCase_withKeyAndValue_whenTheGivenValueIsNull() {

    //test parameters
    final String key = "France";
    final String value = null;

    //execution & verification
    expectRunning(() -> KeyValuePair.withKeyAndValue(key, value))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given value is null.");
  }
}
