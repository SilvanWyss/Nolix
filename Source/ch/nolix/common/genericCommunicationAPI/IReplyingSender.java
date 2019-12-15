//package declaration
package ch.nolix.common.genericCommunicationAPI;

//interface
/**
 * A {@link IReplyingSender} can send messages of a certain type
 * and return replies to the messages.
 * 
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 20
 * @param <M> The type of the messages a {@link IReplyingSender} can send.
 * @param <R> The type of the replies a {@link IReplyingSender} returns.
 */
public interface IReplyingSender<M, R> {

	//method declaration
	/**
	 * Lets the current {@link IReplyingSender} send the given message.
	 * 
	 * @param message
	 * @return the reply to the given message.
	 */
	public abstract R sendAndGetReply(M message);
}
