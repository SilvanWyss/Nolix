//package declaration
package ch.nolix.common.exception;

//class
/**
 * A zero argument exception is an exception that is intended to be thrown when an argument is undesired zero.
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 40
 */
@SuppressWarnings("serial")
public final class ZeroArgumentException extends ArgumentException {
	
	//constant
	private final static String ERROR_PREDICATE = "is 0";
	
	//constructor
	/**
	 * Creates new zero argument exception.
	 */
	public ZeroArgumentException() {
		
		//Calls constructor of the base class.
		super(new ErrorPredicate(ERROR_PREDICATE));
	}
	
	//constructor
	/**
	 * Creates new zero argument exception for an argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public ZeroArgumentException(final String argumentName) {
		
		//Calls constructor of the base class.
		super(new ArgumentName(argumentName), new ErrorPredicate(ERROR_PREDICATE));
	}
}
