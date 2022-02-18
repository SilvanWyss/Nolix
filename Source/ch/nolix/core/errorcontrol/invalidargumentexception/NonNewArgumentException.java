//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link NonNewArgumentException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument is undesirably not new.
 * A {@link NonNewArgumentException} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2021-07-03
 */
@SuppressWarnings("serial")
public final class NonNewArgumentException extends InvalidArgumentException {
	
	//constant
	private static final String ERROR_PREDICATE = "is not new";
	
	//constructor
	/**
	 * Creates a new {@link NonNewArgumentException} for the given argument.
	 * 
	 * @param argument
	 */
	public NonNewArgumentException(final Object argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
}
