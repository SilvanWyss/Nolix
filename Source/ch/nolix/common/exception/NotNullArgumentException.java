//package declaration
package ch.nolix.common.exception;

//class
/**
 * A not null argument exception is an argument exception that is intended to be thrown when an argument is undesired not null.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 40
 */
@SuppressWarnings("serial")
public class NotNullArgumentException extends ArgumentException {

	//constant
	private final static String ERROR_PREDICATE = "is not null";

	//constructor
	/**
	 * Creates new not null argument exception for the given argument.
	 * 
	 * @param argument
	 * @throws RuntimeException if the given argument is null.
	 */
	public NotNullArgumentException(final Argument argument) {
		
		//Calls constructor of the base class.
		super(argument, new ErrorPredicate(ERROR_PREDICATE));
	}
	
	//constructor
	/**
	 * Creates new not null argument exception for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 * @throws RuntimeException if the given argument is null.
	 */
	public NotNullArgumentException(final ArgumentName argumentName, final Argument argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, new ErrorPredicate(ERROR_PREDICATE));
	}
}
