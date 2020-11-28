//package declaration
package ch.nolix.common.communicationapi;

//interface
/**
 * A {@link IReplyingSender} can send messages
 * and return replies to the messages.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 20
 */
public interface IReplyingSender {
	
	//method declaration
	/**
	 * Lets the current {@link IReplyingSender} send the given message.
	 * 
	 * @param message
	 * @return the reply to the given message.
	 */
	String sendAndGetReply(String message);
}
