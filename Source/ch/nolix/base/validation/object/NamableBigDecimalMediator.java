/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

import java.math.BigDecimal;

/**
 * @author Silvan Wyss
 */
public final class NamableBigDecimalMediator extends AbstractBigDecimalMediator {
  private NamableBigDecimalMediator(final BigDecimal argument) {
    super(argument);
  }

  /**
   * @param argument
   * @return a new {@link NamableBigDecimalMediator} for the given argument
   */
  public static NamableBigDecimalMediator forArgument(final BigDecimal argument) {
    return new NamableBigDecimalMediator(argument);
  }

  /**
   * @param argumentName
   * @return a new {@link BigDecimalMediator} for the argument of the current
   *         {@link NamableBigDecimalMediator} with the given argumentName
   */
  public BigDecimalMediator thatIsNamed(final String argumentName) {
    return BigDecimalMediator.forArgumentAndArumgentName(getStoredArgument(), argumentName);
  }
}
