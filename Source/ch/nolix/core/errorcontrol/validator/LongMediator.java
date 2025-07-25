package ch.nolix.core.errorcontrol.validator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsInRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EqualArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidPortException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonNegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.PositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnequalArgumentException;
import ch.nolix.core.independent.arraytool.ArrayTool;
import ch.nolix.coreapi.net.netconstant.PortCatalog;

/**
 * A {@link LongMediator} is a {@link Mediator} for a long argument. A
 * {@link LongMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2017-01-01
 */
public class LongMediator extends Mediator {

  private static final ArrayTool ARRAY_TOOL = new ArrayTool();

  private final long argument;

  /**
   * Creates a new {@link LongMediator} for the given argument.
   * 
   * @param argument
   */
  protected LongMediator(final long argument) {
    this.argument = argument;
  }

  /**
   * Creates a new {@link LongMediator} for the given argumentName and argument.
   * 
   * @param argumentName
   * @param argument
   * @throws ArgumentIsNullException  if the given argumentName is null.
   * @throws InvalidArgumentException if the given argumentName is blank.
   */
  private LongMediator(final String argumentName, final long argument) {

    super(argumentName);

    this.argument = argument;
  }

  /**
   * @param argument
   * @return a new {@link LongMediator} for the given argument.
   */
  public static LongMediator forArgument(final long argument) {
    return new LongMediator(argument);
  }

  /**
   * @param argumentName
   * @param argument
   * @return a new {@link LongMediator} for the given argumentName and argument.
   * @throws ArgumentIsNullException  if the given argumentName is null.
   * @throws InvalidArgumentException if the given argumentName is blank.
   */
  public static LongMediator forArgumentNameAndArgument(final String argumentName, final long argument) {
    return new LongMediator(argumentName, argument);
  }

  /**
   * @param min
   * @param max
   * @throws ArgumentIsOutOfRangeException if the argument of the current
   *                                       {@link LongMediator} is not between the
   *                                       given min and max.
   */
  public final void isBetween(final int min, final int max) {
    isBetween((long) min, (long) max);
  }

  /**
   * @param min
   * @param max
   * @throws ArgumentIsOutOfRangeException if the argument of the current
   *                                       {@link LongMediator} is not between the
   *                                       given min and max.
   */
  public final void isBetween(final long min, final long max) {
    if (argument < min || argument > max) {
      throw ArgumentIsOutOfRangeException.forArgumentAndArgumentNameAndRangeWithMinAndMax(
        argument,
        getArgumentName(),
        min,
        max);
    }
  }

  /**
   * @param value
   * @throws InvalidArgumentException if the argument of the current
   *                                  {@link LongMediator} is not bigger than the
   *                                  given value.
   */
  public final void isBiggerThan(final long value) {
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
   * @throws SmallerArgumentException if the argument of the current
   *                                  {@link LongMediator} is not bigger than or
   *                                  does not equal the given value.
   */
  public final void isBiggerThanOrEquals(final long value) {
    if (argument < value) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        argument,
        getArgumentName(),
        "is not bigger than or equal to " + value);
    }
  }

  /**
   * @param value
   * @throws UnequalArgumentException if the argument of the current
   *                                  {@link LongMediator} does not equal the
   *                                  given value.
   */
  public final void isEqualTo(final int value) {
    isEqualTo((long) value);
  }

  /**
   * @param value
   * @throws UnequalArgumentException if the argument of the current
   *                                  {@link LongMediator} does not equal the
   *                                  given value.
   */
  public final void isEqualTo(final long value) {
    if (argument != value) {
      throw UnequalArgumentException.forArgumentAndArgumentNameAndValue(argument, getArgumentName(), value);
    }
  }

  /**
   * @param value
   * @param values
   * @throws InvalidArgumentException if the argument of the current
   *                                  {@link LongMediator} does not equal one of
   *                                  the given values.
   */
  public final void isEqualToAny(final long value, final long... values) {

    if (argument == value) {
      return;
    }

    for (final long v : values) {
      if (argument == v) {
        return;
      }
    }

    throw //
    InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
      argument,
      getArgumentName(),
      "does not equal one of {" + ARRAY_TOOL.createString(values) + "}");
  }

  /**
   * @throws InvalidArgumentException if the argument of the current
   *                                  {@link LongMediator} is not negative.
   */
  public final void isNegative() {
    if (argument >= 0) {
      throw NonNegativeArgumentException.forArgumentAndArgumentName(argument, getArgumentName());
    }
  }

  /**
   * @param min
   * @param max
   * @throws InvalidArgumentException if the argument of the current
   *                                  {@link LongMediator} is between the given
   *                                  min and max.
   */
  public final void isNotBetween(final long min, final long max) {
    if (argument >= min && argument <= max) {
      throw ArgumentIsInRangeException.forArgumentNameAndArgumentAndRangeWithMinAndMax(
        getArgumentName(),
        argument,
        min,
        max);
    }
  }

  /**
   * @param value
   * @throws EqualArgumentException if the argument of the current
   *                                {@link LongMediator} equals the given value.
   */
  public final void isNotEqualTo(final long value) {
    if (argument == value) {
      throw EqualArgumentException.forArgumentAndArgumentNameAndEqualValue(argument, getArgumentName(), value);
    }
  }

  /**
   * @param value
   * @throws BiggerArgumentException if the argument of the current
   *                                 {@link LongMediator} is bigger than the given
   *                                 value.
   */
  public final void isNotBiggerThan(final int value) {
    isNotBiggerThan((long) value);
  }

  /**
   * @param value
   * @throws BiggerArgumentException if the argument of the current
   *                                 {@link LongMediator} is bigger than the given
   *                                 value.
   */
  public final void isNotBiggerThan(final long value) {
    if (argument > value) {
      throw BiggerArgumentException.forArgumentAndArgumentNameAndMax(argument, getArgumentName(), value);
    }
  }

  /**
   * @throws NegativeArgumentException if the argument of htis
   *                                   {@link LongMediator} is negative.
   */
  public final void isNotNegative() {
    if (argument < 0.0) {
      throw NegativeArgumentException.forArgumentAndArgumentName(argument, getArgumentName());
    }
  }

  /**
   * @throws PositiveArgumentException if the argument of the current
   *                                   {@link LongMediator} is positive.
   */
  public final void isNotPositive() {
    if (argument > 0) {
      throw PositiveArgumentException.forArgumentAndArgumentName(argument, getArgumentName());
    }
  }

  /**
   * @param min
   * @throws SmallerArgumentException if the argument of the current
   *                                  {@link LongMediator} is smaller than the *
   *                                  given min.
   */
  public final void isNotSmallerThan(final int min) {
    if (getArgument() < min) {
      throw SmallerArgumentException.forArgumentNameAndArgumentAndLimit(getArgument(), getArgumentName(), min);
    }
  }

  /**
   * @throws InvalidPortException if the argument of the current
   *                              {@link LongMediator} is not a port.
   */
  public final void isPort() {
    if (argument < PortCatalog.MIN_PORT || argument > PortCatalog.MAX_PORT) {
      throw InvalidPortException.forPort(argument);
    }
  }

  /**
   * @throws NonPositiveArgumentException if the argument of the current
   *                                      {@link LongMediator} is not positive.
   */
  public final void isPositive() {
    if (argument < 1) {
      throw NonPositiveArgumentException.forArgumentAndArgumentName(argument, getArgumentName());
    }
  }

  /**
   * @param value
   * @throws InvalidArgumentException if the argument of the current
   *                                  {@link LongMediator} is not smaller than the
   *                                  given value.
   */
  public final void isSmallerThan(final long value) {
    if (argument >= value) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        argument,
        getArgumentName(),
        "is not smaller than " + value);
    }
  }

  /**
   * @param value
   * @throws BiggerArgumentException if the argument of the current
   *                                 {@link LongMediator} is not smaller than or
   *                                 does not equal the given value.
   */
  public final void isSmallerThanOrEquals(final long value) {
    if (argument > value) {
      throw BiggerArgumentException.forArgumentAndArgumentNameAndMax(argument, getArgumentName(), value);
    }
  }

  /**
   * @return the argument of the current {@link LongMediator}.
   */
  protected final long getArgument() {
    return argument;
  }
}
