//package declaration
package ch.nolix.common.validator;

//own imports
import ch.nolix.common.invalidArgumentExceptions.EmptyArgumentException;
import ch.nolix.common.invalidArgumentExceptions.NegativeArgumentException;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentExceptions.UnequalArgumentException;

//class
/**
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 70
 */
public final class DoubleDeviationMediator extends Mediator {

	//attributes
	private final double argument;
	private final double maxDeviation;
	
	//constructor
	/**
	 * Creates a new named double deviation mediator with the given argument name, argument and max deviation.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param maxDeviation
	 * @throws ArgumentIsNullException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 * @throws NegativeArgumentException if the given max deviation is negative.
	 */
	DoubleDeviationMediator(
		final String argumentName,
		final Double argument, 
		final double maxDeviation) {
		
		//Calls constructor of the base class.
		super(argumentName);
		
		//Checks if the given max deviation is not negative.
		if (maxDeviation < 0.0) {
			throw new NegativeArgumentException("max deviation", maxDeviation);
		}
		
		this.argument = argument;
		this.maxDeviation = maxDeviation;
	}
	
	//method
	/**
	 * @param value
	 * @throws UnequalArgumentException if the argument of this named double deviation mediator does not equal the given value with a deviation that is not bigger than the max deviation of this named double deviation mediator.
	 */
	public void isEqualTo(final double value) {
		
		//Checks if the argument of this named double deviation mediator equals the given value with a deviation that is not bigger than the max deviation of this named double deviation mediator.
		if (Math.abs(value - argument) > maxDeviation) {
			throw new UnequalArgumentException(getArgumentName(), value, argument);
		}
	}
	
	//method
	/**
	 * @throws UnequalArgumentException if the argument of htis named double deviation mediator is not 0.0 with a deviation that is not bigger than the max deviatio of this named double deviation mediator.
	 */
	public void isZero() {
		isEqualTo(0.0);
	}
}
