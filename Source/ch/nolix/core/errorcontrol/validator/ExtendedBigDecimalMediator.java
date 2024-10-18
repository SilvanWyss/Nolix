package ch.nolix.core.errorcontrol.validator;

import java.math.BigDecimal;

public final class ExtendedBigDecimalMediator extends BigDecimalMediator {

  ExtendedBigDecimalMediator(final BigDecimal argument) {

    //Calls constructor of the base class.
    super(argument);
  }

  public BigDecimalMediator thatIsNamed(final String argumentName) {
    return new BigDecimalMediator(argumentName, getStoredArgument());
  }
}
