//package declaration
package ch.nolix.core.communicationAPI;

//interface
/**
 * A {@link IGenericReplyingSender}
 * can send messages of a certain type and return replies.
 * 
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 20
 * @param <M> The type of the messages a {@link IGenericReplyingSender} can send.
 * @param <R> The type of the replies a {@link IGenericReplyingSender} returns.
 */
public interface IGenericReplyingSender<M, R> {

	//abstract method
	/**
	 * Lets the current {@link IGenericReplyingSender} send the given message.
	 * 
	 * @param message
	 * @return the reply to the given message.
	 */
	public abstract R sendAndGetReply(M message);
}
