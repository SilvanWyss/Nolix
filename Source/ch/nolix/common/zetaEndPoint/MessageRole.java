/*
 * file:	MessageRole.java
 * author:	Silvan Wyss
 * month:	2016-09
 * lines:	10
 */

//package declaration
package ch.nolix.common.zetaEndPoint;

//enum
public enum MessageRole {
	NORMAL_MESSAGE,
	SUCCESS_RESPONSE_MESSAGE,
	ERROR_RESPONSE_MESSAGE;
	
	//prefixes
	private static final char NORMAL_MESSAGE_PREFIX = 'M';
	private static final char SUCCESS_RESPONSE_MESSAGE_PREFIX = 'R';
	private static final char ERROR_RESPONSE_MESSAGE_PREFIX = 'E';
	
	public static MessageRole createMessageRole(char prefix) {
		switch (prefix) {
			case NORMAL_MESSAGE_PREFIX:
				return NORMAL_MESSAGE;
			case SUCCESS_RESPONSE_MESSAGE_PREFIX:
				return SUCCESS_RESPONSE_MESSAGE;
			case ERROR_RESPONSE_MESSAGE_PREFIX:
				return ERROR_RESPONSE_MESSAGE;
		}
		
		throw new RuntimeException("Error occured.");
	}
	
	//method
	/**
	 * @return the prefix of this message role
	 */
	public char getPrefix() {
		switch (this) {
			case NORMAL_MESSAGE:
				return NORMAL_MESSAGE_PREFIX;
			case SUCCESS_RESPONSE_MESSAGE:
				return SUCCESS_RESPONSE_MESSAGE_PREFIX;
			case ERROR_RESPONSE_MESSAGE:
				return ERROR_RESPONSE_MESSAGE_PREFIX;
			default:
				throw new RuntimeException("Error occured.");
		}
	}
}
