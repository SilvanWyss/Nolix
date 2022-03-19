//package declaration
package ch.nolix.core.errorcontrol.validator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;

//class
/**
* An extended long mediator is not mutable.
* 
* @author Silvan Wyss
* @date 2017-11-12
* @lines 40
*/
public class ExtendedLongMediator extends LongMediator {

	//constructor
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
