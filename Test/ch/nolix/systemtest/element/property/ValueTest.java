/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.element.property;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.base.util.FunctionService;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;
import ch.nolix.system.element.valueproperty.ValueProperty;

/**
 * @author Silvan Wyss
 */
final class ValueTest extends StandardTest {
  @Test
  void testCase_forInt() {
    // execute
    final var result = //
    ValueProperty.forIntWithNameAndDefaultValueAndSetter("amount", 0, FunctionService::takeObjectAndDoNothing);

    // verify
    expect(result.getName()).isEqualTo("amount");
    expect(result.containsAny()).isTrue();
  }

  @Test
  void testCase_setValue_whenTheGivenValueIsNull() {
    // setup
    final var testUnit = //
    ValueProperty.forStringWithNameAndDefaultValueAndSetter(
      "name",
      StringCatalog.EMPTY_STRING,
      FunctionService::takeObjectAndDoNothing);

    // setup verification
    expect(testUnit.containsAny()).isTrue();

    // execute & verify
    expectRunning(() -> testUnit.setValue(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given value is null.");

    // verify
    expect(testUnit.containsAny()).isTrue();
  }

  @Test
  void testCase_setValue_whenTheGivenValueIsValid() {
    // setup
    final var testUnit = //
    ValueProperty.forStringWithNameAndDefaultValueAndSetter(
      "name",
      StringCatalog.EMPTY_STRING,
      FunctionService::takeObjectAndDoNothing);

    // execute
    testUnit.setValue("Garfield");

    // verify
    expect(testUnit.containsAny()).isTrue();
    expect(testUnit.getStoredValue()).isEqualTo("Garfield");
  }
}
