package ch.nolix.core.errorcontrol.validator;

import java.math.BigDecimal;

import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;

public class BigDecimalMediator extends ArgumentMediator<BigDecimal> {

  BigDecimalMediator(final BigDecimal argument) {

    //Calls constructor of the base class.
    super(argument);
  }

  BigDecimalMediator(final String argumentName, final BigDecimal argument) {

    //Calls constructor of the base class.
    super(argumentName, argument);
  }

  public final void isNotNegative() {

    //Asserts that the argument of the current BigDecimalValidator is not null.
    isNotNull();

    //Asserts that the argument of the current BigDecimalValidator is not negative.
    if (getStoredArgument().compareTo(BigDecimal.ZERO) < 0) {
      throw NegativeArgumentException.forArgumentAndArgumentName(getStoredArgument(), getArgumentName());
    }
  }

  public final void isNotSmallerThan(final BigDecimal value) {

    //Asserts that the argument of the current BigDecimalValidator is not null.
    isNotNull();

    //Asserts that the argument of the current BigDecimalValidator is not smaller
    //than the given value.
    if (getStoredArgument().compareTo(value) < 0) {
      throw SmallerArgumentException.forArgumentNameAndArgumentAndLimit(getArgumentName(), getStoredArgument(), value);
    }
  }

  public final void isPositive() {

    //Asserts that the argument of the current BigDecimalValidator is not null.
    isNotNull();

    //Asserts that the argument of the current BigDecimalValidator is positive.
    if (getStoredArgument().compareTo(BigDecimal.ZERO) <= 0) {
      throw NonPositiveArgumentException.forArgumentNameAndArgument(getArgumentName(), getStoredArgument());
    }
  }
}
