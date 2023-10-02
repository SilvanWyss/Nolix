//package declaration
package ch.nolix.core.errorcontrol.validator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsInRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EqualArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidPortException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonNegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.PositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnequalArgumentException;
import ch.nolix.core.independent.containerhelper.GlobalArrayHelper;
import ch.nolix.core.net.constant.PortCatalogue;

//class
/**
 * A {@link LongMediator} is a {@link Mediator} for a long argument.
 * A {@link LongMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 */
public class LongMediator extends Mediator {
	
	//static method
	/**
	 * @param argument
	 * @return a new {@link LongMediator} for the given argument.
	 */
	public static LongMediator forArgument(final long argument) {
		return new LongMediator(argument);
	}
	
	//static method
	/**
	 * @param argumentName
	 * @param argument
	 * @return a new {@link LongMediator} for the given argumentName and argument.
	 * @throws ArgumentIsNullException if the given argumentName is null.
	 * @throws InvalidArgumentException if the given argumentName is blank.
	 */
	public static LongMediator forArgumentNameAndArgument(final String argumentName, final long argument) {
		return new LongMediator(argumentName, argument);
	}
	
	//attribute
	private final long argument;
	
	//constructor
	/**
	 * Creates a new {@link LongMediator} for the given argument.
	 * 
	 * @param argument
	 */
	protected LongMediator(final long argument) {
		this.argument = argument;
	}
	
	//constructor
	/**
	 * Creates a new {@link LongMediator} for the given argumentName and argument.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws ArgumentIsNullException if the given argumentName is null.
	 * @throws InvalidArgumentException if the given argumentName is blank.
	 */
	private LongMediator(final String argumentName, final long argument) {
		
		super(argumentName);
		
		this.argument = argument;
	}
	
	//method
	/**
	 * @param min
	 * @param max
	 * @throws ArgumentIsOutOfRangeException if
	 * the argument of the current {@link LongMediator} is not between the given min and max.
	 */
	public final void isBetween(final long min, final long max) {
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
	 * @throws InvalidArgumentException if
	 * the argument of the current {@link LongMediator} is not bigger than the given value.
	 */
	public final void isBiggerThan(final long value) {
		if (argument <= value) {
			throw
			InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				getArgumentName(),
				argument,
				"is not bigger than " + value
			);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws SmallerArgumentException if
	 * the argument of the current {@link LongMediator} is not bigger than or does not equal the given value.
	 */
	public final void isBiggerThanOrEquals(final long value) {
		if (argument < value) {
			throw
			InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				getArgumentName(),
				argument,
				"is not bigger than or equal to " + value
			);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws UnequalArgumentException if
	 * the argument of the current {@link LongMediator} does not equal the given value.
	 */
	public final void isEqualTo(final long value) {
		if (argument != value) {
			throw UnequalArgumentException.forArgumentNameAndArgumentAndValue(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @param value
	 * @param values
	 * @throws InvalidArgumentException if
	 * the argument of the current {@link LongMediator} does not equal one of the given values.
	 */
	public final void isEqualToAny(final long value, final long... values) {
		
		if (argument == value) {
			return;
		}
		
		for (final long v : values) {
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
	 * @throws InvalidArgumentException if the argument of the current {@link LongMediator} is not negative.
	 */
	public final void isNegative() {
		if (argument >= 0) {
			throw NonNegativeArgumentException.forArgumentNameAndArgument(getArgumentName(), argument);
		}
	}
	
	//method
	/**
	 * @param min
	 * @param max
	 * @throws InvalidArgumentException if
	 * the argument of the current {@link LongMediator} is between the given min and max.
	 */
	public final void isNotBetween(final long min, final long max) {
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
	 * @throws EqualArgumentException if the argument of the current {@link LongMediator} equals the given value.
	 */
	public final void isNotEqualTo(final long value) {
		if (argument == value) {
			throw EqualArgumentException.forArgumentNameAndArgumentAndEqualValue(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws BiggerArgumentException if
	 * the argument of the current {@link LongMediator} is bigger than the given value.
	 */
	public final void isNotBiggerThan(final long value) {
		if (argument > value) {
			throw BiggerArgumentException.forArgumentNameAndArgumentAndMax(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @throws NegativeArgumentException if the argument of htis {@link LongMediator} is negative.
	 */
	public final void isNotNegative() {
		if (argument < 0.0) {
			throw NegativeArgumentException.forArgumentNameAndArgument(getArgumentName(), argument);
		}
	}
	
	//method
	/**
	 * @throws PositiveArgumentException if the argument of the current {@link LongMediator} is positive.
	 */
	public final void isNotPositive() {
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
	 * @throws NonPositiveArgumentException if the argument of the current {@link LongMediator} is not positive.
	 */
	public final void isPositive() {
		if (argument < 1) {
			throw NonPositiveArgumentException.forArgumentNameAndArgument(getArgumentName(), argument);
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws InvalidArgumentException if
	 * the argument of the current {@link LongMediator} is not smaller than the given value.
	 */
	public final void isSmallerThan(final long value) {
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
	 * @throws BiggerArgumentException if
	 * the argument of the current {@link LongMediator} is not smaller than or does not equal the given value.
	 */
	public final void isSmallerThanOrEquals(final long value) {
		if (argument > value) {
			throw BiggerArgumentException.forArgumentNameAndArgumentAndMax(getArgumentName(), argument, value);
		}
	}
	
	//method
	/**
	 * @return the argument of the current {@link LongMediator}.
	 */
	protected final long getArgument() {
		return argument;
	}
}
