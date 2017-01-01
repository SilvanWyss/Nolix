/*
 * file:	LongMediator.java
 * author:	Silvan Wyss
 * month:	2016-11
 * lines:	120
 */

//package declaration
package ch.nolix.common.zetaValidator;

//own imports
import ch.nolix.common.exception.NegativeArgumentException;
import ch.nolix.common.exception.NonNegativeArgumentException;
import ch.nolix.common.exception.NonPositiveArgumentException;
import ch.nolix.common.exception.NonSmallerArgumentException;
import ch.nolix.common.exception.SmallerArgumentException;
import ch.nolix.common.exception.UnequalArgumentException;
import ch.nolix.common.exception.ZeroArgumentException;

//class
public final class LongMediator {
	
	//attribute
	private final long argument;
	
	//constructor
	/**
	 * Creates new long mediator with the given argument.
	 * 
	 * @param argument		The argument of this long mediator.
	 */
	LongMediator(final long argument) {
		this.argument = argument;
	}
	
	public void equals(final long value) {
		
		//Checks the argument of this long mediator.
		if (argument != value) {
			throw new UnequalArgumentException(argument, value);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws SmallerArgumentException if the argument of this long mediator is smaller than the given value
	 */
	public void isBiggerThanOrEquals(long value) {
		if (argument <= value) {
			throw new SmallerArgumentException(argument, value);
		}
	}
	
	//method
	/**
	 * @throws NonNegativeArgumentException if the argument of this long mediator is not negative
	 */
	public void isNegative() {
		if (argument > 0) {
			throw new NonNegativeArgumentException(argument);
		}
	}
	
	//method
	/**
	 * @throws NegativeArgumentsException if the argument of this long mediator is negative
	 */
	public void isNotNegative() {
		
		//Checks the argument of this long mediator.
		if (argument < 0) {
			throw new NegativeArgumentException(argument);
		}
	}
	
	//method
	/**
	 * @throws ZeroArgumentException if the argument of this long mediator is 0
	 */
	public void isNotZero() {
		
		//Checks the argument of this long mediator.
		if (argument == 0) {
			throw new ZeroArgumentException(argument);
		}
	}
	
	//method
	/**
	 * @throws NonPositiveArgumentsException if the argument of this long mediator is not positive
	 */
	public void isPositive() {
		
		//Checks the argument of this long mediator.
		if (argument <= 0) {
			throw new NonPositiveArgumentException(argument);
		}
	}
	
	//method
	/**
	 * @param value		The value the argument of this long mediator is supposed to be smaller than.
	 * @throws NonSmallerArgumentException if the argument of this long mediator is not smaller than the given value
	 */
	public void isSmallerThan(final long value) {
		
		//Checks the argument of this long mediator.
		if (argument >= value) {
			throw new NonSmallerArgumentException(argument, value);
		}
	}
	
	//method
	/**
	 * @param argumentName		The name of the argument of the created named long mediator.
	 * @return a new named long mediator with the given argument name and the argument of this long mediator
	 */
	public NamedLongMediator thatIsNamed(final String argumentName) {
		return new NamedLongMediator(argumentName, argument);
	}
}
