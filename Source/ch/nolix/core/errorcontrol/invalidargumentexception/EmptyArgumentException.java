//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link EmptyArgumentException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument is undesirably empty.
 * 
 * @author Silvan Wyss
 * @date 2016-03-01
 */
@SuppressWarnings("serial")
public final class EmptyArgumentException extends InvalidArgumentException {
	
	//constant
	private static final String ERROR_PREDICATE = "is empty";
	
	//constructor
	/**
	 * Creates a new {@link EmptyArgumentException} for the given argument.
	 * 
	 * @param argument
	 */
	public EmptyArgumentException(final Object argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link EmptyArgumentException} for the given argumentName and argument.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws IllegalArgumentException if the given argumentName is null.
	 * @throws IllegalArgumentException if the given argumentName is blank.
	 */
	public EmptyArgumentException(final String argumentName, final Object argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
}
