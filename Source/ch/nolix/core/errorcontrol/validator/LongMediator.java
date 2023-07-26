//package declaration
package ch.nolix.core.errorcontrol.validator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsInRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EqualArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidPortException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonNegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.PositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnequalArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.independent.containerhelper.GlobalArrayHelper;
import ch.nolix.core.net.constant.PortCatalogue;

//class
/**
 * A long mediator is a mediator for a long with a name.
 * A long mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 */
public class LongMediator extends Mediator {
	
	//attribute
	private final long argument;
	
	//constructor
	/**
	 * Creates a new long mediator for the given argument.
	 * 
	 * @param argument
	 */
	LongMediator(final long argument) {
		
		//Sets the argument of this long mediator.
		this.argument = argument;
	}

	//constructor
	/**
	 * Creates a new long mediator for the given argument with the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws ArgumentIsNullException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	LongMediator(final String argumentName, final long argument) {
		
		//Calls constructor of the base class.
		super(argumentName);
		
		//Sets the argument of this long mediator.
		this.argument = argument;
	}
	
	/**
	 * @param min
	 * @param max
	 * @throws ArgumentIsOutOfRangeException if
	 * the argument of the current {@link LongMediator} is not between the given min and max.
	 */
	public void isBetween(final int min, final int max) {
		if (argument < min || argument > max) {
			throw
			ArgumentIsOutOfRangeException.forArgumentNameAndArgumentAndRangeWithMinAndMax(
				getArgumentName(),
				argument,
				min,
				max
			);
		}
	}
	
	//method
	/**
	 * @param min
	 * @param max
	 * @throws ArgumentIsOutOfRangeException if
	 * the argument of the current {@link LongMediator} is not between the given min and max.
	 */
	public void isBetween(final long min, final long max) {
		if (argument < min || argument > max) {
			throw
			ArgumentIsOutOfRangeException.forArgumentNameAndArgumentAndRangeWithMinAndMax(
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
	 * @throws InvalidArgumentException
	 * if the argument of this long mediator is not bigger than the given value.
	 */
	public final void isBiggerThan(final long value) {
		
		//Asserts that the argument of this long mediator is bigger than the given value.
		if (argument <= value) {
			throw
			InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				getArgumentName(),
				argument,
				"is not bigger than the given min " + value
			);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws SmallerArgumentException
	 * if the argument of this long mediator
	 * is not bigger than or does not equal the given value.
	 */
	public final void isBiggerThanOrEquals(final long value) {
		
		//Asserts that the argument of this long mediator
		//is bigger than or equals the given value.
		if (argument < value) {
			throw SmallerArgumentException.forArgumentNameAndArgumentAndLimit(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws UnequalArgumentException
	 * if the argument of this long mediator does not equal the given value.
	 */
	public final void isEqualTo(final long value) {
		
		//Asserts that the argument of this long mediator equals the given value.
		if (argument != value) {
			throw UnequalArgumentException.forArgumentNameAndArgumentAndValue(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @param value
	 * @param values
	 * @throws InvalidArgumentException
	 * if the argument of this long mediator does not equal one of the given values.
	 */
	public final void isEqualToAny(final long value, final long... values) {
		
		//Checks if the argument of the current LongMediator equals the given value.
		if (argument == value) {
			return;
		}
		
		//Iterates the given values.
		for (final long v : values) {
			
			//Checks if the argument of the current LongMediator equals the current value.
			if (argument == v) {
				return;
			}
		}
		
		throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
			getArgumentName(),
			argument,
			"does not equal one of {" + GlobalArrayHelper.createString(values) + "}"
		);
	}
	
	//method
	/**
	 * @throws NonNegativeArgumentException
	 * if the argument of this long mediator is not negative.
	 */
	public final void isNegative() {
		
		//Asserts that the argument of this long mediator is negative.
		if (argument >= 0) {
			throw NonNegativeArgumentException.forArgumentNameAndArgument(getArgumentName(), argument);
		}
	}
	
	//method
	/**
	 * @param min
	 * @param max
	 * @throws ArgumentIsInRangeException
	 * if the argument of this long mediator is between the given min and max.
	 */
	public final void isNotBetween(final int min, final int max) {
		
		//Asserts that the argument of this long mediator
		//is not between the given min and max.
		if (argument >= min && argument <= max) {
			throw ArgumentIsInRangeException.forArgumentNameAndArgumentAndRangeWithMinAndMax(
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
	 * @throws EqualArgumentException if the argument of this long mediator equals the given value.
	 */
	public final void isNotEqualTo(final long value) {
		
		//Asserts that the argument of this long mediator does not equals the given value.
		if (argument == value) {
			throw EqualArgumentException.forArgumentNameAndArgumentAndEqualValue(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws BiggerArgumentException
	 * if the argument of this long mediator is bigger than the given value.
	 */
	public final void isNotBiggerThan(final int value) {
		isNotBiggerThan((long)value);
	}
	
	//method
	/**
	 * @param value
	 * @throws BiggerArgumentException
	 * if the argument of this long mediator is bigger than the given value.
	 */
	public final void isNotBiggerThan(final long value) {
		
		//Asserts that the argument of this long mediator is not bigger than the given value.
		if (argument > value) {
			throw BiggerArgumentException.forArgumentNameAndArgumentAndMax(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @throws NegativeArgumentException
	 * if the argument of htis long mediator is negative.
	 */
	public final void isNotNegative() {
		
		//Asserts that the argument of this long mediator is not negative.
		if (argument < 0.0) {
			throw NegativeArgumentException.forArgumentNameAndArgument(getArgumentName(), argument);
		}
	}
	
	//method
	/**
	 * @throws PositiveArgumentException
	 * if the argument of this long mediator is positive.
	 */
	public final void isNotPositive() {
		
		//Asserts that the argument of this long mediator is not positive.
		if (argument > 0) {
			throw PositiveArgumentException.forArgumentNameAndArgument(getArgumentName(), argument);
		}
	}
	
	//method
	/**
	 * @throws InvalidPortException if the argument of the current {@link LongMediator} is not a port.
	 */
	public final void isPort() {
		if (argument < PortCatalogue.MIN_PORT || argument > PortCatalogue.MAX_PORT) {
			throw InvalidPortException.forPort(argument);
		}
	}
	
	//method
	/**
	 * @throws NonPositiveArgumentException
	 * if the argument of this long mediator is not positive.
	 */
	public final void isPositive() {
		
		//Asserts that the argument of this long mediator is positive.
		if (argument < 1) {
			throw NonPositiveArgumentException.forArgumentNameAndArgument(getArgumentName(), argument);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws InvalidArgumentException
	 * if the argument of this long mediator is not smaller than the given value.
	 */
	public final void isSmallerThan(final long value) {
		
		//Asserts that the argument of this long mediator is smaller than the given value.
		if (argument >= value) {
			throw
			InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				getArgumentName(),
				argument,
				"is not smaller than " + value
			);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws BiggerArgumentException
	 * if the argument of the current {@link LongMediator} is not smaller than or does not equal the given value.
	 */
	public void isSmallerThanOrEquals(final long value) {
		if (argument > value) {
			throw BiggerArgumentException.forArgumentNameAndArgumentAndMax(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 *  @throws UnrepresentingArgumentException
	 *  if the argument of the current {@link LongMediator} does not represent a boolean.
	 */
	public final void representsBoolean() {
		
		//Asserts that the argument of the current LongArgument represents a boolean.
		if (argument != 0 && argument != 1) {
			throw
			UnrepresentingArgumentException.forArgumentNameAndArgumentAndType(
				getArgumentName(),
				getArgument(),
				Boolean.class
			);
		}
	}
	
	//method
	/**
	 * @return the argument of this long mediator.
	 */
	protected long getArgument() {
		return argument;
	}
}
