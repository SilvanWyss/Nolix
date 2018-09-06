//package declaration
package ch.nolix.primitive.validator2;

import ch.nolix.primitive.helper.ArrayHelper;
import ch.nolix.primitive.invalidArgumentException.NonBiggerArgumentException;
import ch.nolix.primitive.invalidArgumentException.NonNegativeArgumentException;
import ch.nolix.primitive.invalidArgumentException.NonPositiveArgumentException;
import ch.nolix.primitive.invalidArgumentException.NonSmallerArgumentException;
import ch.nolix.primitive.invalidArgumentException.NullArgumentException;

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
	 * @throws NullArgumentException if the given arguments is not an instance.
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
	 * @throws NullArgumentException if the given arguments is not an instance.
	 */
	MultiDoubleMediator(final double[] arguments) {
		
		//Calls constructor of the base class.
		super(ArrayHelper.createIterable(arguments));
	}

	//method
	/**
	 * @param value
	 * @throws NullArgumentException
	 * if one of the arguments of this multi double mediator is not an instance.
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
	 * if one of the arguments of this multi double mediator is not an instance.
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
	 * if one of the arguments of this multi double mediator is not an instance.
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
	 * if one of the arguments of this multi double mediator is not an instance.
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
