//package declaration
package ch.nolix.core.validator;

//Java import
import java.math.BigDecimal;

//class
public final class ExtendedBigDecimalValidator extends BigDecimalValidator {

	//package-visible constructor
	ExtendedBigDecimalValidator(final BigDecimal argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//method
	public BigDecimalValidator thatIsNamed(final String argumentName) {
		return new BigDecimalValidator(argumentName, getRefArgument());
	}
}
