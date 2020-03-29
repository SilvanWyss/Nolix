//package declaration
package ch.nolix.common.validator;

//Java import
import java.math.BigDecimal;

//own imports
import ch.nolix.common.invalidArgumentExceptions.NegativeArgumentException;
import ch.nolix.common.invalidArgumentExceptions.NonPositiveArgumentException;
import ch.nolix.common.invalidArgumentExceptions.SmallerArgumentException;

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
		
		//Checks if the argument of the current BigDecimalValidator is not null.
		isNotNull();
		
		//Checks if the argument of the current BigDecimalValidator is not negative.
		if (getRefArgument().compareTo(BigDecimal.ZERO) < 0) {
			throw new NegativeArgumentException(getArgumentName(), getRefArgument());
		}
	}
	
	//method
	public final void isNotSmallerThan(final BigDecimal value) {
		
		//Checks if the argument of the current BigDecimalValidator is not null.
		isNotNull();
		
		//Checks if the argument of the current BigDecimalValidator is not smaller than the given value.
		if (getRefArgument().compareTo(value) < 0) {
			throw new SmallerArgumentException(getArgumentName(), getRefArgument(), value);
		}
	}
	
	//method
	public final void isPositive() {
		
		//Checks if the argument of the current BigDecimalValidator is not null.
		isNotNull();
		
		//Checks if the argument of the current BigDecimalValidator is positive.
		if (getRefArgument().compareTo(BigDecimal.ZERO) <= 0) {
			throw new NonPositiveArgumentException(getArgumentName(), getRefArgument());
		}
	}
}
