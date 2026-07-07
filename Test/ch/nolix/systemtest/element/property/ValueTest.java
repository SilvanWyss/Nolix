/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.element.property;

import org.junit.jupiter.api.Test;

import ch.nolix.base.misc.function.FunctionService;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;
import ch.nolix.system.property.value.Value;

/**
 * @author Silvan Wyss
 */
final class ValueTest extends StandardTest {
  @Test
  void testCase_forInt() {
    //execution
    final var result = //
    Value.forIntWithNameAndDefaultValueAndSetter("amount", 0, FunctionService::takeObjectAndDoNothing);

    //verification
    expect(result.getName()).isEqualTo("amount");
    expect(result.containsAny()).isTrue();
  }

  @Test
  void testCase_setValue_whenTheGivenValueIsNull() {
    //setup
    final var testUnit = //
    Value.forStringWithNameAndDefaultValueAndSetter(
      "name",
      StringCatalog.EMPTY_STRING,
      FunctionService::takeObjectAndDoNothing);

    //setup verification
    expect(testUnit.containsAny()).isTrue();

    //execution & verification
    expectRunning(() -> testUnit.setValue(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given value is null.");

    //verification
    expect(testUnit.containsAny()).isTrue();
  }

  @Test
  void testCase_setValue_whenTheGivenValueIsValid() {
    //setup
    final var testUnit = //
    Value.forStringWithNameAndDefaultValueAndSetter(
      "name",
      StringCatalog.EMPTY_STRING,
      FunctionService::takeObjectAndDoNothing);

    //execution
    testUnit.setValue("Garfield");

    //verification
    expect(testUnit.containsAny()).isTrue();
    expect(testUnit.getStoredValue()).isEqualTo("Garfield");
  }
}
