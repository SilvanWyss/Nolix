//package declaration
package ch.nolix.core.communicationInterfaces;

//interface
/**
 * A generic sender 2 can send messages and receive replies.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 20
 * @param <M> - The type of the messages a generic sender 2 sends.
 * @param <R> - The type of the replies a generic sender 2 received.
 */
public interface IGenericSender2<M, R> {

	//abstract method
	/**
	 * Lets this generic sender 2 send the given message.
	 * 
	 * @param message
	 * @return the reply to the given message.
	 */
	public abstract R sendAndGetReply(M message);
}
