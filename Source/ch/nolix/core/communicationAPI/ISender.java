//package declaration
package ch.nolix.core.communicationAPI;

//interface
/**
 * A {@link Sender} can send messages.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 30
 */
public interface ISender {
	
	//abstract method
	/**
	 * Lets the current {@link Sender} send the given message.
	 * 
	 * @param message
	 */
	public abstract void send(String message);
	
	//default method
	/**
	 * Lets the current {@link Sender} send the given messages.
	 * 
	 * @param messages
	 */
	public default void send(final String... messages) {
		
		//Iterates the given messages.
		for (final var m : messages) {
			send(m);
		}
	}
}
