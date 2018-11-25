//package declaration
package ch.nolix.core.invalidArgumentException;

//class
/**
 * A negative argument exception is an exception that is intended to be thrown when an argument is undesired negative.
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 70
 */
@SuppressWarnings("serial")
public final class NegativeArgumentException extends InvalidArgumentException {
	
	//constant
	private static final String ERROR_PREDICATE = "is negative";
	
	//constructor
	/**
	 * Creates a new negative argument exception for the given argument.
	 * 
	 * @param argument
	 */
	public NegativeArgumentException(final double argument) {
		
		//Calls constructor of the base class.
		super(argument,ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new negative argument exception for the given argument.
	 * 
	 * @param argument
	 */
	public NegativeArgumentException(final long argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new negative argument exception for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public NegativeArgumentException(final String argumentName, final double argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new negative argument exception for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public NegativeArgumentException(final String argumentName, final long argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
}
