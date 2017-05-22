//package declaration
package ch.nolix.core.communicationInterfaces;

//interface
/**
 * A sender can send messages and receive replies.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 20
 */
public interface ISender2 {

	//abstract method
	/**
	 * Lets this sender send the given message and return a reply.
	 * 
	 * @param message
	 * @return the reply to the given message.
	 */
	public abstract String sendAndGetReply(String message);
}
