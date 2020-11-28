//package declaration
package ch.nolix.common.invalidargumentexception;

//class
/**
 * A {@link NonNegativeArgumentException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when an argument is undesirably not negative.
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
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is empty.
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
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is empty.
	 */
	public NonNegativeArgumentException(final String argumentName, final long argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
}
