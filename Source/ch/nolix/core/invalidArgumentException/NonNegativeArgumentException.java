//package declaration
package ch.nolix.core.invalidArgumentException;

//class
/**
 * A non negative argument exception is an exception that is intended to be thrown when an argument is undesired not negative.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 70
 */
@SuppressWarnings("serial")
public final class NonNegativeArgumentException extends InvalidArgumentException {

	//constant
	private static final String ERROR_PREDICATE = "is negative";
	
	//constructor
	/**
	 * Creates a new non negative argument exception for the given argument.
	 * 
	 * @param argument
	 */
	public NonNegativeArgumentException(final double argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new non negative argument exception for the given argument.
	 * 
	 * @param argument
	 */
	public NonNegativeArgumentException(final long argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new non negative argument exception for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public NonNegativeArgumentException(final String argumentName, final double argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new non negative argument exception for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public NonNegativeArgumentException(final String argumentName, final long argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
}
