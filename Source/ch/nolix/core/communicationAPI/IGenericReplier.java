//package declaration
package ch.nolix.core.communicationAPI;

//interface
/**
 * A generic replier can receive messages of a certain type and return replies of a certain type to them.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 * @param <M> - The type of the messages a generic replier can reply to.
 * @param <R> - The type of the replies a generic replier can reply.
 */
public interface  IGenericReplier<M, R> {

	//abstract method
	/**
	 * Lets this replier receive the given message.
	 * 
	 * @param message
	 * @return the reply to the given message.
	 */
	public abstract R getReply(M message);
}