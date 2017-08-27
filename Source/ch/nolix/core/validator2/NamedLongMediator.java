//package declaration
package ch.nolix.core.validator2;

//own imports
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.BiggerArgumentException;
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.ErrorPredicate;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidArgumentException.NegativeArgumentException;
import ch.nolix.core.invalidArgumentException.NonBiggerArgumentException;
import ch.nolix.core.invalidArgumentException.NonNegativeArgumentException;
import ch.nolix.core.invalidArgumentException.NonPositiveArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;
import ch.nolix.core.invalidArgumentException.OutOfRangeArgumentException;
import ch.nolix.core.invalidArgumentException.PositiveArgumentException;
import ch.nolix.core.invalidArgumentException.SmallerArgumentException;
import ch.nolix.core.invalidArgumentException.UnequalArgumentException;

//class
/**
 * A named long mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 200
 */
public final class NamedLongMediator extends NamedMediator {
	
	//attribute
	private final long argument;

	//package-visible constructor
	/**
	 * Creates new named long mediator with the given argument name and argument.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is an empty string.
	 */
	NamedLongMediator(final String argumentName, final long argument) {
		
		//Calls constructor of the base class.
		super(argumentName);
		
		//Sets the argument of this named long mediator.
		this.argument = argument;
	}
	
	//method
	/**
	 * @param value
	 * @throws UnequalArgumentException if the argument of this named long mediator does not equal the given value.
	 */
	public void equals(final long value) {
		
		//Checks if the argument of this named long mediator equals the given value.
		if (argument != value) {
			throw new UnequalArgumentException(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @param values
	 * @throws InvalidArgumentException
	 * if the argument of this named long mediator does not equal one of the given values.
	 */
	public void equalsAny(final long... values) {
		
		//Iterates the given values.
		for (final long v : values) {
			
			//Checks if the argument of this named long mediator equals the current value.
			if (argument == v) {
				return;
			}
		}
		
		throw new InvalidArgumentException(
			new ArgumentName(getArgumentName()),
			new Argument(argument),
			new ErrorPredicate("does not equal one of the given values")
		);
	}
	
	//method
	/**
	 * @param min
	 * @param max
	 * @throws OutOfRangeArgumentException if the argument of this named long mediator is not between the given min and max.
	 */
	public void isBetween(final int min, final int max) {
		
		//Checks if the argument of htis named long mediator is in the range defined by the given min and max.
		if (argument < min || argument > max) {
			throw new OutOfRangeArgumentException(getArgumentName(), argument, min, max);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws NonBiggerArgumentException if the argument of this named long mediator is not bigger than the given value.
	 */
	public void isBiggerThan(final long value) {
		
		//Checks if the argument of this named long mediator is bigger than the given value.
		if (argument <= value) {
			throw new NonBiggerArgumentException(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws SmallerArgumentException if the argument of htis named long mediator is not bigger than or does not equal the given value.
	 */
	public void isBiggerThanOrEquals(final long value) {
		
		//Checks if the argument of this named long mediator is bigger than or equals the given value.
		if (argument < value) {
			throw new SmallerArgumentException(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @throws NonNegativeArgumentException if the argument of this named long mediator is not negative.
	 */
	public void isNegative() {
		
		//Checks if the argument of this named long mediator is negative.
		if (argument >= 0) {
			throw new NonNegativeArgumentException(getArgumentName(), argument);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws BiggerArgumentException if the argument of this named long mediator is bigger than the given value.
	 */
	public void isNotBiggerThan(final long value) {
		
		//Checks if the argument of this named long mediator is not bigger than the given value.
		if (argument > value) {
			throw new BiggerArgumentException(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @throws NegativeArgumentException if the argument of htis named long mediator is negative.
	 */
	public void isNotNegative() {
		
		//Checks if the argument of this named long mediator is not negative.
		if (argument < 0.0) {
			throw new NegativeArgumentException(getArgumentName(), argument);
		}
	}
	
	//method
	/**
	 * @throws PositiveArgumentException if the argument of this named long mediator is positive.
	 */
	public void isNotPositive() {
		
		//Checks if the argument of this named long mediator is not positive.
		if (argument > 0) {
			throw new PositiveArgumentException(getArgumentName(), argument);
		}
	}
	
	//method
	/**
	 * @param SmallerArgumentException if the argument of this named long mediator is smaller than the given value.
	 */
	public void isNotSmallerThan(final long value) {
	
		//Checks if the argument of this named long mediator is not smaller than the given value.
		if (argument < value) {
			throw new SmallerArgumentException(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @throws NonPositiveArgumentException if the argument of this named long mediator is not positive.
	 */
	public void isPositive() {
		
		//Checks if the argument of this named long mediator is positive.
		if (argument < 1) {
			throw new NonPositiveArgumentException(getArgumentName(), argument);
		}
	}
	
	//method
	/**
	 * @throws UnequalArgumentException if the argument of this named long mediator is not 0.
	 */
	public void isZero() {
		equals(0);
	}
}
