//package declaration
package ch.nolix.primitive.invalidArgumentException;

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
	private static final String ERROR_PREDICATE = "is empty";

	//constructor
	/**
	 * Creates a new empty argument exception.
	 */
	public EmptyArgumentException() {
		
		//Calls constructor of the base class.
		super(new ErrorPredicate(ERROR_PREDICATE));
	}
	
	//constructor
	/**
	 * Creates a new empty argument exception for the given argument.
	 * 
	 * @param argument
	 * @throws RuntimeException if the given argument is not an instance.
	 */
	public EmptyArgumentException(final Argument argument) {
		
		//Calls constructor of the base class.
		super(argument, new ErrorPredicate(ERROR_PREDICATE));
	}
	
	//constructor
	/**
	 * Creates a new empty argument exception for an argument that has given argument name.
	 * 
	 * @param argumentName
	 * @throws RuntimeException if the given argument name is not an instance.
	 */
	public EmptyArgumentException(final ArgumentName argumentName) {
		
		//Calls constructor of the base class.
		super(argumentName, new ErrorPredicate(ERROR_PREDICATE));
	}
}
