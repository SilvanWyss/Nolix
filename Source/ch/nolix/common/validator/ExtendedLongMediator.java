//package declaration
package ch.nolix.common.validator;

import ch.nolix.common.invalidArgumentExceptions.EmptyArgumentException;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;

//class
/**
* An extended long mediator is not mutable.
* 
* @author Silvan Wyss
* @month 2017-11
* @lines 40
*/
public class ExtendedLongMediator extends LongMediator {

	//package-visible constructor
	/**
	 * Creates a new extended long mediator for the given argument.
	 * 
	 * @param argument
	 */
	ExtendedLongMediator(final long argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//method
	/**
	 * @param argumentName
	 * @return a new long mediator with the given argument name for the argument of this extended long mediator.
	 * @throws ArgumentIsNullException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	public LongMediator thatIsNamed(final String argumentName) {
		return new LongMediator(argumentName, getArgument());
	}
}
