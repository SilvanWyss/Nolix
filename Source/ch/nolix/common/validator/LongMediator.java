//package declaration
package ch.nolix.common.validator;

//own imports
import ch.nolix.common.independentHelper.ArrayHelper;
import ch.nolix.common.invalidArgumentException.ArgumentIsInRangeException;
import ch.nolix.common.invalidArgumentException.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentException.ArgumentIsOutOfRangeException;
import ch.nolix.common.invalidArgumentException.BiggerArgumentException;
import ch.nolix.common.invalidArgumentException.EmptyArgumentException;
import ch.nolix.common.invalidArgumentException.EqualArgumentException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.invalidArgumentException.NegativeArgumentException;
import ch.nolix.common.invalidArgumentException.NonBiggerArgumentException;
import ch.nolix.common.invalidArgumentException.NonNegativeArgumentException;
import ch.nolix.common.invalidArgumentException.NonPositiveArgumentException;
import ch.nolix.common.invalidArgumentException.NonSmallerArgumentException;
import ch.nolix.common.invalidArgumentException.PositiveArgumentException;
import ch.nolix.common.invalidArgumentException.SmallerArgumentException;
import ch.nolix.common.invalidArgumentException.UnequalArgumentException;
import ch.nolix.common.invalidArgumentException.UnrepresentingArgumentException;

//class
/**
 * A long mediator is a mediator for a long with a name.
 * A long mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 340
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
	public LongMediator(final long argument) {
		
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
	
	//method
	/**
	 * @param value
	 * @return a new {@link TerminalLongMediator} for the argument of the current {@link LongMediator}.
	 * @throws UnequalArgumentException
	 * if the argument of this long mediator does not equal the given value.
	 */
	public TerminalLongMediator isEqualTo(final long value) {
		
		//Asserts that the argument of this long mediator equals the given value.
		if (argument != value) {
			throw new UnequalArgumentException(getArgumentName(), argument, value);
		}
		
		return new TerminalLongMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @param values
	 * @return a new {@link TerminalLongMediator} for the argument of the current {@link LongMediator}.
	 * @throws InvalidArgumentException
	 * if the argument of this long mediator does not equal one of the given values.
	 */
	public TerminalLongMediator isEqualToAny(final long... values) {
		
		//Iterates the given values.
		for (final long v : values) {
			
			//Asserts that the argument of this long mediator equals the current value.
			if (argument == v) {
				return new TerminalLongMediator(getArgumentName(), argument);
			}
		}
		
		throw new InvalidArgumentException(
			getArgumentName(),
			argument,
			"does not equal one of {" + ArrayHelper.createString(values) + "}"
		);
	}
	
	//method
	/**
	 * @param min
	 * @param max
	 * @return a new {@link TerminalLongMediator} for the argument of the current {@link LongMediator}.
	 * @throws ArgumentIsOutOfRangeException
	 * if the argument of this long mediator is not between the given min and max.
	 */
	public TerminalLongMediator isBetween(final long min, final long max) {
		
		//Asserts that the argument of this long mediator
		//is between the given min and max.
		if (argument < min || argument > max) {
			throw new ArgumentIsOutOfRangeException(
				getArgumentName(),
				argument,
				min,
				max
			);
		}
		
		return new TerminalLongMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @param value
	 * @return a new {@link TerminalLongMediator} for the argument of the current {@link LongMediator}.
	 * @throws NonBiggerArgumentException
	 * if the argument of this long mediator is not bigger than the given value.
	 */
	public TerminalLongMediator isBiggerThan(final long value) {
		
		//Asserts that the argument of this long mediator is bigger than the given value.
		if (argument <= value) {
			throw new NonBiggerArgumentException(getArgumentName(), argument, value);
		}
		
		return new TerminalLongMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @param value
	 * @return a new {@link TerminalLongMediator} for the argument of the current {@link LongMediator}.
	 * @throws SmallerArgumentException
	 * if the argument of this long mediator
	 * is not bigger than or does not equal the given value.
	 */
	public TerminalLongMediator isBiggerThanOrEquals(final long value) {
		
		//Asserts that the argument of this long mediator
		//is bigger than or equals the given value.
		if (argument < value) {
			throw new SmallerArgumentException(getArgumentName(), argument, value);
		}
		
		return new TerminalLongMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @return a new {@link TerminalLongMediator} for the argument of the current {@link LongMediator}.
	 * @throws NonNegativeArgumentException
	 * if the argument of this long mediator is not negative.
	 */
	public TerminalLongMediator isNegative() {
		
		//Asserts that the argument of this long mediator is negative.
		if (argument >= 0) {
			throw new NonNegativeArgumentException(getArgumentName(), argument);
		}
		
		return new TerminalLongMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @param min
	 * @param max
	 * @return a new {@link TerminalLongMediator} for the argument of the current {@link LongMediator}.
	 * @throws ArgumentIsInRangeException
	 * if the argument of this long mediator is between the given min and max.
	 */
	public TerminalLongMediator isNotBetween(final int min, final int max) {
		
		//Asserts that the argument of this long mediator
		//is not between the given min and max.
		if (argument >= min && argument <= max) {
			throw new ArgumentIsInRangeException(
				getArgumentName(),
				argument,
				min,
				max
			);
		}
		
		return new TerminalLongMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @param value
	 * @return a new {@link TerminalLongMediator} for the argument of the current {@link LongMediator}.
	 * @throws EqualArgumentExcetpion
	 * if the argument of this long mediator equals the given value.
	 */
	public TerminalLongMediator isNotEqualTo(final long value) {
		
		//Asserts that the argument of this long mediator does not equals the given value.
		if (argument == value) {
			throw new EqualArgumentException(getArgumentName(), argument, value);
		}
		
		return new TerminalLongMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @param value
	 * @return a new {@link TerminalLongMediator} for the argument of the current {@link LongMediator}.
	 * @throws BiggerArgumentException
	 * if the argument of this long mediator is bigger than the given value.
	 */
	public TerminalLongMediator isNotBiggerThan(final int value) {
		return isNotBiggerThan((long)value);
	}
	
	//method
	/**
	 * @param value
	 * @return a new {@link TerminalLongMediator} for the argument of the current {@link LongMediator}.
	 * @throws BiggerArgumentException
	 * if the argument of this long mediator is bigger than the given value.
	 */
	public TerminalLongMediator isNotBiggerThan(final long value) {
		
		//Asserts that the argument of this long mediator is not bigger than the given value.
		if (argument > value) {
			throw new BiggerArgumentException(getArgumentName(), argument, value);
		}
		
		return new TerminalLongMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @return a new {@link TerminalLongMediator} for the argument of the current {@link LongMediator}.
	 * @throws NegativeArgumentException
	 * if the argument of htis long mediator is negative.
	 */
	public TerminalLongMediator isNotNegative() {
		
		//Asserts that the argument of this long mediator is not negative.
		if (argument < 0.0) {
			throw new NegativeArgumentException(getArgumentName(), argument);
		}
		
		return new TerminalLongMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @return a new {@link TerminalLongMediator} for the argument of the current {@link LongMediator}.
	 * @throws PositiveArgumentException
	 * if the argument of this long mediator is positive.
	 */
	public TerminalLongMediator isNotPositive() {
		
		//Asserts that the argument of this long mediator is not positive.
		if (argument > 0) {
			throw new PositiveArgumentException(getArgumentName(), argument);
		}
		
		return new TerminalLongMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @return a new {@link TerminalLongMediator} for the argument of the current {@link LongMediator}.
	 * @throws NonPositiveArgumentException
	 * if the argument of this long mediator is not positive.
	 */
	public TerminalLongMediator isPositive() {
		
		//Asserts that the argument of this long mediator is positive.
		if (argument < 1) {
			throw new NonPositiveArgumentException(getArgumentName(), argument);
		}
		
		return new TerminalLongMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @param value
	 * @return a new {@link TerminalLongMediator} for the argument of the current {@link LongMediator}.
	 * @throws NonSmallerArgumentException
	 * if the argument of this long mediator is not smaller than the given value.
	 */
	public TerminalLongMediator isSmallerThan(final long value) {
		
		//Asserts that the argument of this long mediator is smaller than the given value.
		if (argument >= value) {
			throw new NonSmallerArgumentException(getArgumentName(), argument, value);
		}
		
		return new TerminalLongMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 *  @return a new {@link TerminalLongMediator} for the argument of the current {@link LongMediator}.
	 *  @throws UnrepresentingArgumentException
	 *  if the argument of the current {@link LongMediator} does not represent a boolean.
	 */
	public final TerminalLongMediator representsBoolean() {
		
		//Asserts that the argument of the current LongArgument represents a boolean.
		if (argument != 0 && argument != 1) {
			throw new UnrepresentingArgumentException(getArgumentName(), getArgument(), Boolean.class);
		}
		
		return new TerminalLongMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @return the argument of this long mediator.
	 */
	protected long getArgument() {
		return argument;
	}
}
