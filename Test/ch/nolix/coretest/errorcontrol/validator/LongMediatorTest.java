/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coretest.errorcontrol.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonNegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnequalArgumentException;
import ch.nolix.core.errorcontrol.validator.LongMediator;
import ch.nolix.core.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class LongMediatorTest extends StandardTest {
  @Test
  void testCase_isBetween_whenTheGivenArgumentIsSmallerThanTheGivenMin() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", -20);

    //execution & verification
    expectRunning(() -> testUnit.isBetween(0, 100))
      .throwsException()
      .ofType(ArgumentIsOutOfRangeException.class)
      .withMessage("The given value '-20' is not in [0, 100].");
  }

  @ParameterizedTest
  @ValueSource(ints = {
  0, //The argument is the min.
  50, //The given argument is the midpoint.
  100 //The argument is the max.
  })
  void testCase_isBetween_whenTheGivenArgumentIsBetweenTheGivenMinAndMax(final int argument) {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", argument);

    //execution & verification
    expectRunning(() -> testUnit.isBetween(0, 100)).doesNotThrowException();
  }

  @Test
  void testCase_isBetween_whenTheGivenArgumentIsBiggerThanTheGivenMax() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", 120);

    //execution & verification
    expectRunning(() -> testUnit.isBetween(0, 100))
      .throwsException()
      .ofType(ArgumentIsOutOfRangeException.class)
      .withMessage("The given value '120' is not in [0, 100].");
  }

  @Test
  void testCase_isBiggerThan_whenTheGivenArgumentIsSmallerThanTheGivenValue() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", 20);

    //verification & execution
    expectRunning(() -> testUnit.isBiggerThan(100))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given value '20' is not bigger than 100.");
  }

  @Test
  void testCase_isBiggerThan_whenTheGivenArgumentEqualsTheGivenValue() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", 100);

    //verification & execution
    expectRunning(() -> testUnit.isBiggerThan(100))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given value '100' is not bigger than 100.");
  }

  @Test
  void testCase_isBiggerThan_whenTheGivenArgumentIsBiggerThanTheGivenValue() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", 120);

    //verification & execution
    expectRunning(() -> testUnit.isBiggerThan(100)).doesNotThrowException();
  }

  @Test
  void testCase_isBiggerThanOrEquals_whenTheGivenArgumentIsSmallerThanTheGivenValue() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", 20);

    //verification & execution
    expectRunning(() -> testUnit.isBiggerThanOrEquals(100))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given value '20' is not bigger than or equal to 100.");
  }

  @Test
  void testCase_isBiggerThanOrEquals_whenTheGivenArgumentEqualsTheGivenValue() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", 100);

    //verification & execution
    expectRunning(() -> testUnit.isBiggerThanOrEquals(100)).doesNotThrowException();
  }

  @Test
  void testCase_isBiggerThanOrEquals_whenTheGivenArgumentIsBiggerThanTheGivenValue() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", 120);

    //verification & execution
    expectRunning(() -> testUnit.isBiggerThanOrEquals(100)).doesNotThrowException();
  }

  @Test
  void testCase_isEqualToAny_whenTheGivenArgumentEqualsAny() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", 10);

    //execution
    expectRunning(() -> testUnit.isEqualToAny(5, 10, 15, 20)).doesNotThrowException();
  }

  @Test
  void testCase_isEqualToAny_whenTheGivenArgumentDoesNotEqualAny() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", 10);

    //execution
    expectRunning(() -> testUnit.isEqualToAny(15, 20, 25, 30))
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  @Test
  void testCase_isEqualTo_whenTheGivenArgumenIsBiggerThanTheGivenValue() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", 10);

    //execution & verification
    expectRunning(() -> testUnit.isEqualTo(9))
      .throwsException()
      .ofType(UnequalArgumentException.class);
  }

  @Test
  void testCase_isEqualTo_whenTheGivenArgumentEqualsTheGivenValue() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", 10);

    //execution & verification
    expectRunning(() -> testUnit.isEqualTo(10)).doesNotThrowException();
  }

  @ParameterizedTest
  @ValueSource(ints = { -1, -2, -9, -10, -20, -99, -100, -200, -999 })
  void testCase_isNegative_whenTheGivenArgumentIsNegative(final int argument) {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", argument);

    //execution & verification
    expectRunning(testUnit::isNegative).doesNotThrowException();
  }

  @ParameterizedTest
  @ValueSource(ints = { 0, 1, 2, 9, 10, 20, 99, 100, 200, 999 })
  void testCase_isNegative_whenTheGivenArgumentIsNotNegative(final int argument) {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", argument);

    //execution & verification
    expectRunning(testUnit::isNegative)
      .throwsException()
      .ofType(NonNegativeArgumentException.class)
      .withMessage("The given value " + StringTool.getInSingleQuotes(argument) + " is not negative.");
  }

  @Test
  void testCase_isNotNegative_whenTheGivenArgumentIsNegative() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", -1);

    //execution & verification
    expectRunning(testUnit::isNotNegative)
      .throwsException()
      .ofType(NegativeArgumentException.class)
      .withMessage("The given value '-1' is negative.");
  }

  @Test
  void testCase_isNotNegative_whenTheGivenArgumentIs0() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", 0);

    //execution & verification
    expectRunning(testUnit::isNotNegative).doesNotThrowException();
  }

  @Test
  void testCase_isNotNegative_whenTheGivenArgumentIs1() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", 1);

    //execution & verification
    expectRunning(testUnit::isNotNegative).doesNotThrowException();
  }

  @Test
  void testCase_isPositive_whenTheGivenArgumentIsMinus1() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", -1);

    //execution & verification
    expectRunning(testUnit::isPositive)
      .throwsException()
      .ofType(NonPositiveArgumentException.class)
      .withMessage("The given value '-1' is not positive.");
  }

  @Test
  void testCase_isPositive_whenTheGivenArgumentIs0() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", 0);

    //execution & verification
    expectRunning(testUnit::isPositive)
      .throwsException()
      .ofType(NonPositiveArgumentException.class)
      .withMessage("The given value '0' is not positive.");
  }

  @Test
  void testCase_isPositive_whenTheGivenArgumentIs1() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", 1);

    //execution & verification
    expectRunning(testUnit::isPositive).doesNotThrowException();
  }

  @Test
  void testCase_isSmallerThan_whenTheGivenArgumentIsSmallerThanTheGivenValue() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", 20);

    //verification & execution
    expectRunning(() -> testUnit.isSmallerThan(100)).doesNotThrowException();
  }

  @Test
  void testCase_isSmallerThan_whenTheGivenArgumentEqualsTheGivenValue() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", 100);

    //verification & execution
    expectRunning(() -> testUnit.isSmallerThan(100))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given value '100' is not smaller than 100.");
  }

  @Test
  void testCase_isSmallerThan_whenTheGivenArgumentIsBiggerThanTheGivenValue() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", 120);

    //verification & execution
    expectRunning(() -> testUnit.isSmallerThan(100))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given value '120' is not smaller than 100.");
  }

  @Test
  void testCase_isSmallerThanOrEquals_whenTheGivenArgumentIsSmallerThanTheGivenValue() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", 20);

    //verification & execution
    expectRunning(() -> testUnit.isSmallerThanOrEquals(100)).doesNotThrowException();
  }

  @Test
  void testCase_isSmallerThanOrEquals_whenTheGivenArgumentEqualsTheGivenValue() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", 100);

    //verification & execution
    expectRunning(() -> testUnit.isSmallerThanOrEquals(100)).doesNotThrowException();
  }

  @Test
  void testCase_isSmallerThanOrEquals_whenTheGivenArgumentIsBiggerThanTheGivenValue() {
    //setup
    final var testUnit = LongMediator.forArgumentNameAndArgument("value", 120);

    //verification & execution
    expectRunning(() -> testUnit.isSmallerThanOrEquals(100))
      .throwsException()
      .ofType(BiggerArgumentException.class)
      .withMessage("The given value '120' is bigger than 100.");
  }
}
