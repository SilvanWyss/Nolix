//package declaration
package ch.nolix.core.validator2;

//own imports
import ch.nolix.core.invalidArgumentException.NonBiggerArgumentException;
import ch.nolix.core.invalidArgumentException.NonNegativeArgumentException;
import ch.nolix.core.invalidArgumentException.NonPositiveArgumentException;
import ch.nolix.core.invalidArgumentException.NonSmallerArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//class
/**
 * A double container mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 110
 */
public final class MultiDoubleMediator extends MultiGenericArgumentMediator<Double> {
	
	//package-visible constructor
	/**
	 * Creates new double container mediator with the given arguments.
	 * 
	 * @param arguments
	 * @throws NullArgumentException if the given argument container is null.
	 */
	MultiDoubleMediator(final Iterable<Double> arguments) {
		
		//Calls constructor of the base class.
		super(arguments);
	}
	
	public MultiDoubleMediator(double[] arguments) {
		this(ArrayHelper.createIterable(arguments));
	}

	//method
	/**
	 * @param value
	 * @throws NonNBiggerArgumentExceotion
	 * if one of the arguments of this double container mediator is not bigger than the given value.
	 */
	public void areBiggerThan(final double value) {
		
		//Iterates the arguments of this double container mediator.
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
	 * @throws NonNegativeArgumentExceotion
	 * if one of the arguments of this double container mediator is not positive.
	 */
	public void areNegative() {
	
		//Iterates the arguments of this double container mediator.
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
	 * @throws NonPositiveArgumentExceotion
	 * if one of the arguments of this double container mediator is not positive.
	 */
	public void arePositive() {
		
		//Iterates the arguments of this double container mediator.
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
	 * @param value
	 * @throws NonSmallerArgumentException
	 * if one of the argument of this double container mediator is not smaller than the given value.
	 */
	public void areSmallerThan(final double value) {
		
		//Iterates the arguments of this double container mediator.
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
