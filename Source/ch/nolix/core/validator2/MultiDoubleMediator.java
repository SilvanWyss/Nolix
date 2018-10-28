//package declaration
package ch.nolix.core.validator2;

import ch.nolix.core.invalidArgumentException.NonBiggerArgumentException;
import ch.nolix.core.invalidArgumentException.NonNegativeArgumentException;
import ch.nolix.core.invalidArgumentException.NonPositiveArgumentException;
import ch.nolix.core.invalidArgumentException.NonSmallerArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;
import ch.nolix.core.primitiveHelper.ArrayHelper;

//class
/**
 * A multi double mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 150
 */
public final class MultiDoubleMediator extends MultiArgumentMediator<Double> {
	
	//package-visible constructor
	/**
	 * Creates a new multi double mediator for the given arguments.
	 * 
	 * @param arguments
	 * @throws NullArgumentException if the given arguments is null.
	 */
	MultiDoubleMediator(final Iterable<Double> arguments) {
		
		//Calls constructor of the base class.
		super(arguments);
	}
	
	//package-visible constructor
	/**
	 * Creates a new multi double mediator for the given arguments.
	 * 
	 * @param arguments
	 * @throws NullArgumentException if the given arguments is null.
	 */
	MultiDoubleMediator(final double[] arguments) {
		
		//Calls constructor of the base class.
		super(ArrayHelper.createIterable(arguments));
	}

	//method
	/**
	 * @param value
	 * @throws NullArgumentException
	 * if one of the arguments of this multi double mediator is null.
	 * @throws NonNBiggerArgumentExceotion
	 * if one of the arguments of this multi double mediator is not bigger than the given value.
	 */
	public void areBiggerThan(final double value) {
		
		//Checks if the arguments of this multi double mediator are not null.
		areNotNull();
		
		//Iterates the arguments of this multi double mediator.
		int i = 1;
		for (final double a : getRefArguments()) {
			
			//Checks if the current argument is bigger than the given value.
			if (a <= value) {
				throw new NonBiggerArgumentException(i + "th argument", a, value);
			}
			
			//Increments index.
			i++;
		}
	}
	
	//method
	/**
	 * @throws NullArgumentException
	 * if one of the arguments of this multi double mediator is null.
	 * @throws NonNegativeArgumentExceotion
	 * if one of the arguments of this multi double mediator is not positive.
	 */
	public void areNegative() {
		
		//Checks if the arguments of this multi double mediator are not null.
		areNotNull();
	
		//Iterates the arguments of this multi double mediator.
		int i = 1;
		for (final double a : getRefArguments()) {
			
			//Checks if the current arguemnt is negative.
			if (a > 0) {
				throw new NonNegativeArgumentException(i + "th argument", a);
			}
			
			//Increments index.
			i++;
		}
	}

	//method
	/**
	 * @throws NullArgumentException
	 * if one of the arguments of this multi double mediator is null.
	 * @throws NonPositiveArgumentExceotion
	 * if one of the arguments of this multi double mediator is not positive.
	 */
	public void arePositive() {
		
		//Checks if the arguments of this multi double mediator are not null.
		areNotNull();
		
		//Iterates the arguments of this multi double mediator.
		int i = 1;
		for (final double a : getRefArguments()) {
			
			//Checks if the current argument is positive.
			if (a <= 0) {
				throw new NonPositiveArgumentException(i + "th argument", a);
			}
			
			//Increments index.
			i++;
		}
	}
	
	//method
	/**
	 * @throws NullArgumentException
	 * if one of the arguments of this multi double mediator is null.
	 * @param value
	 * @throws NonSmallerArgumentException
	 * if one of the argument of this multi double mediator is not smaller than the given value.
	 */
	public void areSmallerThan(final double value) {
		
		//Checks if the arguments of this multi double mediator are not null.
		areNotNull();
		
		//Iterates the arguments of this multi double mediator.
		int i = 1;
		for (final double a : getRefArguments()) {
			
			//Checks if the current argument is smaller than the given value.
			if (a >= value) {
				throw new NonSmallerArgumentException(i + "th argument", a, value);
			}
			
			//Increments index.
			i++;
		}
	}
}
