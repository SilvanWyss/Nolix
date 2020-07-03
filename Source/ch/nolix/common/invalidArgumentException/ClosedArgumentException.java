//package declaration
package ch.nolix.common.invalidArgumentException;

//class
/**
 * A {@link ClosedArgumentException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when an argument is undesirably closed.
 * 
 * @author Silvan Wyss
 * @month 2019-01
 * @lines 30
 */
@SuppressWarnings("serial")
public final class ClosedArgumentException extends InvalidArgumentException {
	
	//constant
	private static final String ERROR_PREDICATE = "is closed";
	
	//constructor
	/**
	 * Creates a new {@link ClosedArgumentException} for the given argument.
	 * 
	 * @param argument
	 */
	public ClosedArgumentException(final Object argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
}
