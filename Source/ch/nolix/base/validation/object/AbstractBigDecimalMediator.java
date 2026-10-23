/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

import java.math.BigDecimal;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.SmallerArgumentException;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractBigDecimalMediator extends AbstractObjectMediator<BigDecimal> {
  protected AbstractBigDecimalMediator(final BigDecimal argument) {
    super(argument);
  }

  protected AbstractBigDecimalMediator(final BigDecimal argument, final String argumentName) {
    super(argument, argumentName);
  }

  /**
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractBigDecimalMediator} is null or
   *                          negative
   */
  public final void isNotNegative() {
    isNotNull();

    if (getStoredArgument().compareTo(BigDecimal.ZERO) < 0) {
      throw NegativeArgumentException.forArgumentAndArgumentName(getStoredArgument(), getArgumentName());
    }
  }

  /**
   * @param value
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractBigDecimalMediator} is null or
   *                          smaller than the given value
   */
  public final void isNotSmallerThan(final BigDecimal value) {
    isNotNull();

    if (getStoredArgument().compareTo(value) < 0) {
      throw SmallerArgumentException.forArgumentAndArgumentNameAndLimit(getStoredArgument(), getArgumentName(), value);
    }
  }

  /**
   * @throws RuntimeException if the argument of the current
   *                          {@link AbstractBigDecimalMediator} is null or not
   *                          positive
   */
  public final void isPositive() {
    isNotNull();

    if (getStoredArgument().compareTo(BigDecimal.ZERO) <= 0) {
      throw NonPositiveArgumentException.forArgumentAndArgumentName(getStoredArgument(), getArgumentName());
    }
  }
}
