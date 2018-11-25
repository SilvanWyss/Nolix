//package declaration
package ch.nolix.core.invalidStateException;

//own import
import ch.nolix.core.logger.Logger;

//class
/**
 * A {@link InvalidStateException} is a {@link RuntimeException}
 * that is supposed to be thrown when an object is in an invalid state.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 150
 */
@SuppressWarnings("serial")
public class InvalidStateException extends RuntimeException {
	
	//constant
	private static final String DEFAULT_ERROR_PREDICATE = "is not in a valid state";
	
	//attribute
	private final Object object;
	
	//static method
	/**
	 * @param errorPredicate
	 * @return a safe error predicate for the given error predicate.
	 * @throws RuntimeException if the given error predicate is null.
	 * @throws RuntimeException if the given error predicate is blank.
	 */
	private static String createSafeErrorPredicate(final String errorPredicate) {
		
		//Checks if the given error predicate is not null.
		if (errorPredicate == null) {
			throw new RuntimeException("The given error predicate is null.");
		}
		
		//Checks if the given error predicate is not blank.
		if (errorPredicate.isBlank()) {
			throw new RuntimeException("The given error predicate is blank.");
		}
		
		return errorPredicate;
	}
	
	//static method
	/**
	 * @param objectName
	 * @return a safe object name for the given object name.
	 * @throws RuntimeException if the given object name is null.
	 * @throws RuntimeException if the given object name is blank.
	 */
	private static String createSafeObjectName(final String objectName) {
		
		//Checks if the given object name is not null.
		if (objectName == null) {
			throw new RuntimeException("The given object name is null.");
		}
		
		//Checks if the given object name is not blank.
		if (objectName.isBlank()) {
			throw new RuntimeException("The given object name is blank.");
		}
		
		return objectName;
	}
	
	//static method
	/**
	 * @param object
	 * @return a safe object name for the given object.
	 * @throws RuntimeException if the given object is null.
	 */
	private static String createSafeObjectName2(final Object object) {
		
		//Checks if the given object is not null.
		if (object == null) {
			throw new RuntimeException("The given object is null.");
		}
		
		return object.getClass().getSimpleName();
	}
	
	//constructor
	/**
	 * Creates a new {@link InvalidStateException} for the given object.
	 * 
	 * @param object
	 * @throws RuntimeException if the given object is null.
	 */
	public InvalidStateException(final Object object) {
		
		//Calls other constructor
		this(createSafeObjectName2(object), object, DEFAULT_ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link InvalidStateException} for the given object and error predicate.
	 * 
	 * @param object
	 * @param errorPredicate
	 * @throws RuntimeException if the given object is null.
	 * @return RuntimeException if the given error predicate is null.
	 * @throws RuntimeException if the given error predicate is blank.
	 */
	public InvalidStateException(final Object object, final String errorPredicate) {
		
		//Calls other constructor
		this(createSafeObjectName2(object), object, errorPredicate);
	}
	
	//constructor
	/**
	 * Creates a new {@link InvalidStateException}
	 * for the given object, object name, and error predicate.
	 * 
	 * @param objectName
	 * @param object
	 * @param errorPredicate
	 * @throws RuntimeException if the given object name is null.
	 * @throws RuntimeException if the given object name is blank.
	 * @return RuntimeException if the given object is null.
	 * @throws RuntimeException if the given error predicate is null.
	 * @throws RuntimeException if the given error predicate is blank.
	 */
	public InvalidStateException(
		final String objectName,
		final Object object,
		final String errorPredicate
	) {
		
		super("The " + createSafeObjectName(objectName) + ' ' + createSafeErrorPredicate(errorPredicate));
		
		//Checks if the given object is not null.
		if (object == null) {
			throw new RuntimeException("The given object is null.");
		}
		
		//Sets the object of the current invalid state exception.
		this.object = object;
		
		Logger.logError(getMessage());
	}
	
	//method
	/**
	 * @return the object of the current {@link InvalidStateException}.
	 */
	public final Object getRefObject() {
		return object;
	}
}
