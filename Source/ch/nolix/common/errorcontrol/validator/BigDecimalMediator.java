//package declaration
package ch.nolix.common.errorcontrol.validator;

//Java imports
import java.math.BigDecimal;

import ch.nolix.common.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.SmallerArgumentException;

//class
public class BigDecimalMediator extends ArgumentMediator<BigDecimal> {
	
	//constructor
	BigDecimalMediator(final BigDecimal argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//constructor
	BigDecimalMediator(final String argumentName, final BigDecimal argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument);
	}
	
	//method
	public final void isNotNegative() {
		
		//Asserts that the argument of the current BigDecimalValidator is not null.
		isNotNull();
		
		//Asserts that the argument of the current BigDecimalValidator is not negative.
		if (getRefArgument().compareTo(BigDecimal.ZERO) < 0) {
			throw new NegativeArgumentException(getArgumentName(), getRefArgument());
		}
	}
	
	//method
	public final void isNotSmallerThan(final BigDecimal value) {
		
		//Asserts that the argument of the current BigDecimalValidator is not null.
		isNotNull();
		
		//Asserts that the argument of the current BigDecimalValidator is not smaller than the given value.
		if (getRefArgument().compareTo(value) < 0) {
			throw new SmallerArgumentException(getArgumentName(), getRefArgument(), value);
		}
	}
	
	//method
	public final void isPositive() {
		
		//Asserts that the argument of the current BigDecimalValidator is not null.
		isNotNull();
		
		//Asserts that the argument of the current BigDecimalValidator is positive.
		if (getRefArgument().compareTo(BigDecimal.ZERO) <= 0) {
			throw new NonPositiveArgumentException(getArgumentName(), getRefArgument());
		}
	}
}
