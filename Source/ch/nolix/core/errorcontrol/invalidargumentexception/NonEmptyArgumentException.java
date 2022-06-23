//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link NonEmptyArgumentException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument is undesirably not (!) empty.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 */
@SuppressWarnings("serial")
public final class NonEmptyArgumentException extends InvalidArgumentException {
	
	//constant
	private static final String ERROR_PREDICATE = "is not empty";
	
	//constructor
	/**
	 * Creates a new {@link NonEmptyArgumentException} for the given argument.
	 * 
	 * @param argument
	 */
	public NonEmptyArgumentException(final Object argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link NonEmptyArgumentException} for the given argumentName and argument.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws IllegalArgumentException if the given argumentName is null.
	 * @throws IllegalArgumentException if the given argumentName is empty.
	 */
	public NonEmptyArgumentException(final String argumentName, final Object argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
}
