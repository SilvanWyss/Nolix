//package declaration
package ch.nolix.core.validator2;

import ch.nolix.core.invalidArgumentException.BiggerArgumentException;
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.EqualArgumentException;
import ch.nolix.core.invalidArgumentException.NegativeArgumentException;
import ch.nolix.core.invalidArgumentException.NonBiggerArgumentException;
import ch.nolix.core.invalidArgumentException.NonNegativeArgumentException;
import ch.nolix.core.invalidArgumentException.NonPositiveArgumentException;
import ch.nolix.core.invalidArgumentException.NonSmallerArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;
import ch.nolix.core.invalidArgumentException.OutOfRangeArgumentException;
import ch.nolix.core.invalidArgumentException.SmallerArgumentException;
import ch.nolix.core.invalidArgumentException.UnequalArgumentException;
import ch.nolix.core.invalidArgumentException.ZeroArgumentException;

//class
/**
 * A double mediator is a mediator for a double.
 * A double mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 240
 */
public class DoubleMediator extends Mediator {
	
	//attribute
	private final double argument;
	
	//package-visible constructor
	/**
	 * Creates a new double mediator for the given argument.
	 * 
	 * @param argument
	 */
	DoubleMediator(double argument) {
		
		//Sets the argument of this double mediator.
		this.argument = argument;
	}

	//package-visible constructor
	/**
	 * Creates a new double mediator for the given argument with the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws NullArgumentException if the given argument name is null.
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
	 * @throws OutOfRangeArgumentException
	 * if the argument of this double mediator is not between the given min and max.
	 */
	public void isBetween(final double min, final double max) {
		
		//Checks if the argument of this double mediator
		//is between the given min and max.
		if (argument < min || argument > max) {
			throw new OutOfRangeArgumentException(
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
		
		//Checks if the argument of this double mediator is bigger than the given value.
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
		
		//Checks if the argument of this double mediator equals the given value.
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
		
		//Checks if the argument of this double mediator is negative.
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
		
		//Checks if the argument of this named long mediator is not bigger than the given value.
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
		
		//Checks if the argument of this double mediator does not equal the given value.
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
		
		//Checks if the argument of this double mediator is not negative.
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
		
		//Checks if the argument of this double mediator is not positive.
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
		
		//Checks if the argument of this double mediator is not smaller than the given value.
		if (argument > value) {
			throw new SmallerArgumentException(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @throws ZeroArgumentException
	 * if the argument of this double mediator is 0.
	 */
	public void isNotZero() {
		
		//Checks if the argument of this double mediator is not zero.
		isNotEqualTo(0);
	}
	
	//method
	/**
	 * @throws NonPositiveArgumentException if the argument of this double mediator is not positive.
	 */
	public void isPositive() {
		
		//Checks if the argument of this double mediator is positive.
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
		
		//Checks if the argument of this double mediator is smaller than the given value.
		if (argument >= value) {
			throw new NonSmallerArgumentException(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @throws UnequalArgumentException
	 * if the argument of htis double mediator is not 0.
	 */
	public void isZero() {
		
		//Checks if the argument of this double mediator is 0.
		isEqualTo(0);
	}
	
	//method
	/**
	 * @return the argument of htis double mediator.
	 */
	protected double getArgument() {
		return argument;
	}
}
