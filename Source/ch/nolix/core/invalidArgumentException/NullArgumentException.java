//package declaration
package ch.nolix.core.invalidArgumentException;

//class
/**
 * A null argument exception is an exception that is intended to be thrown when an argument is undesired null.
 * 
 * @author Silvan Wyss
 * @month 2016-04
 * @lines 40
 */
@SuppressWarnings("serial")
public final class NullArgumentException extends InvalidArgumentException {

	//constant
	private static final String ERROR_PREDICATE = "is null";
	
	//constructor
	/**
	 * Creates new null argument exception.
	 */
	public NullArgumentException() {
		
		//Calls constructor of the base class.
		super(new ErrorPredicate(ERROR_PREDICATE));
	}
	
	//constructor
	/**
	 * Creates new null argument name exception for an argument with the given argument name.
	 * 
	 * @param argumentName
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public NullArgumentException(final String argumentName) {
		
		//Calls constructor of the base class.
		super(new ArgumentName(argumentName), new ErrorPredicate(ERROR_PREDICATE));
	}
}
