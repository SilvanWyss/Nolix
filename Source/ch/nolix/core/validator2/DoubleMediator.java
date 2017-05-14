//package declaration
package ch.nolix.core.validator2;

//own imports
import ch.nolix.core.invalidArgumentException.NegativeArgumentException;
import ch.nolix.core.invalidArgumentException.NonBiggerArgumentException;
import ch.nolix.core.invalidArgumentException.NonNegativeArgumentException;
import ch.nolix.core.invalidArgumentException.NonPositiveArgumentException;
import ch.nolix.core.invalidArgumentException.PositiveArgumentException;
import ch.nolix.core.invalidArgumentException.SmallerArgumentException;

//class
/**
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 110
 */
public final class DoubleMediator {

	//attribute
	private final double argument;
	
	//package-visible constructor
	/**
	 * Creates new double mediator with the given argument.
	 * 
	 * @param argument
	 */
	DoubleMediator(final double argument) {
		this.argument = argument;
	}
	
	//method
	/**
	 * @param value
	 * @throws NonBiggerArgumentException if the argument of this double mediator is not bigger than the given value.
	 */
	public void isBiggerThan(final double value) {
		
		//Checks if the argument of this double mediator is bigger than the given value.
		if (argument <= value) {
			throw new NonBiggerArgumentException(argument, value);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws SmallerArgumentException if the argument of this double mediator is not bigger than or does not equal the given value.
	 */
	public void isBiggerThanOrEquals(final double value) {
		
		//Checks if the argument of this double mediator is bigger than or equals the given value.
		if (argument < value) {
			throw new SmallerArgumentException(argument, value);
		}
	}
	
	//method
	/**
	 * @throws NonPositiveArgumentException if the argument of this double mediator is not positive.
	 */
	public void isPositive() {
		
		//Checks if the argument of thid double mediator is positive.
		if (argument <= 0) {
			throw new NonPositiveArgumentException(argument);
		}
	}
	
	//method
	/**
	 * @throws NonNegativeArgumentException if the argument of this double mediator is not negative.
	 */
	public void isNegative() {
		
		//Checks if the argument of thid double mediator is negative.
		if (argument >= 0) {
			throw new NonNegativeArgumentException(argument);
		}
	}

	//method
	/**
	 * @throws NegativeArgumentException if the argument of this double mediator is negative.
	 */
	public void isNotNegative() {
		
		//Checks if the argument of thid double mediator is not negative.
		if (argument < 0) {
			throw new NegativeArgumentException(argument);
		}
	}
	
	//method
	/**
	 * @throws PositiveArgumentException if the argument of this double mediator is positive.
	 */
	public void isNotPositive() {
		
		//Checks if the argument of thid double mediator is not positive.
		if (argument > 0) {
			throw new PositiveArgumentException(argument);
		}
	}

	//method
	/**
	 * @param argumentName
	 * @return new named double mediator with the given argument name and the argument of this double mediator.
	 */
	public NamedDoubleMediator thatIsNamed(final String argumentName) {
		return new NamedDoubleMediator(argumentName, argument);
	}
}
