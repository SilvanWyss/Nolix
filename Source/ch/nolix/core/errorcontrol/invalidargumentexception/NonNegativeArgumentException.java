//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link NonNegativeArgumentException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument is undesirably not (!) negative.
 * 
 * @author Silvan Wyss
 * @date 2016-12-01
 */
@SuppressWarnings("serial")
public final class NonNegativeArgumentException extends InvalidArgumentException {
	
	//constant
	private static final String ERROR_PREDICATE = "is not negative";
	
	//constructor
	/**
	 * Creates a new {@link NonNegativeArgumentException} for the given argumentName and argument.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws IllegalArgumentException if the given argumentName is null.
	 * @throws IllegalArgumentException if the given argumentName is blank.
	 */
	public NonNegativeArgumentException(final String argumentName, final double argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link NonNegativeArgumentException} for the given argumentName and argument.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws IllegalArgumentException if the given argumentName is null.
	 * @throws IllegalArgumentException if the given argumentName is blank.
	 */
	public NonNegativeArgumentException(final String argumentName, final long argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
}
