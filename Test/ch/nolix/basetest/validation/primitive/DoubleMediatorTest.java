/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.validation.primitive;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.base.validation.primitive.DoubleMediator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.SmallerArgumentException;

/**
 * @author Silvan Wyss
 */
final class DoubleMediatorTest extends StandardTest {
  @ParameterizedTest
  @ValueSource(doubles = { -1000.0, -1.5, -1.0, -0.5, -0.001, 0.0, 0.001, 0.5, 1.0, 1.5, 1000.0 })
  void testCase_isBiggerThan_whenTheGivenArgumentIsBiggerThanTheChosenInfimum(final double argument) {
    // setup
    final var testUnit = DoubleMediator.forArgumentAndArgumentName(argument, "argument");

   // execute & verification
    expectRunning(() -> testUnit.isBiggerThan(-1001.0)).doesNotThrowException();
  }

  @ParameterizedTest
  @ValueSource(doubles = { -1000.0, -1.5, -1.0, -0.5, -0.001, 0.0, 0.001, 0.5, 1.0, 1.5, 1000.0 })
  void testCase_isBiggerThan_whenTheGivenArgumentIsNotBiggerThanTheChosenInfimum(final double argument) {
    // setup
    final var testUnit = DoubleMediator.forArgumentAndArgumentName(argument, "argument");

   // execute & verification
    expectRunning(() -> testUnit.isBiggerThan(1000.0))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given argument '" + argument + "' is not bigger than 1000.0.");
  }

  @ParameterizedTest
  @ValueSource(doubles = { -1000.0, -1.5, -1.0, -0.5, -0.001, 0.0, 0.001, 0.5, 1.0, 1.5, 1000.0 })
  void testCase_isBiggerThan_whenTheGivenArgumentIsNotThan(final double argument) {
    // setup
    final var testUnit = DoubleMediator.forArgumentAndArgumentName(argument, "argument");

   // execute & verification
    expectRunning(() -> testUnit.isBiggerThan(-10_000)).doesNotThrowException();
  }

  @ParameterizedTest
  @ValueSource(doubles = { -1000.0, -1.5, -1.0, -0.5, -0.001 })
  void testCase_isNotNegative_wheTheGivenArgumentIsNegative(final double argument) {
    // setup
    final var testUnit = DoubleMediator.forArgumentAndArgumentName(argument, "argument");

   // execute & verification
    expectRunning(testUnit::isNotNegative)
      .throwsException()
      .ofType(NegativeArgumentException.class)
      .withMessage("The given argument '" + argument + "' is negative.");
  }

  @ParameterizedTest
  @ValueSource(doubles = { 0.0, 0.001, 0.5, 1.0, 1.5, 1000.0 })
  void testCase_isNotNegative_whenTheGivenArgumentIsNotNegative(final double argument) {
    // setup
    final var testUnit = DoubleMediator.forArgumentAndArgumentName(argument, "argument");

   // execute & verification
    expectRunning(testUnit::isNotNegative).doesNotThrowException();
  }

  @ParameterizedTest
  @ValueSource(doubles = { -1000.0, -1.5, -1.0, -0.5, -0.001, 0.0, 0.001, 0.5, 1.0, 1.5, 1000.0 })
  void testCase_isNotSmallerThan_whenTheGivenArgumentIsSmallerThanTheChosenMin(final double argument) {
    // setup
    final var testUnit = DoubleMediator.forArgumentAndArgumentName(argument, "argument");

   // execute & verification
    expectRunning(() -> testUnit.isNotSmallerThan(1001.0))
      .throwsException()
      .ofType(SmallerArgumentException.class)
      .withMessageThatMatches("The given argument '" + argument + "' is smaller than 1001.0.");
  }

  @ParameterizedTest
  @ValueSource(doubles = { -1000.0, -1.5, -1.0, -0.5, -0.001, 0.0, 0.001, 0.5, 1.0, 1.5, 1000.0 })
  void testCase_isNotSmallerThan_whenTheGivenArgumentIsNotSmallerThanTheChosenMin(final double argument) {
    // setup
    final var testUnit = DoubleMediator.forArgumentAndArgumentName(argument, "argument");

   // execute & verification
    expectRunning(() -> testUnit.isNotSmallerThan(-1000.0)).doesNotThrowException();
  }

  @ParameterizedTest
  @ValueSource(doubles = { 0.001, 0.5, 1.0, 1.5, 1000.0 })
  void testCase_isPositive_whenTheGivenArgumentIsPositive(final double argument) {
    // setup
    final var testUnit = DoubleMediator.forArgumentAndArgumentName(argument, "argument");

   // execute & verification
    expectRunning(testUnit::isPositive).doesNotThrowException();
  }

  @ParameterizedTest
  @ValueSource(doubles = { -1000.0, -1.5, -1.0, -0.5, -0.001, 0.0 })
  void testCase_isPositive_whenTheGivenArgumentIsNotPositive(final double argument) {
    // setup
    final var testUnit = DoubleMediator.forArgumentAndArgumentName(argument, "argument");

   // execute & verification
    expectRunning(testUnit::isPositive)
      .throwsException()
      .ofType(NonPositiveArgumentException.class)
      .withMessage("The given argument '" + argument + "' is not positive.");
  }
}
