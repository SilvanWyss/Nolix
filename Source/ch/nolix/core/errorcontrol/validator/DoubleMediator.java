package ch.nolix.core.errorcontrol.validator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EqualArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonNegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnequalArgumentException;
import ch.nolix.core.independent.machineprecision.GlobalNumberComparator;

/**
 * A double mediator is a mediator for a double. A double mediator is not
 * mutable.
 * 
 * @author Silvan Wyss
 * @version 2017-01-01
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
   * @throws ArgumentIsNullException  if the given argumentName is null.
   * @throws InvalidArgumentException if the given argumentName is blank.
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
   * @throws ArgumentIsOutOfRangeException if the argument of this double mediator
   *                                       is not between the given min and max.
   */
  public void isBetween(final double min, final double max) {

    //Asserts that the argument of this double mediator
    //is between the given min and max.
    if (argument < min || argument > max) {
      throw ArgumentIsOutOfRangeException.forArgumentNameAndArgumentAndRangeWithMinAndMax(
        getArgumentName(),
        argument,
        min,
        max);
    }
  }

  /**
   * @param value
   * @throws InvalidArgumentException if the argument of this double mediator is
   *                                  not bigger than the given value.
   */
  public void isBiggerThan(final double value) {

    //Asserts that the argument of this double mediator is bigger than the given
    //value.
    if (argument <= value) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        argument,
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
    if (!GlobalNumberComparator.areEqual(argument, value)) {
      throw UnequalArgumentException.forArgumentNameAndArgumentAndValue(getArgumentName(), argument, value);
    }
  }

  /**
   * @throws NonNegativeArgumentException if the argument of this double mediator
   *                                      is not negative.
   */
  public void isNegative() {

    //Asserts that the argument of this double mediator is negative.
    if (argument >= 0) {
      throw NonNegativeArgumentException.forArgumentNameAndArgument(getArgumentName(), argument);
    }
  }

  /**
   * @param value
   * @throws BiggerArgumentException if the argument of this double mediator is
   *                                 bigger than the given value.
   */
  public void isNotBiggerThan(final double value) {

    //Asserts that the argument of this named long mediator is not bigger than the
    //given value.
    if (argument > value) {
      throw BiggerArgumentException.forArgumentNameAndArgumentAndMax(getArgumentName(), argument, value);
    }
  }

  /**
   * @param value
   * @throws EqualArgumentException if the argument of this double mediator equals
   *                                the given value.
   */
  public void isNotEqualTo(final double value) {

    //Asserts that the argument of this double mediator does not equal the given value.
    if (GlobalNumberComparator.areEqual(argument, value)) {
      throw EqualArgumentException.forArgumentNameAndArgumentAndEqualValue(getArgumentName(), argument, value);
    }
  }

  /**
   * @throws NegativeArgumentException if the argument of this double mediator is
   *                                   negative.
   */
  public void isNotNegative() {

    //Asserts that the argument of this double mediator is not negative.
    if (argument < 0) {
      throw NegativeArgumentException.forArgumentNameAndArgument(getArgumentName(), argument);
    }
  }

  /**
   * @throws NonPositiveArgumentException if the argument of this double mediator
   *                                      is positive.
   */
  public void isNotPositive() {

    //Asserts that the argument of this double mediator is not positive.
    if (argument <= 0) {
      throw NonPositiveArgumentException.forArgumentNameAndArgument(getArgumentName(), argument);
    }
  }

  /**
   * @param value
   * @throws SmallerArgumentException if the argument of this double mediator is
   *                                  smaller than the given value.
   */
  public void isNotSmallerThan(final double value) {

    //Asserts that the argument of this double mediator is not smaller than the
    //given value.
    if (argument > value) {
      throw SmallerArgumentException.forArgumentNameAndArgumentAndLimit(getArgumentName(), argument, value);
    }
  }

  /**
   * @throws NonPositiveArgumentException if the argument of this double mediator
   *                                      is not positive.
   */
  public void isPositive() {

    //Asserts that the argument of this double mediator is positive.
    if (argument <= 0) {
      throw NonPositiveArgumentException.forArgumentNameAndArgument(getArgumentName(), argument);
    }
  }

  /**
   * @param value
   * @throws InvalidArgumentException if the argument of this double mediator is
   *                                  not smaller than the given value.
   */
  public void isSmallerThan(final double value) {

    //Asserts that the argument of this double mediator is smaller than the given
    //value.
    if (argument >= value) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        argument,
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
