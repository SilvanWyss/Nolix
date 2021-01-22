//package declaration
package ch.nolix.common.communicationapi;

//interface
/**
 * A {@link IReplier} sends messages and returns a reply to a message.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 20
 */
public interface IReplier {
	
	//method declaration
	/**
	 * @param message
	 * @return the reply to the given message from the current {@link IReplier}.
	 */
	String getReplyTo(String message);
}
