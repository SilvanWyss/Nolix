//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link ArgumentIsNotNullException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument is undesirably not (!) null.
 * 
 * @author Silvan Wyss
 * @date 2017-01-08
 */
@SuppressWarnings("serial")
public final class ArgumentIsNotNullException extends InvalidArgumentException {
	
	//constant
	private static final String ERROR_PREDICATE = "is not null";
	
	//constructor
	/**
	 * Creates a {@link ArgumentIsNotNullException} for the given argument.
	 * 
	 * @param argument
	 */
	public ArgumentIsNotNullException(final Object argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
}
