//package declaration
package ch.nolix.core.validator;

//Java import
import java.math.BigDecimal;

//own imports
import ch.nolix.core.invalidArgumentException.NegativeArgumentException;
import ch.nolix.core.invalidArgumentException.SmallerArgumentException;

//class
public class BigDecimalValidator extends ArgumentMediator<BigDecimal> {
	
	//package-visible constructor
	BigDecimalValidator(final BigDecimal argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//package-visible constructor
	BigDecimalValidator(final String argumentName, final BigDecimal argument) {
		
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
}
