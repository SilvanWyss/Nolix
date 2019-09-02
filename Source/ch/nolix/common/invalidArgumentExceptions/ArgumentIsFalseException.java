//package declaration
package ch.nolix.common.invalidArgumentExceptions;

//class
/**
 * A false argument exception is an argument exception
 * that is supposed to be thrown when an argument is undesired false.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 40
 */
@SuppressWarnings("serial")
public final class ArgumentIsFalseException extends InvalidArgumentException {

	//constant
	private static final String ERROR_PREDICATE = "is false";
	
	//constructor
	/**
	 * Creates a new false argument exception.
	 */
	public ArgumentIsFalseException(final Object argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new false argument exception
	 * for an argument with the given argument name.
	 * 
	 * @param argumentName
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public ArgumentIsFalseException(final String argumentName, final Object argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
}
