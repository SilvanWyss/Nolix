/*
 * file:	DoubleMediator.java
 * author:	Silvan Wyss
 * month:	2016-12
 * lines:	100
 */

//package declaration
package ch.nolix.common.zetaValidator;

//own imports
import ch.nolix.common.exception.NegativeArgumentException;
import ch.nolix.common.exception.NonBiggerArgumentException;
import ch.nolix.common.exception.NonNegativeArgumentException;
import ch.nolix.common.exception.NonPositiveArgumentException;
import ch.nolix.common.exception.PositiveArgumentException;

//class
public final class DoubleMediator {

	//attribute
	private final double argument;
	
	//constructor
	/**
	 * Creates new double mediator with the given argument.
	 * 
	 * @param argument		The argument of the double mediator.
	 */
	public DoubleMediator(final double argument) {
		this.argument = argument;
	}
	
	//method
	/**
	 * @param value		The value the argument of this double mediator is supposed to be bigger than.
	 * @throws NonBiggerArgumentException if the argument of this double mediator is not bigger than the given value
	 */
	public void isBiggerThan(final double value) {
		
		//Checks the argument of this double mediator.
		if (argument <= value) {
			throw new NonBiggerArgumentException(argument, value);
		}
	}
	
	//method
	/**
	 * @throws NonPositiveArgumentException if the argument of this double mediator is not positive
	 */
	public void isPositive() {
		
		//Checks the argument of this double mediator.
		if (argument <= 0) {
			throw new NonPositiveArgumentException(argument);
		}
	}
	
	//method
	/**
	 * @throws NonNegativeArgumentException if the argument of this double mediator is not negative
	 */
	public void isNegative() {
		
		//Checks the argument of this double mediator.
		if (argument >= 0) {
			throw new NonNegativeArgumentException(argument);
		}
	}

	//method
	/**
	 * @throws NegativeArgumentException if the argument of this double mediator is negative
	 */
	public void isNotNegative() {
		
		//Checks the argument of this double mediator.
		if (argument < 0) {
			throw new NegativeArgumentException(argument);
		}
	}
	
	//method
	/**
	 * @throws PositiveArgumentException if the argument of this double mediator is positive
	 */
	public void isNotPositive() {
		
		//Checks the argument of this double mediator.
		if (argument > 0) {
			throw new PositiveArgumentException(argument);
		}
	}

	//method
	/**
	 * @param argumentName		The name of the argument of the created named double mediator
	 * @return new named double mediator with the given argument named and the argument of this double mediator
	 */
	public NamedDoubleMediator thatIsNamed(final String argumentName) {
		return new NamedDoubleMediator(argumentName, argument);
	}
}
