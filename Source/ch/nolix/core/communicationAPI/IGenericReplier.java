//package declaration
package ch.nolix.core.communicationAPI;

//interface
/**
 * A {@link IGenericReplier} can reply to messages of a certain type.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 * @param <M> The type of the messages a {@link IGenericReplier} can reply to.
 * @param <R> The type of the replies a {@link IGenericReplier} can return.
 */
public interface IGenericReplier<M, R> {
	
	//abstract method
	/**
	 * Lets the current {@link IGenericReplier} receive the given message.
	 * 
	 * @param message
	 * @return the reply to the given message.
	 */
	public abstract R getReply(M message);
}
