//package declaration
package ch.nolix.core.validator2;

//own imports
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 40
 */
public final class NamedStringMediator extends NamedArgumentMediator<String> {

	//package-visible constructor
	/**
	 * Creates new named string mediator with the given argument name and argument.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is an empty string.
	 */
	NamedStringMediator(final String argumentName, final String argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument);
	}

	//method
	/**
	 * @throws EmptyArgumentException if the argument of this named string mediator is empty.
	 */
	public void isNotEmpty() {		
		
		//Checks if the argument of this named string mediator is not empty.
		if (getArgument().isEmpty()) {
			throw new EmptyArgumentException();
		}
	}
}
