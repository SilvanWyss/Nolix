//package declaration
package ch.nolix.common.invalidArgumentExceptions;

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
public final class ArgumentIsTrueException extends InvalidArgumentException {

	//constant
	private static final String ERROR_PREDICATE = "is true";
	
	//constructor
	/**
	 * Creates a new true argument exception.
	 */
	public ArgumentIsTrueException(final Object argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
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
	public ArgumentIsTrueException(final Object argument, final String argumentName) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
}
