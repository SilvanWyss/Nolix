//package declaration
package ch.nolix.core.invalidArgumentException;

//class
/**
 * A false argument exception is an argument exception
 * that is supposed to be thrown when an argument is undesired false.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 40
 */
@SuppressWarnings("serial")
public final class FalseArgumentException extends InvalidArgumentException {

	//constant
	private static final String ERROR_PREDICATE = "is false";
	
	//constructor
	/**
	 * Creates new false argument exception.
	 */
	public FalseArgumentException() {
		
		//Calls constructor of the base class.
		super(new ErrorPredicate(ERROR_PREDICATE));
	}
	
	//constructor
	/**
	 * Creates new false argument exception
	 * for an argument with the given argument name.
	 * 
	 * @param argumentName
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public FalseArgumentException(final String argumentName) {
		
		//Calls constructor of the base class.
		super(new ArgumentName(
			argumentName),
			new ErrorPredicate(ERROR_PREDICATE)
		);
	}
}
