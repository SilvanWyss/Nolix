//package declaration
package ch.nolix.core.invalidArgumentException;

//class
/**
 * A non empty argument exception is an argument exception
 * that is supposed to be thrown when an argument is undesired not empty.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 60
 */
@SuppressWarnings("serial")
public final class NonEmptyArgumentException extends InvalidArgumentException {

	//constant
	private static final String ERROR_PREDICATE = "is not empty";
	
	//constructor
	/**
	 * Creates a new non empty argument exception for the given argument.
	 * 
	 * @param argument
	 * @throws RuntimeException if the given argument is null.
	 */
	public NonEmptyArgumentException(final Object argument) {

		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new non empty argument exception for the given argument with the given argumet name.
	 * @param argumentName
	 * @param argument
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public NonEmptyArgumentException(final String argumentName, final Object argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
}
