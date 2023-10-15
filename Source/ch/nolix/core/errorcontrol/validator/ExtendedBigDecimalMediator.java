//package declaration
package ch.nolix.core.errorcontrol.validator;

//Java imports
import java.math.BigDecimal;

//class
public final class ExtendedBigDecimalMediator extends BigDecimalMediator {

  // constructor
  ExtendedBigDecimalMediator(final BigDecimal argument) {

    // Calls constructor of the base class.
    super(argument);
  }

  // method
  public BigDecimalMediator thatIsNamed(final String argumentName) {
    return new BigDecimalMediator(argumentName, getStoredArgument());
  }
}
