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
 * @lines 120
 */
@SuppressWarnings("serial")
public class InvalidStateException extends RuntimeException {
	
	//constant
	private static final String ERROR_PREDICATE = "is not in a valid state";
	
	//attribute
	private final Object object;
	
	//static method
	/**
	 * @param errorPredicate
	 * @return a safe error predicate for the given error predicate.
	 * @throws RuntimeException if the given error predicate is null.
	 * @throws RuntimeException if the given error predicate is blank.
	 */
	private static String getSafeErrorPredicate(final String errorPredicate) {
		
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
	 * @param object
	 * @return a safe name for the given object.
	 * @throws RuntimeException if the given object is null.
	 */
	private static String getSafeName(final Object object) {
		
		//Checks if the given object is not null.
		if (object == null) {
			throw new RuntimeException("The given object is null.");
		}
		
		return object.getClass().getSimpleName();
	}
	
	//static method
	/**
	 * @param name
	 * @return a safe name for the given name.
	 * @throws RuntimeException if the given object name is null.
	 * @throws RuntimeException if the given object name is blank.
	 */
	private static String getSafeName2(final String name) {
		
		//Checks if the given object name is not null.
		if (name == null) {
			throw new RuntimeException("The given name is null.");
		}
		
		//Checks if the given name is not blank.
		if (name.isBlank()) {
			throw new RuntimeException("The given name is blank.");
		}
		
		return name;
	}
	
	//constructor
	/**
	 * Creates a new {@link InvalidStateException}
	 * for the given object with the given error predicate.
	 * 
	 * @param object
	 * @throws RuntimeException if the given object is null.
	 */
	public InvalidStateException(final Object object) {
		
		//Calls constructor of the base class.
		super(getSafeName(object) + ' ' + ERROR_PREDICATE + '.');
		
		//Sets the object of the current invalid state exception.
		this.object = object;
		
		Logger.logError(getMessage());
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
		
		//Calls constructor of the base class.
		super(getSafeName(object) + ' ' + getSafeErrorPredicate(errorPredicate) + '.');
		
		//Sets the object of the current invalid state exception.
		this.object = object;
		
		Logger.logError(getMessage());
	}
	
	//constructor
	/**
	 * Creates a new {@link InvalidStateException} for the given object, that has the given name,
	 * and for the given error predicate.
	 * 
	 * @param name
	 * @param object
	 * @param errorPredicate
	 * @throws RuntimeException if the given name is null.
	 * @throws RuntimeException if the given name is blank.
	 * @return RuntimeException if the given object is null.
	 * @throws RuntimeException if the given error predicate is null.
	 * @throws RuntimeException if the given error predicate is blank.
	 */
	public InvalidStateException(
		final String name,
		final Object object,
		final String errorPredicate
	) {
		
		super("The " + getSafeName2(name) + ' ' + errorPredicate);
		
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
