//package declaration
package ch.nolix.common.zetaValidator;

//own imports
import ch.nolix.common.exception.EmptyArgumentException;
import ch.nolix.common.exception.NegativeArgumentException;
import ch.nolix.common.exception.NonBiggerArgumentException;
import ch.nolix.common.exception.NonPositiveArgumentException;
import ch.nolix.common.exception.NullArgumentException;
import ch.nolix.common.exception.SmallerArgumentException;

//class
/**
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 80
 */
public final class NamedDoubleMediator extends NamedArgumentMediator {
	
	//attribute
	private final double argument;

	//package-visible constructor
	/**
	 * Creates new named double mediator with the given argument name and argument.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is an empty string.
	 */
	NamedDoubleMediator(final String argumentName, final double argument) {
		
		//Calls constructor of the base class.
		super(argumentName);
		
		this.argument = argument;
	}
	
	//method
	/**
	 * @param value
	 * @throws NonBiggerArgumentException if the argument of this named double mediator is not bigger than the given value.
	 */
	public void isBiggerThan(final double value) {
		
		//Checks if the argument of this named double mediator is bigger than the given value.
		if (argument <= value) {
			throw new NonBiggerArgumentException(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws SmallerArgumentException if the argumetn of this named double mediator is not smaller than or equals the given value.
	 */
	public void isBiggerThanOrEquals(final double value) {
		
		//Checks if the argument of this named double mediator is bigger than or equals the given value.
		if (argument < value) {
			throw new SmallerArgumentException(value, value);
		}
	}

	//method
	/**
	 * @throws NegativeArgumentException if the argument of this named double mediator is negative.
	 */
	public void isNotNegative() {
		
		//Checks if the argument of this named double mediator is not negative.
		if (argument < 0) {
			throw new NegativeArgumentException(getArgumentName(), argument);
		}
	}
	
	//method
	/**
	 * @throws NonPositiveArgumentException if the argument of this named double mediator is not positive.
	 */
	public void isPositive() {
		
		//Checks if the argument of this named double mediator is positive.
		if (argument <= 0) {
			throw new NonPositiveArgumentException(getArgumentName(), argument);
		}
	}
}
