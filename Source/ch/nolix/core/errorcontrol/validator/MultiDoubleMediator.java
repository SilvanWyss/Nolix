//package declaration
package ch.nolix.core.errorcontrol.validator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonNegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonSmallerArgumentException;
import ch.nolix.core.independent.independenthelper.CentralArrayHelper;

//class
/**
 * A multi double mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 */
public final class MultiDoubleMediator extends MultiArgumentMediator<Double> {
	
	//constructor
	/**
	 * Creates a new multi double mediator for the given arguments.
	 * 
	 * @param arguments
	 * @throws ArgumentIsNullException if the given arguments is null.
	 */
	MultiDoubleMediator(final Iterable<Double> arguments) {
		
		//Calls constructor of the base class.
		super(arguments);
	}
	
	//constructor
	/**
	 * Creates a new multi double mediator for the given arguments.
	 * 
	 * @param arguments
	 * @throws ArgumentIsNullException if the given arguments is null.
	 */
	MultiDoubleMediator(final double[] arguments) {
		
		//Calls constructor of the base class.
		super(CentralArrayHelper.createIterable(arguments));
	}

	//method
	/**
	 * @param value
	 * @throws ArgumentIsNullException
	 * if one of the arguments of this multi double mediator is null.
	 * @throws InvalidArgumentException
	 * if one of the arguments of this multi double mediator is not bigger than the given value.
	 */
	public void areBiggerThan(final double value) {
		
		//Asserts that the arguments of this multi double mediator are not null.
		areNotNull();
		
		//Iterates the arguments of this multi double mediator.
		int i = 1;
		for (final double a : getRefArguments()) {
			
			//Asserts that the current argument is bigger than the given value.
			if (a <= value) {
				throw
				InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
					i + "th argument",
					a,
					"is not bigger than " + value
				);
			}
			
			//Increments index.
			i++;
		}
	}
	
	//method
	/**
	 * @throws ArgumentIsNullException
	 * if one of the arguments of this multi double mediator is null.
	 * @throws NonNegativeArgumentException
	 * if one of the arguments of this multi double mediator is not positive.
	 */
	public void areNegative() {
		
		//Asserts that the arguments of this multi double mediator are not null.
		areNotNull();
	
		//Iterates the arguments of this multi double mediator.
		int i = 1;
		for (final double a : getRefArguments()) {
			
			//Asserts that the current arguemnt is negative.
			if (a > 0) {
				throw NonNegativeArgumentException.forArgumentNameAndArgument(i + "th argument", a);
			}
			
			//Increments index.
			i++;
		}
	}

	//method
	/**
	 * @throws ArgumentIsNullException
	 * if one of the arguments of this multi double mediator is null.
	 * @throws NonPositiveArgumentException
	 * if one of the arguments of this multi double mediator is not positive.
	 */
	public void arePositive() {
		
		//Asserts that the arguments of this multi double mediator are not null.
		areNotNull();
		
		//Iterates the arguments of this multi double mediator.
		int i = 1;
		for (final double a : getRefArguments()) {
			
			//Asserts that the current argument is positive.
			if (a <= 0) {
				throw NonPositiveArgumentException.forArgumentNameAndArgument(i + "th argument", a);
			}
			
			//Increments index.
			i++;
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws ArgumentIsNullException if one of the arguments of this multi double mediator is null.
	 * @throws NonSmallerArgumentException
	 * if one of the argument of this multi double mediator is not smaller than the given value.
	 */
	public void areSmallerThan(final double value) {
		
		//Asserts that the arguments of this multi double mediator are not null.
		areNotNull();
		
		//Iterates the arguments of this multi double mediator.
		int i = 1;
		for (final double a : getRefArguments()) {
			
			//Asserts that the current argument is smaller than the given value.
			if (a >= value) {
				throw new NonSmallerArgumentException(i + "th argument", a, value);
			}
			
			//Increments index.
			i++;
		}
	}
}
