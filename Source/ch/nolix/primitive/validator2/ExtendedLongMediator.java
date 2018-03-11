//package declaration
package ch.nolix.primitive.validator2;

import ch.nolix.primitive.invalidArgumentException.EmptyArgumentException;
import ch.nolix.primitive.invalidArgumentException.NullArgumentException;

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
	 * Creates new extended long mediator for the given argument.
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
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	public LongMediator thatIsNamed(final String argumentName) {
		return new LongMediator(argumentName, getArgument());
	}
}
