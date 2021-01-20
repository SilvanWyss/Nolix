//package declaration
package ch.nolix.common.genericcommunicationapi;

//interface
/**
 * A {@link IReplyingSender} can send messages of a certain type
 * and return replies to the messages.
 * 
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 20
 * @param <M> is the type of the messages a {@link IReplyingSender} can send.
 * @param <R> is the type of the replies a {@link IReplyingSender} returns.
 */
public interface IReplyingSender<M, R> {

	//method declaration
	/**
	 * Lets the current {@link IReplyingSender} send the given message.
	 * 
	 * @param message
	 * @return the reply to the given message.
	 */
	R sendAndGetReply(M message);
}
