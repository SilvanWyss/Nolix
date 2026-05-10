/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.primitive;

import ch.nolix.base.validation.base.AbstractMediator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.UnequalArgumentException;

/**
 * @author Silvan Wyss
 */
public final class DoubleDeviationMediator extends AbstractMediator {
  private final double argument;

  private final double maxDeviation;

  /**
   * Creates a {@link DoubleDeviationMediator} with the given argument,
   * argumentName and maxDeviation.
   * 
   * @param argument
   * @param argumentName
   * @param maxDeviation
   * @throws RuntimeException if the given argumentName is null or blank.
   * @throws RuntimeException if the given maxDeviation is negative.
   */
  private DoubleDeviationMediator(final double argument, final String argumentName, final double maxDeviation) {
    if (maxDeviation < 0.0) {
      throw NegativeArgumentException.forArgumentAndArgumentName(maxDeviation, "max deviation");
    }

    super(argumentName);

    this.argument = argument;
    this.maxDeviation = maxDeviation;
  }

  /**
   * @param argument
   * @param argumentName
   * @param maxDeviation
   * @return a {@link DoubleDeviationMediator} with the given argument,
   *         argumentName and maxDeviation.
   * @throws RuntimeException if the given argumentName is null or blank.
   * @throws RuntimeException if the given maxDeviation is negative.
   */
  public static DoubleDeviationMediator forArgumentAndArgumentNameAndMaxDeviation(
    final double argument,
    final String argumentName,
    final double maxDeviation) {
    return new DoubleDeviationMediator(argument, argumentName, maxDeviation);
  }

  /**
   * @param value
   * @throws UnequalArgumentException if the argument of this named double
   *                                  deviation mediator does not equal the given
   *                                  value with a deviation that is not bigger
   *                                  than the max deviation of this named double
   *                                  deviation mediator.
   */
  public void isEqualTo(final double value) {
    if (Math.abs(value - argument) > maxDeviation) {
      throw UnequalArgumentException.forArgumentAndArgumentNameAndValue(value, getArgumentName(), argument);
    }
  }
}
