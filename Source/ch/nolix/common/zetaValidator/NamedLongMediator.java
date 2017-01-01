/*
 * file:	NamedLongMediator.java
 * author:	Silvan Wyss
 * month:	2016-12
 * lines:	80
 */

//package declaration
package ch.nolix.common.zetaValidator;

//own imports
import ch.nolix.common.exception.NonBiggerArgumentException;
import ch.nolix.common.exception.NonNegativeArgumentException;
import ch.nolix.common.exception.NonPositiveArgumentException;
import ch.nolix.common.exception.PositiveArgumentException;

//class
public final class NamedLongMediator extends NamedArgumentMediator {
	
	//attribute
	private final long argument;

	//package-visible constructor
	/**
	 * Creates new named long mediator with the guven argument name and argument.
	 * 
	 * @param argumentName		The name of the arugment of this named long mediator.
	 * @param argument			The argument of this named long mediator.
	 */
	NamedLongMediator(String argumentName, long argument) {
		
		//Calls constructor of the base class.
		super(argumentName);
		
		this.argument = argument;
	}
	
	//method
	/**
	 * @param value		The value the argument of this named long mediator is supposed to be bigger than.
	 * @throws NonBiggerArgumentException if the argument of this named long mediator is not bigger than the given value
	 */
	public void isBiggerThan(final long value) {
		
		//Checks the argument of this named long mediator.
		if (argument <= value) {
			throw new NonBiggerArgumentException(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @throws NonNegativeArgumentException if the argument of this named long mediator is not negative
	 */
	public void isNegative() {
		
		//Checks the argument of this named long mediator.
		if (argument >= 0) {
			throw new NonNegativeArgumentException(getArgumentName(), argument);
		}
	}
	
	//method
	/**
	 * @throws PositiveArgumentException if the argument of this named long mediator is positive
	 */
	public void isNotPositive() {
		
		//Checks the argument of this named long mediator.
		if (argument > 0) {
			throw new PositiveArgumentException(getArgumentName(), argument);
		}
	}
	
	//method
	/**
	 * @throws NonPositiveArgumentException if the argument of this named long mediator is not positive
	 */
	public void isPositive() {
		
		//Checks the argument of this named long mediator.
		if (argument < 1) {
			throw new NonPositiveArgumentException(getArgumentName(), argument);
		}
	}

	public void isNotNegative() {
		// TODO Auto-generated method stub
		
	}
}
