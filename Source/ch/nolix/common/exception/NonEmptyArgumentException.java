//package declaration
package ch.nolix.common.exception;

//class
/**
 * A non empty argument exception is an argument exception that is intended to be thrown when an argument is undesired not empty.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 40
 */
@SuppressWarnings("serial")
public final class NonEmptyArgumentException extends ArgumentException {

	//constant
	private final static String PREDICATE = "is not empty";
	
	//constructor
	/**
	 * Creates new non empty argument exception for the given argument.
	 * 
	 * @param argument
	 * @throws RuntimeException if the given argument is null.
	 */
	public NonEmptyArgumentException(final Argument argument) {

		//Calls constructor of the base class.
		super(argument, new ErrorPredicate(PREDICATE));
	}
	
	//constructor
	/**
	 * Creates new non empty argument exception for an argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @throws RuntimeException if the given argument name is null.
	 */
	public NonEmptyArgumentException(final ArgumentName argumentName) {

		//Calls constructor of the base class.
		super(argumentName, new ErrorPredicate(PREDICATE));
	}
}
