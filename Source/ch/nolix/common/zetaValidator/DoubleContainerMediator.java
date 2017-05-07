//package declaration
package ch.nolix.common.zetaValidator;

//own imports
import ch.nolix.common.invalidArgumentException.NonBiggerArgumentException;
import ch.nolix.common.invalidArgumentException.NonNegativeArgumentException;
import ch.nolix.common.invalidArgumentException.NonPositiveArgumentException;
import ch.nolix.common.invalidArgumentException.NullArgumentException;

//class
/**
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 80
 */
public final class DoubleContainerMediator extends ElementContainerMediator<Double> {
	
	//package-visible constructor
	/**
	 * Creates new double container mediator with the given arguments.
	 * 
	 * @param arguments
	 * @throws NullArgumentException if the given arguments is null.
	 */
	DoubleContainerMediator(final Iterable<Double> arguments) {
		
		//Calls constructor of the base class.
		super(arguments);
	}
	
	//method
	/**
	 * @param value
	 * @throws NonNBiggerArgumentExceotion if one of the arguments of this double container mediator is not bigger than the given value.
	 */
	public void areBiggerThan(final double value) {
		
		//Iterates through the arguments of this double container mediator.
		int i = 1;
		for (double a: getRefArguments()) {
			
			//Checks if the current argument is bigger than the given value.
			if (a <= value) {
				throw new NonBiggerArgumentException(i + "th", a, value);
			}
			
			i++;
		}
	}
	
	//method
	/**
	 * @throws NonNegativeArgumentExceotion if one of the arguments of this double container mediator is not positive.
	 */
	public void areNegative() {
	
		//Iterates through the arguments of this double container mediator.
		int i = 1;
		for (double a: getRefArguments()) {
			
			//Checks if the current arguemnt is negative.
			if (a > 0) {
				throw new NonNegativeArgumentException(i + "th", a);
			}
			
			i++;
		}
	}

	//method
	/**
	 * @throws NonPositiveArgumentExceotion if one of the arguments of this double container mediator is not positive.
	 */
	public void arePositive() {
		
		//Iterates through the arguments of this double container mediator.
		int i = 1;
		for (double a: getRefArguments()) {
			
			//Checks if the current argument is positive.
			if (a <= 0) {
				throw new NonPositiveArgumentException(i + "th", a);
			}
			
			i++;
		}
	}
}
