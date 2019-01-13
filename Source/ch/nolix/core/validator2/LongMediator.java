//package declaration
package ch.nolix.core.validator2;

//own imports
import ch.nolix.core.invalidArgumentException.BiggerArgumentException;
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.EqualArgumentException;
import ch.nolix.core.invalidArgumentException.InRangeArgumentException;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidArgumentException.NegativeArgumentException;
import ch.nolix.core.invalidArgumentException.NonBiggerArgumentException;
import ch.nolix.core.invalidArgumentException.NonNegativeArgumentException;
import ch.nolix.core.invalidArgumentException.NonPositiveArgumentException;
import ch.nolix.core.invalidArgumentException.NonSmallerArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;
import ch.nolix.core.invalidArgumentException.OutOfRangeArgumentException;
import ch.nolix.core.invalidArgumentException.PositiveArgumentException;
import ch.nolix.core.invalidArgumentException.SmallerArgumentException;
import ch.nolix.core.invalidArgumentException.UnequalArgumentException;
import ch.nolix.core.primitiveHelper.ArrayHelper;

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

	//package-visible constructor
	/**
	 * Creates a new long mediator for the given argument with the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws NullArgumentException if the given argument name is null.
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
	 * @return a new {@link LongTerminalMediator} for the argument of the current {@link LongMediator}.
	 * @throws UnequalArgumentException
	 * if the argument of this long mediator does not equal the given value.
	 */
	public LongTerminalMediator isEqualTo(final long value) {
		
		//Checks if the argument of this long mediator equals the given value.
		if (argument != value) {
			throw new UnequalArgumentException(getArgumentName(), argument, value);
		}
		
		return new LongTerminalMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @param values
	 * @return a new {@link LongTerminalMediator} for the argument of the current {@link LongMediator}.
	 * @throws InvalidArgumentException
	 * if the argument of this long mediator does not equal one of the given values.
	 */
	public LongTerminalMediator isEqualToAny(final long... values) {
		
		//Iterates the given values.
		for (final long v : values) {
			
			//Checks if the argument of this long mediator equals the current value.
			if (argument == v) {
				return new LongTerminalMediator(getArgumentName(), argument);
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
	 * @return a new {@link LongTerminalMediator} for the argument of the current {@link LongMediator}.
	 * @throws OutOfRangeArgumentException
	 * if the argument of this long mediator is not between the given min and max.
	 */
	public LongTerminalMediator isBetween(final long min, final long max) {
		
		//Checks if the argument of this long mediator
		//is between the given min and max.
		if (argument < min || argument > max) {
			throw new OutOfRangeArgumentException(
				getArgumentName(),
				argument,
				min,
				max
			);
		}
		
		return new LongTerminalMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @param value
	 * @return a new {@link LongTerminalMediator} for the argument of the current {@link LongMediator}.
	 * @throws NonBiggerArgumentException
	 * if the argument of this long mediator is not bigger than the given value.
	 */
	public LongTerminalMediator isBiggerThan(final long value) {
		
		//Checks if the argument of this long mediator is bigger than the given value.
		if (argument <= value) {
			throw new NonBiggerArgumentException(getArgumentName(), argument, value);
		}
		
		return new LongTerminalMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @param value
	 * @return a new {@link LongTerminalMediator} for the argument of the current {@link LongMediator}.
	 * @throws SmallerArgumentException
	 * if the argument of this long mediator
	 * is not bigger than or does not equal the given value.
	 */
	public LongTerminalMediator isBiggerThanOrEquals(final long value) {
		
		//Checks if the argument of this long mediator
		//is bigger than or equals the given value.
		if (argument < value) {
			throw new SmallerArgumentException(getArgumentName(), argument, value);
		}
		
		return new LongTerminalMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @return a new {@link LongTerminalMediator} for the argument of the current {@link LongMediator}.
	 * @throws NonNegativeArgumentException
	 * if the argument of this long mediator is not negative.
	 */
	public LongTerminalMediator isNegative() {
		
		//Checks if the argument of this long mediator is negative.
		if (argument >= 0) {
			throw new NonNegativeArgumentException(getArgumentName(), argument);
		}
		
		return new LongTerminalMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @param min
	 * @param max
	 * @return a new {@link LongTerminalMediator} for the argument of the current {@link LongMediator}.
	 * @throws InRangeArgumentException
	 * if the argument of this long mediator is between the given min and max.
	 */
	public LongTerminalMediator isNotBetween(final int min, final int max) {
		
		//Checks if the argument of this long mediator
		//is not between the given min and max.
		if (argument >= min && argument <= max) {
			throw new InRangeArgumentException(
				getArgumentName(),
				argument,
				min,
				max
			);
		}
		
		return new LongTerminalMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @param value
	 * @return a new {@link LongTerminalMediator} for the argument of the current {@link LongMediator}.
	 * @throws EqualArgumentExcetpion
	 * if the argument of this long mediator equals the given value.
	 */
	public LongTerminalMediator isNotEqualTo(final long value) {
		
		//Checks if the argument of this long mediator does not equals the given value.
		if (argument == value) {
			throw new EqualArgumentException(getArgumentName(), argument, value);
		}
		
		return new LongTerminalMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @param value
	 * @return a new {@link LongTerminalMediator} for the argument of the current {@link LongMediator}.
	 * @throws BiggerArgumentException
	 * if the argument of this long mediator is bigger than the given value.
	 */
	public LongTerminalMediator isNotBiggerThan(final long value) {
		
		//Checks if the argument of this long mediator is not bigger than the given value.
		if (argument > value) {
			throw new BiggerArgumentException(getArgumentName(), argument, value);
		}
		
		return new LongTerminalMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @return a new {@link LongTerminalMediator} for the argument of the current {@link LongMediator}.
	 * @throws NegativeArgumentException
	 * if the argument of htis long mediator is negative.
	 */
	public LongTerminalMediator isNotNegative() {
		
		//Checks if the argument of this long mediator is not negative.
		if (argument < 0.0) {
			throw new NegativeArgumentException(getArgumentName(), argument);
		}
		
		return new LongTerminalMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @return a new {@link LongTerminalMediator} for the argument of the current {@link LongMediator}.
	 * @throws PositiveArgumentException
	 * if the argument of this long mediator is positive.
	 */
	public LongTerminalMediator isNotPositive() {
		
		//Checks if the argument of this long mediator is not positive.
		if (argument > 0) {
			throw new PositiveArgumentException(getArgumentName(), argument);
		}
		
		return new LongTerminalMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @return a new {@link LongTerminalMediator} for the argument of the current {@link LongMediator}.
	 * @param SmallerArgumentException
	 * if the argument of this long mediator is smaller than the given value.
	 */
	public LongTerminalMediator isNotSmallerThan(final long value) {
	
		//Checks if the argument of this long mediator is not smaller than the given value.
		if (argument < value) {
			throw new SmallerArgumentException(getArgumentName(), argument, value);
		}
		
		return new LongTerminalMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @return a new {@link LongTerminalMediator} for the argument of the current {@link LongMediator}.
	 * @throws NonPositiveArgumentException
	 * if the argument of this long mediator is not positive.
	 */
	public LongTerminalMediator isPositive() {
		
		//Checks if the argument of this long mediator is positive.
		if (argument < 1) {
			throw new NonPositiveArgumentException(getArgumentName(), argument);
		}
		
		return new LongTerminalMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @param value
	 * @return a new {@link LongTerminalMediator} for the argument of the current {@link LongMediator}.
	 * @throws NonSmallerArgumentException
	 * if the argument of this long mediator is not smaller than the given value.
	 */
	public LongTerminalMediator isSmallerThan(final long value) {
		
		//Checks if the argument of this long mediator is smaller than the given value.
		if (argument >= value) {
			throw new NonSmallerArgumentException(getArgumentName(), argument, value);
		}
		
		return new LongTerminalMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @return a new {@link LongTerminalMediator} for the argument of the current {@link LongMediator}.
	 * @throws UnequalArgumentException
	 * if the argument of this long mediator is not 0.
	 */
	public LongTerminalMediator isZero() {
		
		isEqualTo(0);
		
		return new LongTerminalMediator(getArgumentName(), argument);
	}
	
	//method
	/**
	 * @return the argument of this long mediator.
	 */
	protected long getArgument() {
		return argument;
	}
}
