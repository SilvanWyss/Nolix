//package declaration
package ch.nolix.common.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link UnsupportedArgumentException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when a given argument is not supported.
 * 
 * For example:
 * -A database adapter does not support a given database engine.
 * -An image viewer does not support a given image format.
 * 
 * @author Silvan Wyss
 * @month 2019-12
 * @lines 30
 */
@SuppressWarnings("serial")
public final class UnsupportedArgumentException extends InvalidArgumentException {
	
	//constant
	private static final String ERROR_PREDICATE = "is not supported";
	
	//constructor
	/**
	 * Creates a new {@link UnsupportedArgumentException} for the given argument.
	 * 
	 * @param argument
	 */
	public UnsupportedArgumentException(final Object argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
}
