//package declaration
package ch.nolix.core.validator2;

//own imports
import ch.nolix.core.invalidArgumentException.BiggerArgumentException;
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.EqualArgumentException;
import ch.nolix.core.invalidArgumentException.InRangeArgumentException;
import ch.nolix.core.invalidArgumentException.NegativeArgumentException;
import ch.nolix.core.invalidArgumentException.NonNegativeArgumentException;
import ch.nolix.core.invalidArgumentException.NonPositiveArgumentException;
import ch.nolix.core.invalidArgumentException.NonSmallerArgumentException;
import ch.nolix.core.invalidArgumentException.OutOfRangeArgumentException;
import ch.nolix.core.invalidArgumentException.PositiveArgumentException;
import ch.nolix.core.invalidArgumentException.SmallerArgumentException;
import ch.nolix.core.invalidArgumentException.UnequalArgumentException;

//class
/**
 * A long mediator is a mediator for a long.
 * A long mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 240
 */
public final class LongMediator {
	
	//attribute
	private final long argument;
	
	//package-visible constructor
	/**
	 * Creates a new long mediator for the given argument.
	 * 
	 * @param argument
	 */
	LongMediator(final long argument) {
		
		//Sets the argument of this long mediator.
		this.argument = argument;
	}
	
	//method
	/**
	 * @param min
	 * @param max
	 * @throws OutOfRangeArgumentException
	 * if the argument of this long mediator is not between the given min and max.
	 */
	public void isBetween(final long min, final long max) {
		
		//Checks if the argument of this long mediator is between the given min and max.
		if (argument < min || argument > max) {
			throw new OutOfRangeArgumentException(argument, min, max);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws SmallerArgumentException
	 * if the argument of this long mediator is smaller than the given value.
	 */
	public void isBiggerThanOrEquals(final long value) {
		
		//Checks if the argument of this long mediator is bigger than or equals the given value.
		if (argument <= value) {
			throw new SmallerArgumentException(argument, value);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws UnequalArgumentException
	 * if the argument of this long mediator does not equal the given value.
	 */
	public void isEqualTo(final long value) {
		
		//Checks if the argument of this long mediator equals the given value.
		if (argument != value) {
			throw new UnequalArgumentException(argument, value);
		}
	}
	
	//method
	/**
	 * @throws NonNegativeArgumentException
	 * if the argument of this long mediator is not negative.
	 */
	public void isNegative() {
		
		//Checks if the argument of this long mediator is negative.
		if (argument > 0) {
			throw new NonNegativeArgumentException(argument);
		}
	}
	
	//method
	/**
	 * @param min
	 * @param max
	 * @throws InRangeArgumentException
	 * if the argument of this long mediator is between the given min and max.
	 */
	public void isNotBetween(final long min, final long max) {
		
		//Checks if the argument of this long mediator is not between the given min and max.
		if (argument >= min && argument <= max) {
			throw new InRangeArgumentException(argument, min, max);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws BiggerArgumentException
	 * if the argument of this long mediator is bigger than the given value.
	 */
	public void isNotBiggerThan(final long value) {
		
		//Checks if the argument of this long mediator is not bigger than the given value.
		if (argument > value) {
			throw new BiggerArgumentException(argument, value);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws EqualArgumentException
	 * if the argument of this long mediator equals the given value.
	 */
	public void isNotEqualTo(final long value) {
		
		//Checks if the argument of this long mediator equals not the given value.
		if (argument == value) {
			throw new EqualArgumentException(argument, value);
		}
	}
	
	//method
	/**
	 * @throws NegativeArgumentsException
	 * if the argument of this long mediator is negative.
	 */
	public void isNotNegative() {
		
		//Checks if the argument of this long mediator is not negative.
		if (argument < 0) {
			throw new NegativeArgumentException(argument);
		}
	}
	
	//method
	/**
	 * @throws PositiveArgumentException
	 * if the argument of this long mediator is positive.
	 */
	public void isNotPositive() {
		
		//Checks if the argument of this long mediator is not positive.
		if (argument > 0) {
			throw new PositiveArgumentException(argument);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws SmallerArgumentException
	 * if the argument of this long mediator is smaller than the given value.
	 */
	public void isNotSmallerThan(final long value) {
		
		//Checks if the argument of this long mediator is not smaller than the given value.
		if (argument < value) {
			throw new SmallerArgumentException(argument, value);
		}
	}
	
	//method
	/**
	 * @throws EqualArgumentException
	 * if the argument of this long mediator is 0.
	 */
	public void isNotZero() {
		
		//Checks if the argument of this long mediator is not 0.
		isNotEqualTo(0);
	}
	
	//method
	/**
	 * @throws NonPositiveArgumentsException
	 * if the argument of this long mediator is not positive.
	 */
	public void isPositive() {
		
		//Checks if the argument of this long mediator is positive.
		if (argument <= 0) {
			throw new NonPositiveArgumentException(argument);
		}
	}
	
	//method
	/**
	 * @param value	
	 * @throws NonSmallerArgumentException
	 * if the argument of this long mediator is not smaller than the given value.
	 */
	public void isSmallerThan(final long value) {
		
		//Checks if the argument of this long mediator is smaller than the given value.
		if (argument >= value) {
			throw new NonSmallerArgumentException(argument, value);
		}
	}
	
	//method
	/**
	 * @throws UnequalArgumentException
	 * if the argument of this long mediator is not 0.
	 */
	public void isZero() {
		
		//Checks if the argument of this long mediator is 0.
		isEqualTo(0);
	}
	
	//method
	/**
	 * @param argumentName
	 * @return a new named long mediator
	 * with the given argument name and for the argument of this long mediator.
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	public NamedLongMediator thatIsNamed(final String argumentName) {
		return new NamedLongMediator(argumentName, argument);
	}
}
