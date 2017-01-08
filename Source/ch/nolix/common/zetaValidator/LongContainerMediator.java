//package declaration
package ch.nolix.common.zetaValidator;

//own imports
import ch.nolix.common.exception.NonPositiveArgumentException;
import ch.nolix.common.exception.NullArgumentException;

//class
/**
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 40
 */
public final class LongContainerMediator extends ElementContainerMediator<Long> {


	//package-visible constructor
	/**
	 * Creates new long container mediator with the given arguments.
	 * 
	 * @param arguments
	 * @throws NullArgumentException if the given arguments is null.
	 */
	LongContainerMediator(final Iterable<Long> arguments) {
		
		//Calls constructor of the base class.
		super(arguments);
	}
	
	//method
	/**
	 * @throws NonPositiveArgumentException if one of the arguments of this long container mediator is not positive.
	 */
	public void arePositive() {
	
		//Iterates through the arguments of this long container mediator.
		int i = 1;
		for (long a: getRefArguments()) {
			
			//Checks if the current argument is positive.
			if (a <= 0) {
				throw new NonPositiveArgumentException(i + "th", a);
			}
			
			i++;
		}
	}
}
