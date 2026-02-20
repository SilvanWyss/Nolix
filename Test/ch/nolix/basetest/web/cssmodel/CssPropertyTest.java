/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.web.cssmodel;

import org.junit.jupiter.api.Test;

import ch.nolix.base.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.base.web.cssmodel.CssProperty;

/**
 * @author Silvan Wyss
 */
final class CssPropertyTest extends StandardTest {
  @Test
  void testCase_toString() {
    //setup
    final var testUnit = CssProperty.withNameAndValue("width", "200px");

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("width: 200px;");
  }

  @Test
  void testCase_withNameAndValue() {
    //execution
    final var result = CssProperty.withNameAndValue("width", "200px");

    //verification
    expect(result.getName()).isEqualTo("width");
    expect(result.getValue()).isEqualTo("200px");
  }

  @Test
  void testCase_withNameAndValue_whenTheGivenNameIsNull() {
    //execution & verification
    expectRunning(() -> CssProperty.withNameAndValue(null, "200px"))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given name is null.");
  }

  @Test
  void testCase_withNameAndValue_whenTheGivenValueIsNull() {
    //setup
    final String value = null;

    //execution & verification
    expectRunning(() -> CssProperty.withNameAndValue("width", value))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given value is null.");
  }
}
