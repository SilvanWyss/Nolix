//package declaration
package ch.nolix.core.errorcontrol.validator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;

//class
/**
* An extended string mediator is not mutable.
* 
* @author Silvan Wyss
* @date 2017-11-12
* @lines 40
*/
public class ExtendedStringMediator extends StringMediator {
	
	//constructor
	/**
	 * Creates a new extended string mediator for the given argument.
	 * 
	 * @param value
	 */
	ExtendedStringMediator(final String value) {
		
		//Calls constructor of the base class.
		super(value);
	}
	
	//method
	/**
	 * @param type
	 * @return a new {@link StringMediator} for the argument of the current {@link ExtendedStringMediator}.
	 */
	public final StringMediator thatIsNamed(final Class<?> type) {
		return new StringMediator(type.getSimpleName(), getRefArgument());
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
