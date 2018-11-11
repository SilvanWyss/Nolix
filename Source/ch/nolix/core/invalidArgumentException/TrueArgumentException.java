//package declaration
package ch.nolix.core.invalidArgumentException;

import ch.nolix.core.argument.ArgumentName;
import ch.nolix.core.argument.ErrorPredicate;

//class
/**
 * A true argument exception is an invalid argument exception
 * that is supposed to be thrown when an argument is undesired true.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 40
 */
@SuppressWarnings("serial")
public final class TrueArgumentException extends InvalidArgumentException {

	//constant
	private static final String ERROR_PREDICATE = "is true";
	
	//constructor
	/**
	 * Creates a new true argument exception.
	 */
	public TrueArgumentException() {
		
		//Calls constructor of the base class.
		super(new ErrorPredicate(ERROR_PREDICATE));
	}
	
	//constructor
	/**
	 * Creates a new true argument exception
	 * for an argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public TrueArgumentException(final String argumentName) {
		
		//Calls constructor of the base class.
		super(
			new ArgumentName(argumentName),
			new ErrorPredicate(ERROR_PREDICATE)
		);
	}
}
