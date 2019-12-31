//package declaration
package ch.nolix.common.validator;

//Java import
import java.math.BigDecimal;

//class
public final class ExtendedBigDecimalValidator extends BigDecimalValidator {

	//constructor
	ExtendedBigDecimalValidator(final BigDecimal argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//method
	public BigDecimalValidator thatIsNamed(final String argumentName) {
		return new BigDecimalValidator(argumentName, getRefArgument());
	}
}
