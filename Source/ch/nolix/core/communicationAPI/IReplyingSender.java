//package declaration
package ch.nolix.core.communicationAPI;

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
	
	//abstract method
	/**
	 * Lets the current {@link IReplyingSender} send the given message.
	 * 
	 * @param message
	 * @return the reply to the given message.
	 */
	public abstract String sendAndGetReply(String message);
}
