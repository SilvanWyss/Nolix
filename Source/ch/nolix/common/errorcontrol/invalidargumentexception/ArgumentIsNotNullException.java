//package declaration
package ch.nolix.common.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link ArgumentIsNotNullException} is a {@link InvalidArgumentException}
 * that is intended to be thrown when an argument is undesirably not null.
 * 
 * @author Silvan Wyss
 * @date 2017-01-08
 * @lines 40
 */
@SuppressWarnings("serial")
public class ArgumentIsNotNullException extends InvalidArgumentException {

	//constant
	private static final String ERROR_PREDICATE = "is not null";

	//constructor
	/**
	 * Creates a new not null argument exception for the given argument.
	 * 
	 * @param argument
	 * @throws IllegalArgumentException if the given argument is null.
	 */
	public ArgumentIsNotNullException(final Object argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new not null argument exception for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is empty.
	 * @throws IllegalArgumentException if the given argument is null.
	 */
	public ArgumentIsNotNullException(final String argumentName, final Object argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
}
