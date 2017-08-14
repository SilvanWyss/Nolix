//package declaration
package ch.nolix.core.validator2;

//own imports
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//class
/**
 * A string container mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 50
 */
public final class StringContainerMediator extends ArgumentContainerMediator<String> {

	//package-visible constructor
	/**
	 * Creates new string container mediator with the given arguments.
	 * 
	 * @param arguments
	 * @throws NullArgumentException if the given argument container is null.
	 */
	StringContainerMediator(final Iterable<String> arguments) {
		
		//Calls constructor of the base class.
		super(arguments);
	}
	
	//package-visible constructor
	/**
	 * Creates new string container mediator with the given arguments.
	 * 
	 * @param arguments
	 * @throws NullArgumentException if the given argument container is null.
	 */
	StringContainerMediator(final String[] arguments) {
		
		//Calls method of the base class.
		super(arguments);
	}

	//method
	/**
	 * @throws NullArgumentException if one of the arguments of this strinc container mediator is null.
	 * @throws EmptyArgumentException if one of the arguments of this string container mediator is empty.
	 */
	public void areNotEmpty() {
		
		//Checks if the arguments of this string container mediator are not null.
		areNotNull();
		
		//Iterates the arguments of this string container mediator.
		int index = 1;
		for (final String a : getRefArguments()) {
						
			//Checks if the current argument is not empty.
			if (a.isEmpty()) {
				throw new EmptyArgumentException(new ArgumentName(index + "th argument"));
			}
			
			//Increments index.
			index++;
		}
	}
}
