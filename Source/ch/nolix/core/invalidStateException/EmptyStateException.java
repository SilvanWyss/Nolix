//package declaration
package ch.nolix.core.invalidStateException;

//class
/**
 * An empty state exception is an invalid state exception
 * that is supposed to be thrown when an object is undesired empty.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 30
 */
@SuppressWarnings("serial")
public final class EmptyStateException extends InvalidStateException {

	//constant
	private static final String ERROR_PREDICATE = "is empty";

	//constructor
	/**
	 * Creates new empty state exception for the given object.
	 * 
	 * @param object
	 * @throws NullArgumentException if the given object is null.
	 */
	public EmptyStateException(final Object object) {
		
		//Calls constructor of the base class.
		super(object, ERROR_PREDICATE);
	}	
}
