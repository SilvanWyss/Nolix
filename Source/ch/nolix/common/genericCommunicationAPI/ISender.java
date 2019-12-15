//package declaration
package ch.nolix.common.genericCommunicationAPI;

//interface
/**
 * A {@link ISender} can send messages of a certain type.
 * 
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 30
 * @param <M> The type of the messages a {@link ISender} can send.
 */
public interface ISender<M> {
	
	//method declaration
	/**
	 * Lets the current {@link ISender} send the given message.
	 * 
	 * @param message
	 */
	public abstract void send(M message);
	
	//default method
	/**
	 * Lets the current {@link ISender} send the given messages.
	 * 
	 * @param messages
	 */
	@SuppressWarnings("unchecked")
	public default void send(final M... messages) {
		
		//Iterates the given messages.
		for (final var m : messages) {
			send(m);
		}
	}
}
