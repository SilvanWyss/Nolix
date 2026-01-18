/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coretest.commontypetool.stringtool;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.core.commontypetool.stringtool.StringToolUnit;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class GetInParenthesesMethodTest extends StandardTest {
  @Test
  void testCase_getInParantheses_whenGivenObjectIsNull() {
    //setup
    final var testUnit = new StringToolUnit();

    //execution & verification
    expectRunning(() -> testUnit.getInParentheses(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given 1th object is null.");
  }

  @ParameterizedTest
  @CsvSource({
  "'', '()'", //
  "zebra, (zebra)", //
  "lorem ipsum, (lorem ipsum)"
  })
  void testCase_getInParantheses_when1StringIsGiven(final String string, final String expectedResult) {
    //setup
    final var testUnit = new StringToolUnit();

    //execution
    final var result = testUnit.getInParentheses(string);

    //verification
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_getInParantheses_when3StringsAreGiven() {
    //setup
    final var testUnit = new StringToolUnit();

    //execution
    final var result = testUnit.getInParentheses("antelope", "baboon", "elephant");

    //verification
    expect(result).isEqualTo("(antelope,baboon,elephant)");
  }

  @Test
  void testCase_getInParantheses_whenOneOfGivenStringsIsNull() {
    //setup
    final var testUnit = new StringToolUnit();

    //execution & verification
    expectRunning(() -> testUnit.getInParentheses("antelope", null, "baboon"))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given 2th object is null.");
  }

  @Test
  void testCase_getInParantheses_whenGivenVarargsIsNull() {
    //setup
    final Object[] varargs = null;
    final var testUnit = new StringToolUnit();

    //execution & verification
    expectRunning(() -> testUnit.getInParentheses("antelope", varargs))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given objects is null.");
  }
}
