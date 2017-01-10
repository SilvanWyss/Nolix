//package declaration
package ch.nolix.common.exception;

//class
/**
 * A true argument exception is an argument exception that is intended to be thrown when an argument is undesired true.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 40
 */
@SuppressWarnings("serial")
public class TrueArgumentException extends ArgumentException {

	//constant
	private final static String ERROR_PREDICATE = "is true";
	
	//constructor
	/**
	 * Creates new true argument exception.
	 */
	public TrueArgumentException() {
		
		//Calls constructor of the base class.
		super(new ErrorPredicate(ERROR_PREDICATE));
	}
	
	//constructor
	/**
	 * Creates new true argument exception for an argument with the given argument name.
	 * 
	 * @param argumentName
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public TrueArgumentException(final String argumentName) {
		
		//Calls constructor of the base class.
		super(new ArgumentName(argumentName), new ErrorPredicate(ERROR_PREDICATE));
	}
}
