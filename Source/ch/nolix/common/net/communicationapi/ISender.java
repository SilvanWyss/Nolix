//package declaration
package ch.nolix.common.net.communicationapi;

//interface
/**
 * A {@link ISender} can send messages.
 * 
 * @author Silvan Wyss
 * @date 2017-05-06
 * @lines 30
 */
public interface ISender {
	
	//method declaration
	/**
	 * Lets the current {@link ISender} send the given message.
	 * 
	 * @param message
	 */
	void send(String message);
	
	//method
	/**
	 * Lets the current {@link ISender} send the given messages.
	 * 
	 * @param messages
	 */
	default void send(final String... messages) {
		
		//Iterates the given messages.
		for (final var m : messages) {
			send(m);
		}
	}
}
