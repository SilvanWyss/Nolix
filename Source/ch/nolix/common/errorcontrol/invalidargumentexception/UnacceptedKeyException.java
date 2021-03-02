//package declaration
package ch.nolix.common.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link UnacceptedKeyException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when a given key is not accepted.
 * 
 * @author Silvan Wyss
 * @date 2020-12-18
 * @lines 30
 */
@SuppressWarnings("serial")
public final class UnacceptedKeyException extends InvalidArgumentException {
	
	//constants
	private static final String ARGUMENT_NAME = "key";
	private static final String ERROR_PREDICATE = "is not accepted";
	
	//constructor
	/**
	 * Creates a new {@link UnacceptedKeyException} for the given key.
	 * 
	 * @param key
	 */
	public UnacceptedKeyException(final String key) {
		
		//Calls constructor of the base class.
		super(ARGUMENT_NAME, key, ERROR_PREDICATE);
	}
}
