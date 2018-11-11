//package declaration
package ch.nolix.core.invalidStateException;

//class
/**
 * A {@link ClosedStateException} is a {@link InvalidStateException}
 * that is supposed to be thrown when an object is undesired closed.
 * 
 * A {@link ClosedStateException} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 30
 */
@SuppressWarnings("serial")
public final class ClosedStateException extends InvalidStateException {
	
	//constant
	private static final String ERROR_PREDICATE = "is closed";
	
	//constructor
	/**
	 * Creates a new {@link ClosedStateException} for the given object.
	 * 
	 * @param object
	 * @throws NullArgumentException if the given object is null.
	 */
	public ClosedStateException(final AutoCloseable object) {
		
		//Calls constructor of the base class.
		super(object, ERROR_PREDICATE);
	}
}
