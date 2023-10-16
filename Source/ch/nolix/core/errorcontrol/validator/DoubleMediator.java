//package declaration
package ch.nolix.core.errorcontrol.validator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EqualArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonNegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnequalArgumentException;

//class
/**
 * A double mediator is a mediator for a double. A double mediator is not
 * mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 */
public class DoubleMediator extends Mediator {

  //attribute
  private final double argument;

  //constructor
  /**
   * Creates a new double mediator for the given argument.
   * 
   * @param argument
   */
  public DoubleMediator(double argument) {

    //Sets the argument of this double mediator.
    this.argument = argument;
  }

  //constructor
  /**
   * Creates a new double mediator for the given argument with the given argument
   * name.
   * 
   * @param argumentName
   * @param argument
   * @throws ArgumentIsNullException if the given argument name is null.
   * @throws EmptyArgumentException  if the given argument name is empty.
   */
  DoubleMediator(final String argumentName, final double argument) {

    //Calls constructor of the base class.
    super(argumentName);

    //Sets the argument of this double mediator.
    this.argument = argument;
  }

  //method
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

  //method
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

  //method
  /**
   * @param value
   * @throws UnequalArgumentException if the argument of this double mediator does
   *                                  not equal the given value.
   */
  public void isEqualTo(final double value) {

    //Asserts that the argument of this double mediator equals the given value.
    if (argument != value) {
      throw UnequalArgumentException.forArgumentNameAndArgumentAndValue(getArgumentName(), argument, value);
    }
  }

  //method
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

  //method
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

  //method
  /**
   * @param value
   * @throws EqualArgumentException if the argument of this double mediator equals
   *                                the given value.
   */
  public void isNotEqualTo(final double value) {

    //Asserts that the argument of this double mediator does not equal the given
    //value.
    if (argument == value) {
      throw EqualArgumentException.forArgumentNameAndArgumentAndEqualValue(getArgumentName(), argument, value);
    }
  }

  //method
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

  //method
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

  //method
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

  //method
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

  //method
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

  //method
  public DoubleDeviationMediator withMaxDeviation(final double maxDeviation) {
    return new DoubleDeviationMediator(getArgumentName(), getArgument(), maxDeviation);
  }

  //method
  /**
   * @return the argument of htis double mediator.
   */
  protected double getArgument() {
    return argument;
  }
}
