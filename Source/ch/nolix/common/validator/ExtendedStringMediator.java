//package declaration
package ch.nolix.common.validator;

import ch.nolix.common.invalidArgumentException.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentException.EmptyArgumentException;

//class
/**
* An extended string mediator is not mutable.
* 
* @author Silvan Wyss
* @month 2017-11
* @lines 40
*/
public class ExtendedStringMediator extends StringMediator {
	
	//constructor
	/**
	 * Creates a new extended string mediator for the given argument.
	 * 
	 * @param argument
	 */
	ExtendedStringMediator(final String value) {
		
		//Calls constructor of the base class.
		super(value);
	}

	//method
	/**
	 * @param argumentName
	 * @return a new string mediator
	 * for the argument of this extended string mediator with the given argument name.
	 * @throws ArgumentIsNullException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	public StringMediator thatIsNamed(final String argumentName) {
		return new StringMediator(argumentName, getRefArgument());
	}
}
