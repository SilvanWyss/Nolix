/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.commontype.stringtool;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.base.commontype.stringtool.StringToolUnit;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class GetInParenthesesMethodTest extends StandardTest {
  @Test
  void testCase_getInParantheses_whenGivenObjectIsNull() {
    // setup
    final var testUnit = new StringToolUnit();

    // execute
    final var result = testUnit.getInParentheses(null, null, null);

    // verify
    expect(result).isEqualTo("(null,null,null)");
  }

  @ParameterizedTest
  @CsvSource({
  "'', '()'", //
  "zebra, (zebra)", //
  "lorem ipsum, (lorem ipsum)" //
  })
  void testCase_getInParantheses_when1StringIsGiven(final String string, final String expectedResult) {
    // setup
    final var testUnit = new StringToolUnit();

    // execute
    final var result = testUnit.getInParentheses(string);

    // verify
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_getInParantheses_when3StringsAreGiven() {
    // setup
    final var testUnit = new StringToolUnit();

    // execute
    final var result = testUnit.getInParentheses("antelope", "baboon", "elephant");

    // verify
    expect(result).isEqualTo("(antelope,baboon,elephant)");
  }

  @Test
  void testCase_getInParantheses_whenOneOfGivenStringsIsNull() {
    // setup
    final var testUnit = new StringToolUnit();

    // execute
    final var result = testUnit.getInParentheses("antelope", null, "elephant");

    // verify
    expect(result).isEqualTo("(antelope,null,elephant)");
  }
}
