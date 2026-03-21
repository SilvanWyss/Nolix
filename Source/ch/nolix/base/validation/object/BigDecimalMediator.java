/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

import java.math.BigDecimal;

/**
 * @author Silvan Wyss
 */
public final class BigDecimalMediator extends AbstractBigDecimalMediator {
  private BigDecimalMediator(final BigDecimal argument, final String argumentName) {
    super(argument, argumentName);
  }

  public static BigDecimalMediator forArgumentAndArumgentName(final BigDecimal argument, final String argumentName) {
    return new BigDecimalMediator(argument, argumentName);
  }
}
