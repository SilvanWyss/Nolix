/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.primitive;

import ch.nolix.base.independent.arraytool.ArrayTool;
import ch.nolix.base.validation.base.AbstractMediator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsInRangeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.EqualArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidPortException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NonNegativeArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.PositiveArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.UnequalArgumentException;
import ch.nolix.baseapi.independent.arraytool.IArrayTool;
import ch.nolix.baseapi.misc.variable.PluralLowerCaseVariableCatalog;
import ch.nolix.baseapi.net.netconstant.PortCatalog;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractLongMediator extends AbstractMediator {
  private static final IArrayTool ARRAY_TOOL = new ArrayTool();

  private final long argument;

  /**
   * Creates a new {@link AbstractLongMediator} for the given argument.
   * 
   * @param argument
   */
  protected AbstractLongMediator(final long argument) {
    this.argument = argument;
  }

  /**
   * Creates a new {@link AbstractLongMediator} for the given argumentName and
   * argument.
   * 
   * @param argumentName
   * @param argument
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  protected AbstractLongMediator(final String argumentName, final long argument) {
    super(argumentName);

    this.argument = argument;
  }

  /**
   * @param min
   * @param max
   * @throws RuntimeException if the argument of the current {@link LongMediator}
   *                          is not between the given min and max.
   */
  public final void isBetween(final int min, final int max) {
    isBetween((long) min, (long) max);
  }

  /**
   * @param min
   * @param max
   * @throws RuntimeException if the argument of the current {@link LongMediator}
   *                          is not between the given min and max.
   */
  public final void isBetween(final long min, final long max) {
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
   * @throws RuntimeException if the argument of the current {@link LongMediator}
   *                          is not bigger than the given value.
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
   * @throws RuntimeException if the argument of the current {@link LongMediator}
   *                          is not bigger than or does not equal the given
   *                          value.
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
   * @param values
   * @throws RuntimeException if the given values is null.
   * @throws RuntimeException if the argument of the current {@link LongMediator}
   *                          does not equal one of the given values.
   */
  public final void isEqualToAnyOf(final long... values) {
    if (values == null) {
      throw ArgumentIsNullException.forArgumentName(PluralLowerCaseVariableCatalog.VALUES);
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
   * @throws RuntimeException if the argument of the current {@link LongMediator}
   *                          is not negative.
   */
  public final void isNegative() {
    if (argument >= 0) {
      throw NonNegativeArgumentException.forArgumentAndArgumentName(argument, getArgumentName());
    }
  }

  /**
   * @param min
   * @param max
   * @throws RuntimeException if the argument of the current {@link LongMediator}
   *                          is between the given min and max.
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
   * @throws RuntimeException if the argument of the current {@link LongMediator}
   *                          equals the given value.
   */
  public final void isNotEqualTo(final long value) {
    if (argument == value) {
      throw EqualArgumentException.forArgumentAndArgumentNameAndEqualValue(argument, getArgumentName(), value);
    }
  }

  /**
   * @param value
   * @throws RuntimeException if the argument of the current {@link LongMediator}
   *                          is bigger than the given value.
   */
  public final void isNotBiggerThan(final int value) {
    isNotBiggerThan((long) value);
  }

  /**
   * @param value
   * @throws RuntimeException if the argument of the current {@link LongMediator}
   *                          is bigger than the given value.
   */
  public final void isNotBiggerThan(final long value) {
    if (argument > value) {
      throw BiggerArgumentException.forArgumentAndArgumentNameAndMax(argument, getArgumentName(), value);
    }
  }

  /**
   * @throws RuntimeException if the argument of htis {@link LongMediator} is
   *                          negative.
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
   * @throws RuntimeException if the argument of the current {@link LongMediator}
   *                          is smaller than the given min.
   */
  public final void isNotSmallerThan(final int min) {
    if (argument < min) {
      throw SmallerArgumentException.forArgumentAndArgumentNameAndMin(argument, getArgumentName(), min);
    }
  }

  /**
   * @throws RuntimeException if the argument of the current {@link LongMediator}
   *                          is not a port.
   */
  public final void isPort() {
    if (argument < PortCatalog.MIN_PORT || argument > PortCatalog.MAX_PORT) {
      throw InvalidPortException.forPort(argument);
    }
  }

  /**
   * @throws RuntimeException if the argument of the current {@link LongMediator}
   *                          is not positive.
   */
  public final void isPositive() {
    if (argument < 1) {
      throw NonPositiveArgumentException.forArgumentAndArgumentName(argument, getArgumentName());
    }
  }

  /**
   * @param value
   * @throws RuntimeException if the argument of the current {@link LongMediator}
   *                          is not smaller than the given value.
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
   * @throws RuntimeException if the argument of the current {@link LongMediator}
   *                          is not smaller than or does not equal the given
   *                          value.
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
