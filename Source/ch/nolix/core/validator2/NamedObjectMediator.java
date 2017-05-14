//package declaration
package ch.nolix.core.validator2;

//own imports
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//class
/**
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 30
 */
public final class NamedObjectMediator extends NamedElementMediator<Object> {

	//package-visible constructor
	/**
	 * Creates new named object mediator with the given argument name and the given argument.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is an empty string.
	 */
	NamedObjectMediator(final String argumentName, final Object argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument);
	}
}
