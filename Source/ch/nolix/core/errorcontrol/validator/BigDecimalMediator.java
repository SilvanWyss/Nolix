//package declaration
package ch.nolix.core.errorcontrol.validator;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;

//class
public class BigDecimalMediator extends ArgumentMediator<BigDecimal> {

  // constructor
  BigDecimalMediator(final BigDecimal argument) {

    // Calls constructor of the base class.
    super(argument);
  }

  // constructor
  BigDecimalMediator(final String argumentName, final BigDecimal argument) {

    // Calls constructor of the base class.
    super(argumentName, argument);
  }

  // method
  public final void isNotNegative() {

    // Asserts that the argument of the current BigDecimalValidator is not null.
    isNotNull();

    // Asserts that the argument of the current BigDecimalValidator is not negative.
    if (getStoredArgument().compareTo(BigDecimal.ZERO) < 0) {
      throw NegativeArgumentException.forArgumentNameAndArgument(getArgumentName(), getStoredArgument());
    }
  }

  // method
  public final void isNotSmallerThan(final BigDecimal value) {

    // Asserts that the argument of the current BigDecimalValidator is not null.
    isNotNull();

    // Asserts that the argument of the current BigDecimalValidator is not smaller
    // than the given value.
    if (getStoredArgument().compareTo(value) < 0) {
      throw SmallerArgumentException.forArgumentNameAndArgumentAndLimit(getArgumentName(), getStoredArgument(), value);
    }
  }

  // method
  public final void isPositive() {

    // Asserts that the argument of the current BigDecimalValidator is not null.
    isNotNull();

    // Asserts that the argument of the current BigDecimalValidator is positive.
    if (getStoredArgument().compareTo(BigDecimal.ZERO) <= 0) {
      throw NonPositiveArgumentException.forArgumentNameAndArgument(getArgumentName(), getStoredArgument());
    }
  }
}
