//package declaration
package ch.nolix.common.communicationAPI;

//interface
/**
 * A {@link Sender} can send messages.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 30
 */
public interface ISender {
	
	//method declaration
	/**
	 * Lets the current {@link Sender} send the given message.
	 * 
	 * @param message
	 */
	void send(String message);
	
	//method
	/**
	 * Lets the current {@link Sender} send the given messages.
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
