//package declaration
package ch.nolix.common.zetaValidator;

//own imports
import ch.nolix.common.exception.NegativeArgumentException;
import ch.nolix.common.exception.NonNegativeArgumentException;
import ch.nolix.common.exception.NonPositiveArgumentException;
import ch.nolix.common.exception.NonSmallerArgumentException;
import ch.nolix.common.exception.OutOfRangeArgumentException;
import ch.nolix.common.exception.SmallerArgumentException;
import ch.nolix.common.exception.UnequalArgumentException;
import ch.nolix.common.exception.ZeroArgumentException;

//class
/**
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 140
 */
public final class LongMediator {
	
	//attribute
	private final long argument;
	
	//package-visible constructor
	/**
	 * Creates new long mediator with the given argument.
	 * 
	 * @param argument
	 */
	LongMediator(final long argument) {
		this.argument = argument;
	}
	
	//method
	/**
	 * @param value
	 * @throws UnequalArgumentException if the argument of this long mediator does not equal the given value.
	 */
	public void equals(final long value) {
		
		//Checks if the argument of this long mediator equals the given value.
		if (argument != value) {
			throw new UnequalArgumentException(argument, value);
		}
	}
	
	//method
	/**
	 * @param min
	 * @param max
	 * @throws OutOfRangeArgumentException if the argument of this long mediator is not between the given min and max.
	 */
	public void isBetween(final int min, final int max) {
		
		//Checks if the argument of this long mediator is in the range defined by the given min and max.
		if (argument < min || argument > max) {
			throw new OutOfRangeArgumentException(argument, min, max);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws SmallerArgumentException if the argument of this long mediator is smaller than the given value.
	 */
	public void isBiggerThanOrEquals(long value) {
		
		//Checks if the argument of this long mediator is bigger than or equals the given value.
		if (argument <= value) {
			throw new SmallerArgumentException(argument, value);
		}
	}
	
	//method
	/**
	 * @throws NonNegativeArgumentException if the argument of this long mediator is not negative.
	 */
	public void isNegative() {
		
		//Checks if the argument of this long mediator is negative.
		if (argument > 0) {
			throw new NonNegativeArgumentException(argument);
		}
	}
	
	//method
	/**
	 * @throws NegativeArgumentsException if the argument of this long mediator is negative.
	 */
	public void isNotNegative() {
		
		//Checks if the argument of this long mediator is not negative.
		if (argument < 0) {
			throw new NegativeArgumentException(argument);
		}
	}
	
	//method
	/**
	 * @throws ZeroArgumentException if the argument of this long mediator is 0.
	 */
	public void isNotZero() {
		
		//Checks if the argument of this long mediator is not 0.
		if (argument == 0) {
			throw new ZeroArgumentException();
		}
	}
	
	//method
	/**
	 * @throws NonPositiveArgumentsException if the argument of this long mediator is not positive.
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
	 * @throws NonSmallerArgumentException if the argument of this long mediator is not smaller than the given value.
	 */
	public void isSmallerThan(final long value) {
		
		//Checks if the argument of this long mediator is smaller than the given value.
		if (argument >= value) {
			throw new NonSmallerArgumentException(argument, value);
		}
	}
	
	//method
	/**
	 * @param argumentName
	 * @return a new named long mediator with the given argument name and the argument of this long mediator.
	 */
	public NamedLongMediator thatIsNamed(final String argumentName) {
		return new NamedLongMediator(argumentName, argument);
	}
}
