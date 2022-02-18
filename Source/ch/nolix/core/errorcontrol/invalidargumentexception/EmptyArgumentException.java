//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * An empty argument exception is an exception that is intended to be thrown when an argument is undesired empty.
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
	 * Creates a new empty argument exception for the given argument.
	 * 
	 * @param argument
	 * @throws IllegalArgumentException if the given argument is null.
	 */
	public EmptyArgumentException(final Object argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new empty argument exception for an argument that has given argumentName.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws IllegalArgumentException if the given argumentName is null.
	 */
	public EmptyArgumentException(final String argumentName, final Object argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
}
