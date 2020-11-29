//package declaration
package ch.nolix.common.validator;

import ch.nolix.common.independenthelper.ArrayHelper;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.NonBiggerArgumentException;
import ch.nolix.common.invalidargumentexception.NonNegativeArgumentException;
import ch.nolix.common.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.common.invalidargumentexception.NonSmallerArgumentException;

//class
/**
 * A multi double mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 150
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
		super(ArrayHelper.createIterable(arguments));
	}

	//method
	/**
	 * @param value
	 * @throws ArgumentIsNullException
	 * if one of the arguments of this multi double mediator is null.
	 * @throws NonNBiggerArgumentExceotion
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
				throw new NonBiggerArgumentException(i + "th argument", a, value);
			}
			
			//Increments index.
			i++;
		}
	}
	
	//method
	/**
	 * @throws ArgumentIsNullException
	 * if one of the arguments of this multi double mediator is null.
	 * @throws NonNegativeArgumentExceotion
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
				throw new NonNegativeArgumentException(i + "th argument", a);
			}
			
			//Increments index.
			i++;
		}
	}

	//method
	/**
	 * @throws ArgumentIsNullException
	 * if one of the arguments of this multi double mediator is null.
	 * @throws NonPositiveArgumentExceotion
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
				throw new NonPositiveArgumentException(i + "th argument", a);
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
