/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.errorcontrol.validator;

import org.junit.jupiter.api.Test;

import ch.nolix.base.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.base.errorcontrol.validator.MethodMediator;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class MethodMediatorTest extends StandardTest {
  @Test
  void testCase_hasReturnType_whenIsNull() {
    //setup
    final var testUnit = MethodMediator.forArgument(null);

    //execution & verification
    expectRunning(() -> testUnit.hasReturnType(String.class))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given method does not have the return type 'java.lang.String'.");
  }
}
