//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link DeletedArgumentException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when an argument is undesirably deleted.
 * 
 * @author Silvan Wyss
 * @date 2021-07-12
 * @lines 30
 */
@SuppressWarnings("serial")
public final class DeletedArgumentException extends InvalidArgumentException {
	
	//constant
	private static final String ERROR_PREDICATE = "is deleted";
	
	//constructor
	/**
	 * Creates a new {@link DeletedArgumentException} for the given argument.
	 * 
	 * @param argument
	 */
	public DeletedArgumentException(final Object argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
}
