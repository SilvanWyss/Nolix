/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.validation.primitive;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.base.validation.primitive.NamableDoubleMediator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NonPositiveArgumentException;

/**
 * @author Silvan Wyss
 */
final class DoubleMediatorTest extends StandardTest {
  @ParameterizedTest
  @ValueSource(doubles = { 0.0, 0.0001, 0.5, 1.0, 1.5, 1000.0 })
  void testCase_isNotNegative_whenGivenArgumentIsNotNegative(final double argument) {
    //setup
    final var testUnit = NamableDoubleMediator.forArgument(argument);

    //execution & verification
    expectRunning(testUnit::isNotNegative).doesNotThrowException();
  }

  @ParameterizedTest
  @ValueSource(doubles = { -0.0001, -0.5, -1.0, -1.5, -1000.0 })
  void testCase_isNotNegative_whenGivenArgumentIsNegative(final double argument) {
    //setup
    final var testUnit = NamableDoubleMediator.forArgument(argument);

    //execution & verification
    expectRunning(testUnit::isNotNegative)
      .throwsException()
      .ofType(NegativeArgumentException.class)
      .withMessage("The given argument '" + argument + "' is negative.");
  }

  @ParameterizedTest
  @ValueSource(doubles = { 0.0001, 0.5, 1.0, 1.5, 1000.0 })
  void testCase_isPositive_whenGivenArgumentIsPositive(final double argument) {
    //setup
    final var testUnit = NamableDoubleMediator.forArgument(argument);

    //execution & verification
    expectRunning(testUnit::isPositive).doesNotThrowException();
  }

  @ParameterizedTest
  @ValueSource(doubles = { 0.0, -0.0001, -0.5, -1.0, -1.5, -1000.0 })
  void testCase_isPositive_whenGivenArgumentIsNotPositive(final double argument) {
    //setup
    final var testUnit = NamableDoubleMediator.forArgument(argument);

    //execution & verification
    expectRunning(testUnit::isPositive)
      .throwsException()
      .ofType(NonPositiveArgumentException.class)
      .withMessage("The given argument '" + argument + "' is not positive.");
  }
}
