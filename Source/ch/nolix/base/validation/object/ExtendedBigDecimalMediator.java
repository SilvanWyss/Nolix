/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

import java.math.BigDecimal;

/**
 * @author Silvan Wyss
 */
public final class ExtendedBigDecimalMediator extends BigDecimalMediator {
  public ExtendedBigDecimalMediator(final BigDecimal argument) {
    //Calls constructor of the base class.
    super(argument);
  }

  public BigDecimalMediator thatIsNamed(final String argumentName) {
    return new BigDecimalMediator(argumentName, getStoredArgument());
  }
}
