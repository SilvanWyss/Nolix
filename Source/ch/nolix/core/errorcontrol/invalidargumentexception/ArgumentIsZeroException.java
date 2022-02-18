//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A zero argument exception is an exception that is intended to be thrown when an argument is undesired zero.
 * 
 * @author Silvan Wyss
 * @date 2016-03-01
 */
@SuppressWarnings("serial")
public final class ArgumentIsZeroException extends InvalidArgumentException {
	
	//constant
	private static final String ERROR_PREDICATE = "is 0";
	
	//constructor
	/**
	 * Creates a new zero argument exception for the given argument.
	 * 
	 * @param argument
	 */
	public ArgumentIsZeroException(final long argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new zero argument exception for the given argument, that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is empty.
	 */
	public ArgumentIsZeroException(final String argumentName, final double argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new zero argument exception for the given argument, that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is empty.
	 */
	public ArgumentIsZeroException(final String argumentName, final long argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
}
