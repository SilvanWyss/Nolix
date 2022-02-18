//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link ExpiredArgumentException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument is undesirably expired.
 * A {@link ExpiredArgumentException} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2021-03-07
 */
@SuppressWarnings("serial")
public final class ExpiredArgumentException extends InvalidArgumentException {
	
	//constant
	private static final String ERROR_PREDICATE = "is expired";
	
	//constructor
	/**
	 * Creates a new {@link ExpiredArgumentException} for the given argument.
	 * 
	 * @param argument
	 */
	public ExpiredArgumentException(final Object argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
}
