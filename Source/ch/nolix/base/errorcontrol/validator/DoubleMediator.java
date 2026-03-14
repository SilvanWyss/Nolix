/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.errorcontrol.validator;

import ch.nolix.base.independent.math.NumberComparator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.EqualArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NonNegativeArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.UnequalArgumentException;

/**
 * A double mediator is a mediator for a double. A double mediator is not
 * mutable.
 * 
 * @author Silvan Wyss
 */
public class DoubleMediator extends Mediator {
  private final double argument;

  /**
   * Creates a new {@link DoubleMediator} for the given argument.
   * 
   * @param argument
   */
  protected DoubleMediator(final double argument) {
    //Sets the argument of the current DoubleMediator.
    this.argument = argument;
  }

  /**
   * Creates a new {@link DoubleMediator} for the given argument, which has has
   * the given argumentName.
   * 
   * @param argumentName
   * @param argument
   * @throws RuntimeException  if the given argumentName is null.
   * @throws RuntimeException if the given argumentName is blank.
   */
  protected DoubleMediator(final String argumentName, final double argument) {
    //Calls constructor of the base class.
    super(argumentName);

    //Sets the argument of the current DoubleMediator.
    this.argument = argument;
  }

  /**
   * @param argument
   * @return a new {@link DoubleMediator} for the given argument.
   */
  public static DoubleMediator forArgument(final double argument) {
    return new DoubleMediator(argument);
  }

  /**
   * @param min
   * @param max
   * @throws RuntimeException if the argument of this double mediator
   *                                       is not between the given min and max.
   */
  public void isBetween(final double min, final double max) {
    //Asserts that the argument of this double mediator
    //is between the given min and max.
    if (argument < min || argument > max) {
      throw //
      ArgumentIsOutOfRangeException.forArgumentAndArgumentNameAndRangeWithMinAndMax(
        argument,
        getArgumentName(),
        min,
        max);
    }
  }

  /**
   * @param value
   * @throws RuntimeException if the argument of this double mediator is
   *                                  not bigger than the given value.
   */
  public void isBiggerThan(final double value) {
    //Asserts that the argument of this double mediator is bigger than the given
    //value.
    if (argument <= value) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        argument,
        getArgumentName(),
        "is not bigger than " + value);
    }
  }

  /**
   * @param value
   * @throws UnequalArgumentException if the argument of this double mediator does
   *                                  not equal the given value.
   */
  public void isEqualTo(final double value) {
    //Asserts that the argument of this double mediator equals the given value.
    if (!NumberComparator.areEqual(argument, value)) {
      throw UnequalArgumentException.forArgumentAndArgumentNameAndValue(argument, getArgumentName(), value);
    }
  }

  /**
   * @throws NonNegativeArgumentException if the argument of this double mediator
   *                                      is not negative.
   */
  public void isNegative() {
    //Asserts that the argument of this double mediator is negative.
    if (argument >= 0) {
      throw NonNegativeArgumentException.forArgumentAndArgumentName(argument, getArgumentName());
    }
  }

  /**
   * @param value
   * @throws RuntimeException if the argument of this double mediator is
   *                                 bigger than the given value.
   */
  public void isNotBiggerThan(final double value) {
    //Asserts that the argument of this named long mediator is not bigger than the
    //given value.
    if (argument > value) {
      throw BiggerArgumentException.forArgumentAndArgumentNameAndMax(argument, getArgumentName(), value);
    }
  }

  /**
   * @param value
   * @throws RuntimeException if the argument of this double mediator equals
   *                                the given value.
   */
  public void isNotEqualTo(final double value) {
    //Asserts that the argument of this double mediator does not equal the given value.
    if (NumberComparator.areEqual(argument, value)) {
      throw EqualArgumentException.forArgumentAndArgumentNameAndEqualValue(argument, getArgumentName(), value);
    }
  }

  /**
   * @throws RuntimeException if the argument of this double mediator is
   *                                   negative.
   */
  public void isNotNegative() {
    //Asserts that the argument of this double mediator is not negative.
    if (argument < 0) {
      throw NegativeArgumentException.forArgumentAndArgumentName(argument, getArgumentName());
    }
  }

  /**
   * @throws RuntimeException if the argument of this double mediator
   *                                      is positive.
   */
  public void isNotPositive() {
    //Asserts that the argument of this double mediator is not positive.
    if (argument <= 0) {
      throw NonPositiveArgumentException.forArgumentAndArgumentName(argument, getArgumentName());
    }
  }

  /**
   * @param value
   * @throws RuntimeException if the argument of this double mediator is
   *                                  smaller than the given value.
   */
  public void isNotSmallerThan(final double value) {
    //Asserts that the argument of this double mediator is not smaller than the
    //given value.
    if (argument > value) {
      throw SmallerArgumentException.forArgumentNameAndArgumentAndLimit(argument, getArgumentName(), value);
    }
  }

  /**
   * @throws RuntimeException if the argument of this double mediator
   *                                      is not positive.
   */
  public void isPositive() {
    //Asserts that the argument of this double mediator is positive.
    if (argument <= 0) {
      throw NonPositiveArgumentException.forArgumentAndArgumentName(argument, getArgumentName());
    }
  }

  /**
   * @param value
   * @throws RuntimeException if the argument of this double mediator is
   *                                  not smaller than the given value.
   */
  public void isSmallerThan(final double value) {
    //Asserts that the argument of this double mediator is smaller than the given
    //value.
    if (argument >= value) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        argument,
        getArgumentName(),
        "is not smaller than " + value);
    }
  }

  public DoubleDeviationMediator withMaxDeviation(final double maxDeviation) {
    return new DoubleDeviationMediator(getArgumentName(), getArgument(), maxDeviation);
  }

  /**
   * @return the argument of htis double mediator.
   */
  protected double getArgument() {
    return argument;
  }
}
