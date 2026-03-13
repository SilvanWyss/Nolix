/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.errorcontrol.validator;

import org.junit.jupiter.api.Test;

import ch.nolix.base.errorcontrol.validator.MultiDoubleMediator;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NonPositiveArgumentException;

/**
 * @author Silvan Wyss
 */
final class MultiDoubleMediatorTest extends StandardTest {
  @Test
  void testCase_arePositive_whenTheGivenArgumentsAreAllPositive() {
    //setup
    final var arguments = new double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
    final var testUnit = new MultiDoubleMediator(arguments);

    //execution
    expectRunning(testUnit::arePositive).doesNotThrowException();
  }

  @Test
  void testCase_arePositive_whenOneOfTheGivenArgumentsIs0() {
    //setup
    final var arguments = new double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 0.0 };
    final var testUnit = new MultiDoubleMediator(arguments);

    //execution
    expectRunning(testUnit::arePositive)
      .throwsException()
      .ofType(NonPositiveArgumentException.class)
      .withMessage("The given 6th argument '0.0' is not positive.");
  }

  @Test
  void testCase_arePositive_whenOneOfTheGivenArgumentsIsNegative() {
    //setup
    final var arguments = new double[] { 1.0, 2.0, 3.0, 4.0, 5.0, -1.0 };
    final var testUnit = new MultiDoubleMediator(arguments);

    //execution
    expectRunning(testUnit::arePositive)
      .throwsException()
      .ofType(NonPositiveArgumentException.class)
      .withMessage("The given 6th argument '-1.0' is not positive.");
  }
}
