//package declaration
package ch.nolix.common.errorcontrol.validator;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsZeroException;
import ch.nolix.common.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.EqualArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.NonBiggerArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.NonNegativeArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.NonSmallerArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.UnequalArgumentException;

//class
/**
 * A double mediator is a mediator for a double.
 * A double mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 * @lines 230
 */
public class DoubleMediator extends Mediator {
	
	//attribute
	private final double argument;
	
	//constructor
	/**
	 * Creates a new double mediator for the given argument.
	 * 
	 * @param argument
	 */
	DoubleMediator(double argument) {
		
		//Sets the argument of this double mediator.
		this.argument = argument;
	}

	//constructor
	/**
	 * Creates a new double mediator for the given argument with the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws ArgumentIsNullException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	DoubleMediator(final String argumentName, final double argument) {
		
		//Calls constructor of the base class.
		super(argumentName);
		
		//Sets the argument of this double mediator.
		this.argument = argument;
	}
	
	//method
	/**
	 * @param min
	 * @param max
	 * @throws ArgumentIsOutOfRangeException
	 * if the argument of this double mediator is not between the given min and max.
	 */
	public void isBetween(final double min, final double max) {
		
		//Asserts that the argument of this double mediator
		//is between the given min and max.
		if (argument < min || argument > max) {
			throw new ArgumentIsOutOfRangeException(
				getArgumentName(),
				argument,
				min,
				max
			);
		}
	}

	//method
	/**
	 * @param value
	 * @throws NonBiggerArgumentException
	 * if the argument of this double mediator is not bigger than the given value.
	 */
	public void isBiggerThan(final double value) {
		
		//Asserts that the argument of this double mediator is bigger than the given value.
		if (argument <= value) {
			throw new NonBiggerArgumentException(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws UnequalArgumentException
	 * if the argument of this double mediator does not equal the given value.
	 */
	public void isEqualTo(final double value) {
		
		//Asserts that the argument of this double mediator equals the given value.
		if (argument != value) {
			throw new UnequalArgumentException(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @throws NonNegativeArgumentException
	 * if the argument of this double mediator is not negative.
	 */
	public void isNegative() {
		
		//Asserts that the argument of this double mediator is negative.
		if (argument >= 0) {
			throw new NonNegativeArgumentException(getArgumentName(), argument);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws BiggerArgumentException
	 * if the argument of this double mediator is bigger than the given value.
	 */
	public void isNotBiggerThan(final double value) {
		
		//Asserts that the argument of this named long mediator is not bigger than the given value.
		if (argument > value) {
			throw new BiggerArgumentException(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws EqualArgumentException
	 * if the argument of this double mediator equals the given value.
	 */
	public void isNotEqualTo(final double value) {
		
		//Asserts that the argument of this double mediator does not equal the given value.
		if (argument == value) {
			throw new EqualArgumentException(getArgumentName(), argument, value);
		}
	}

	//method
	/**
	 * @throws NegativeArgumentException
	 * if the argument of this double mediator is negative.
	 */
	public void isNotNegative() {
		
		//Asserts that the argument of this double mediator is not negative.
		if (argument < 0) {
			throw new NegativeArgumentException(getArgumentName(), argument);
		}
	}
	
	//method
	/**
	 * @throws NonPositiveArgumentException
	 * if the argument of this double mediator is positive.
	 */
	public void isNotPositive() {
		
		//Asserts that the argument of this double mediator is not positive.
		if (argument <= 0) {
			throw new NonPositiveArgumentException(getArgumentName(), argument);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws SmallerArgumentException
	 * if the argument of this double mediator is smaller than the given value.
	 */
	public void isNotSmallerThan(final double value) {
		
		//Asserts that the argument of this double mediator is not smaller than the given value.
		if (argument > value) {
			throw new SmallerArgumentException(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @throws ArgumentIsZeroException
	 * if the argument of this double mediator is 0.
	 */
	public void isNotZero() {
		
		//Asserts that the argument of this double mediator is not zero.
		isNotEqualTo(0);
	}
	
	//method
	/**
	 * @throws NonPositiveArgumentException if the argument of this double mediator is not positive.
	 */
	public void isPositive() {
		
		//Asserts that the argument of this double mediator is positive.
		if (argument <= 0) {
			throw new NonPositiveArgumentException(getArgumentName(), argument);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws NonSmallerArgumentException
	 * if the argument of this double mediator is not smaller than the given value.
	 */
	public void isSmallerThan(final double value) {
		
		//Asserts that the argument of this double mediator is smaller than the given value.
		if (argument >= value) {
			throw new NonSmallerArgumentException(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @return the argument of htis double mediator.
	 */
	protected double getArgument() {
		return argument;
	}
}
