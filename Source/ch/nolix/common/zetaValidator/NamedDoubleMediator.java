/*
 * file:	NamedDoubleMediator.java
 * author:	Silvan Wyss
 * month:	2016-12
 * lines:	70
 */

//package declaration
package ch.nolix.common.zetaValidator;

//own imports
import ch.nolix.common.exception.NegativeArgumentException;
import ch.nolix.common.exception.NonBiggerArgumentException;
import ch.nolix.common.exception.NonPositiveArgumentException;

//class
public final class NamedDoubleMediator extends NamedArgumentMediator {
	
	//attribute
	private final double argument;

	//package-visible constructor
	/**
	 * Creates new named double mediator with the given argument name and argument.
	 * 
	 * @param argumentName		The name of the argument of this named double mediator.
	 * @param argument			The argument of this named double mediator.
	 */
	NamedDoubleMediator(final String argumentName, final double argument) {
		
		//Calls constructor of the base class.
		super(argumentName);
		
		this.argument = argument;
	}
	
	//method
	/**
	 * @param value		The value the argument of this named double mediator is supposed to be bigger than.
	 * @throws NonBiggerArgumentException if the argument of this named double mediator is not bigger than the given value
	 */
	public void isBiggerThan(final double value) {
		
		//Checks the argument of this named double mediator.
		if (argument <= value) {
			throw new NonBiggerArgumentException(getArgumentName(), argument, value);
		}
	}

	//method
	/**
	 * @throws NegativeArgumentException if the argument of this named double mediator is negative
	 */
	public void isNotNegative() {
		
		//Checks the argument of this named double mediator.
		if (argument < 0) {
			throw new NegativeArgumentException(getArgumentName(), argument);
		}
	}
	
	//method
	/**
	 * @throws NonPositiveArgumentException if the argument of this named double mediator is not positive
	 */
	public void isPositive() {
		
		//Checks the argument of this named double mediator.
		if (argument <= 0) {
			throw new NonPositiveArgumentException(getArgumentName(), argument);
		}
	}
}
