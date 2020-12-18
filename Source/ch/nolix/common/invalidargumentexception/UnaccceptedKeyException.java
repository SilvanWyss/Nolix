//package declaration
package ch.nolix.common.invalidargumentexception;

//class
/**
 * A {@link UnaccceptedKeyException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when a given key is not accepted.
 * 
 * @author Silvan Wyss
 * @date 2020-12-18
 * @lines 30
 */
@SuppressWarnings("serial")
public final class UnaccceptedKeyException extends InvalidArgumentException {
	
	//constants
	private static final String ARGUMENT_NAME = "key";
	private static final String ERROR_PREDICATE = "is not accepted";
	
	//constructor
	/**
	 * Creates a new {@link UnaccceptedKeyException} for the given key.
	 * 
	 * @param key
	 */
	public UnaccceptedKeyException(final String key) {
		
		//Calls constructor of the base class.
		super(ARGUMENT_NAME, key, ERROR_PREDICATE);
	}
}
