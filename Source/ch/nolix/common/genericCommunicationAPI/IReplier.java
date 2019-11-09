//package declaration
package ch.nolix.common.genericCommunicationAPI;

//interface
/**
 * A {@link IReplier} can reply to messages of a certain type.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 * @param <M> The type of the messages a {@link IReplier} can reply to.
 * @param <R> The type of the replies a {@link IReplier} can return.
 */
public interface IReplier<M, R> {
	
	//abstract method
	/**
	 * Lets the current {@link IReplier} receive the given message.
	 * 
	 * @param message
	 * @return the reply to the given message.
	 */
	public abstract R getReply(M message);
}
