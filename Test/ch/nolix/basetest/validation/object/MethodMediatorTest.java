/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.validation.object;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.base.validation.object.MethodMediator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;

/**
 * @author Silvan Wyss
 */
final class MethodMediatorTest extends StandardTest {
  @Test
  void testCase_hasReturnType_whenIsNull() {
    // setup
    final var testUnit = MethodMediator.forArgumentAndArgumentName(null, "method");

    // execute & verify
    expectRunning(() -> testUnit.hasReturnType(String.class))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given method does not have the return type 'java.lang.String'.");
  }
}
