//package declaration
package ch.nolix.core.validator2;

//own imports
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//class
/**
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 40
 */
public final class StringContainerMediator extends ElementContainerMediator<String> {

	// package-visible constructor
	/**
	 * Creates new string container mediator with the given arguments.
	 * 
	 * @param arguments
	 * @throws NullArgumentException if the given arguments is null.
	 */
	StringContainerMediator(final Iterable<String> arguments) {
		
		//Calls constructor of the base class.
		super(arguments);
	}
	
	//method
	/**
	 * @throws EmptyArgumentException if one of the arguments of htis string container mediator is an empty string.
	 */
	public void areNotEmpty() {
		
		//Iterates through the arguments of this string container mediator.
		int i = 1;
		for (String a: getRefArguments()) {
			
			//Checks if the current argument is an empty string.
			if (a.isEmpty()) {
				throw new EmptyArgumentException(new ArgumentName(i + "th argument"));
			}
			
			i++;
		}
	}
}
