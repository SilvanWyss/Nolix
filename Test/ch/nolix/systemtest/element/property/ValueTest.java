/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.element.property;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.misc.function.FunctionService;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.element.property.Value;

/**
 * @author Silvan Wyss
 */
final class ValueTest extends StandardTest {
  @Test
  void testCase_forInt() {
    //execution
    final var result = Value.forInt("amount", FunctionService::takeObjectAndDoNothing);

    //verification
    expect(result.getName()).isEqualTo("amount");
    expect(result.isEmpty()).isTrue();
  }

  @Test
  void testCase_getSpecification() {
    //setup
    final var testUnit = Value.forInt("amount", FunctionService::takeObjectAndDoNothing);
    testUnit.setValue(500);

    //execution
    final var result = testUnit.getSpecification();

    //verification
    expect(result).hasStringRepresentation("amount(500)");
  }

  @Test
  void testCase_setValue_whenTheGivenValueIsNull() {
    //setup
    final var testUnit = Value.forString("name", FunctionService::takeObjectAndDoNothing);

    //setup verification
    expect(testUnit.isEmpty()).isTrue();

    //execution & verification
    expectRunning(() -> testUnit.setValue(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given value is null.");

    //verification
    expect(testUnit.isEmpty()).isTrue();
  }

  @Test
  void testCase_setValue_whenTheGivenValueIsValid() {
    //setup
    final var testUnit = Value.forString("name", FunctionService::takeObjectAndDoNothing);

    //execution
    testUnit.setValue("Garfield");

    //verification
    expect(testUnit.containsAny()).isTrue();
    expect(testUnit.getValue()).isEqualTo("Garfield");
  }
}
