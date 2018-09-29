//package declaration
package ch.nolix.core.invalidArgumentException;

//class
/**
 * A not null argument exception is an argument exception that is intended to be thrown when an argument is undesired not null.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 40
 */
@SuppressWarnings("serial")
public class NotNullArgumentException extends InvalidArgumentException {

	//constant
	private static final String ERROR_PREDICATE = "is not null";

	//constructor
	/**
	 * Creates a new not null argument exception for the given argument.
	 * 
	 * @param argument
	 * @throws RuntimeException if the given argument is not an instance.
	 */
	public NotNullArgumentException(final Argument argument) {
		
		//Calls constructor of the base class.
		super(argument, new ErrorPredicate(ERROR_PREDICATE));
	}
	
	//constructor
	/**
	 * Creates a new not null argument exception for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws RuntimeException if the given argument name is not an instance.
	 * @throws RuntimeException if the given argument name is empty.
	 * @throws RuntimeException if the given argument is not an instance.
	 */
	public NotNullArgumentException(final ArgumentName argumentName, final Argument argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, new ErrorPredicate(ERROR_PREDICATE));
	}
}
