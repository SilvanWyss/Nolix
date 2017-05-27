//package declaration
package ch.nolix.core.communicationInterfaces;

//interface
/**
 * A sender 2 can send messages and receive replies.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 20
 */
public interface ISender2 {

	//abstract method
	/**
	 * Lets this sender 2 send the given message.
	 * 
	 * @param message
	 * @return the reply to the given message.
	 */
	public abstract String sendAndGetReply(String message);
}
