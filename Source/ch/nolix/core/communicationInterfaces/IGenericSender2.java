//package declaration
package ch.nolix.core.communicationInterfaces;

//interface
/**
 * A generic sender 2 can send messages of a cetrain type and receive replies of a certain type.
 * 
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 30
 * @param <M> - The type of the messages a generic sender can send.
 * @param <R> - The type of the replies a generic sender can receive.
 */
public interface IGenericSender2<M, R> {

	//abstract method
	/**
	 * Lets this sender send the given message.
	 * 
	 * @param message
	 * @return the reply to the given message.
	 */
	public abstract R sendAndGetReply(M message);
}
