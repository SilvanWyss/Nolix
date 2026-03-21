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

  public static NamableBigDecimalMediator forArgument(final BigDecimal argument) {
    return new NamableBigDecimalMediator(argument);
  }

  public BigDecimalMediator thatIsNamed(final String argumentName) {
    return BigDecimalMediator.forArgumentAndArumgentName(getStoredArgument(), argumentName);
  }
}
