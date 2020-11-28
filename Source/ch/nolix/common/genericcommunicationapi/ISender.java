//package declaration
package ch.nolix.common.genericcommunicationapi;

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
	void send(M message);
	
	//method
	/**
	 * Lets the current {@link ISender} send the given messages.
	 * 
	 * @param messages
	 */
	@SuppressWarnings("unchecked")
	default void send(final M... messages) {
		
		//Iterates the given messages.
		for (final var m : messages) {
			send(m);
		}
	}
}
