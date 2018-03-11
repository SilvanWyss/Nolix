//package declaration
package ch.nolix.primitive.invalidStateException;

//class
/**
 * An invalid state exception is an exception that intended to be thrown
 * when an object is in an invalid state.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 70
 */
@SuppressWarnings("serial")
public class InvalidStateException extends RuntimeException {

	//constant
	private static final String DEFAULT_ERROR_PREDICATE = "is in an invalid state";
	
	//attributes
	final Object object;
	final String errorPredicate;
	
	//constructor
	/**
	 * Creates a new invalid state exception for the given object.
	 * 
	 * @param object
	 */
	public InvalidStateException(final Object object) {
		
		//Calls other constructor.
		this(object, DEFAULT_ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new invalid state exception for the given object and the given error predicate.
	 * 
	 * @param object
	 * @param errorPredicate
	 * @throws RuntimeException if the given error predicate is null.
	 * @throws RuntimeException if the given error predicate is empty.
	 */
	public InvalidStateException(final Object object, final String errorPredicate) {
		
		//Calls constructor of the base class.
		super(object.getClass().getSimpleName() + " " + errorPredicate + ".");
		
		//Checks if the given error predicate is not null.
		if (errorPredicate == null) {
			throw new RuntimeException("The given error predicate is null.");
		}
		
		//Checks if the given error predicate is not empty.
		if (errorPredicate.isEmpty()) {
			throw new RuntimeException("The given error predicate is empty.");
		}
		
		//Sets the object of this invalid state exception.
		this.object = object;
		
		//Sets the error predicate of this invalid state exception.
		this.errorPredicate = errorPredicate;
	}
	
	//method
	/**
	 * @return the object of this invalid state exception.
	 */
	public Object getRefObject() {
		return object;
	}
}
