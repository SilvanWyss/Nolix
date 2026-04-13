/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.primitive;

import ch.nolix.base.independent.math.NumberComparator;
import ch.nolix.base.validation.base.AbstractMediator;
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
 * @author Silvan Wyss
 */
public abstract class AbstractDoubleMediator extends AbstractMediator {
  private final double argument;

  /**
   * Creates a new {@link AbstractDoubleMediator} for the given argument.
   * 
   * @param argument
   */
  protected AbstractDoubleMediator(final double argument) {
    this.argument = argument;
  }

  /**
   * Creates a new {@link AbstractDoubleMediator} for the given argument and
   * argumentName.
   * 
   * @param argument
   * @param argumentName
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  protected AbstractDoubleMediator(final double argument, final String argumentName) {
    super(argumentName);

    this.argument = argument;
  }

  /**
   * @param min
   * @param max
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractDoubleMediator} is not between the
   *                          given min and max.
   */
  public final void isBetween(final double min, final double max) {
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
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractDoubleMediator} is not bigger than
   *                          the given value.
   */
  public final void isBiggerThan(final double value) {
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
   * @throws UnequalArgumentException if the argument of the current
   *                                  {@link AbstractDoubleMediator} does not
   *                                  equal the given value.
   */
  public final void isEqualTo(final double value) {
    if (!NumberComparator.areEqual(argument, value)) {
      throw UnequalArgumentException.forArgumentAndArgumentNameAndValue(argument, getArgumentName(), value);
    }
  }

  /**
   * @throws NonNegativeArgumentException if the argument of the current
   *                                      {@link AbstractDoubleMediator} is not
   *                                      negative.
   */
  public final void isNegative() {
    if (argument >= 0) {
      throw NonNegativeArgumentException.forArgumentAndArgumentName(argument, getArgumentName());
    }
  }

  /**
   * @param value
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractDoubleMediator} is bigger than the
   *                          given value.
   */
  public final void isNotBiggerThan(final double value) {
    if (argument > value) {
      throw BiggerArgumentException.forArgumentAndArgumentNameAndMax(argument, getArgumentName(), value);
    }
  }

  /**
   * @param value
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractDoubleMediator} equals the given
   *                          value.
   */
  public final void isNotEqualTo(final double value) {
    if (NumberComparator.areEqual(argument, value)) {
      throw EqualArgumentException.forArgumentAndArgumentNameAndEqualValue(argument, getArgumentName(), value);
    }
  }

  /**
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractDoubleMediator} is negative.
   */
  public final void isNotNegative() {
    if (argument < 0) {
      throw NegativeArgumentException.forArgumentAndArgumentName(argument, getArgumentName());
    }
  }

  /**
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractDoubleMediator} is positive.
   */
  public final void isNotPositive() {
    if (argument <= 0) {
      throw NonPositiveArgumentException.forArgumentAndArgumentName(argument, getArgumentName());
    }
  }

  /**
   * @param min
   * @throws RuntimeException if the argument of the current
   *                          {@link DoubleMediator} is smaller than the given
   *                          min.
   */
  public final void isNotSmallerThan(final double min) {
    if (argument < min) {
      throw SmallerArgumentException.forArgumentAndArgumentNameAndMin(argument, getArgumentName(), min);
    }
  }

  /**
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractDoubleMediator} is not positive.
   */
  public final void isPositive() {
    if (argument <= 0) {
      throw NonPositiveArgumentException.forArgumentAndArgumentName(argument, getArgumentName());
    }
  }

  /**
   * @param value
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractDoubleMediator} is not smaller than
   *                          the given value.
   */
  public final void isSmallerThan(final double value) {
    if (argument >= value) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        argument,
        getArgumentName(),
        "is not smaller than " + value);
    }
  }

  /**
   * @param maxDeviation
   * @return a new {@link DoubleDeviationMediator} for the argument and
   *         argumentName of the current {@link AbstractDoubleMediator} and the
   *         given maxDeviation.
   * @throws RuntimeException if the given maxDeviation is negative.
   */
  public final DoubleDeviationMediator withMaxDeviation(final double maxDeviation) {
    return //
    DoubleDeviationMediator.forArgumentAndArgumentNameAndMaxDeviation(getArgument(), getArgumentName(), maxDeviation);
  }

  /**
   * @return the argument of htis double mediator.
   */
  protected double getArgument() {
    return argument;
  }
}
