//package declaration
package ch.nolix.core.invalidStateException;

//class
/**
 * A closed state exception is an invalid state exception
 * that is supposed to be thrown when an object is undesired closed.
 * 
 * A close state exception is not mutable.
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
	 * Creates new closed state exception for the given object.
	 * 
	 * @param object
	 * @throws NullArgumentException if the given object is null.
	 */
	public ClosedStateException(final AutoCloseable object) {
		
		//Calls constructor of the base class.
		super(object, ERROR_PREDICATE);
	}
}
