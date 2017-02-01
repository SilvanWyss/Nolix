//package declaration
package ch.nolix.common.exception;

//class
/**
 * An empty argument exception is an exception that is intended to be thrown when an argument is undesired empty.
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 50
 */
@SuppressWarnings("serial")
public final class EmptyArgumentException extends InvalidArgumentException {

	//constant
	private final static String ERROR_PREDICATE = "is empty";

	//constructor
	/**
	 * Creates new empty argument exception.
	 */
	public EmptyArgumentException() {
		
		//Calls constructor of the base class.
		super(new ErrorPredicate(ERROR_PREDICATE));
	}
	
	//constructor
	/**
	 * Creates new empty argument exception for the given argument.
	 * 
	 * @param argument
	 * @throws RuntimeException if the given argument is null.
	 */
	public EmptyArgumentException(final Argument argument) {
		
		//Calls constructor of the base class.
		super(argument, new ErrorPredicate(ERROR_PREDICATE));
	}
	
	//constructor
	/**
	 * Creates new empty argument exception for an argument that has given argument name.
	 * 
	 * @param argumentName
	 * @throws RuntimeException if the given argument name is null.
	 */
	public EmptyArgumentException(final ArgumentName argumentName) {
		
		//Calls constructor of the base class.
		super(argumentName, new ErrorPredicate(ERROR_PREDICATE));
	}
}
