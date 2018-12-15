//package declaration
package ch.nolix.core.endPoint4;

//own imports
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidArgumentException.NonRepresentingArgumentException;

//enum
/**
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 70
 */
public enum MessageRole {
	TARGET_APPLICATION_MESSAGE,
	RESPONSE_EXPECTING_MESSAGE,
	SUCCESS_RESPONSE,
	ERROR_RESPONSE;
	
	//prefixes
	private static final char TARGET_APPLICATION_PREFIX = 'T';
	private static final char RESPONSE_EXPECTING_MESSAGE_PREFIX = 'M';
	private static final char SUCCESS_RESPONSE_PREFIX = 'R';
	private static final char ERROR_RESPONSE_PREFIX = 'E';
	
	//static method
	/**
	 * @param prefix
	 * @return a new message role the given prefix represents.
	 * @throws InvalidArgumentException if the given prefix represents no message role.
	 */
	public static MessageRole createMessageRole(final char prefix) {
		
		//Enumerates the given prefix.
		switch (prefix) {
			case TARGET_APPLICATION_PREFIX:
				return TARGET_APPLICATION_MESSAGE;
			case RESPONSE_EXPECTING_MESSAGE_PREFIX:
				return RESPONSE_EXPECTING_MESSAGE;
			case SUCCESS_RESPONSE_PREFIX:
				return SUCCESS_RESPONSE;
			case ERROR_RESPONSE_PREFIX:
				return ERROR_RESPONSE;
			default:
				throw new NonRepresentingArgumentException(prefix, MessageRole.class);
		}
	}
	
	//method
	/**
	 * @return the prefix of this message role.
	 */
	public char getPrefix() {
		
		//Enumerates this message role.
		switch (this) {
			case TARGET_APPLICATION_MESSAGE:
				return TARGET_APPLICATION_PREFIX;
			case RESPONSE_EXPECTING_MESSAGE:
				return RESPONSE_EXPECTING_MESSAGE_PREFIX;
			case SUCCESS_RESPONSE:
				return SUCCESS_RESPONSE_PREFIX;
			case ERROR_RESPONSE:
				return ERROR_RESPONSE_PREFIX;
			default:
				throw new InvalidArgumentException(this);
		}
	}
}
