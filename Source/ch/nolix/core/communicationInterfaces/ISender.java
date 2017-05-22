//package declaration
package ch.nolix.core.communicationInterfaces;

//interface
/**
 * A sender can send messages.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 30
 */
public interface ISender {

	//abstract method
	/**
	 * Lets this sender send the given message.
	 * 
	 * @param message
	 */
	public abstract void send(String message);
	
	//default method
	/**
	 * Lets this sender send the given messages.
	 * 
	 * @param messages
	 * @throws NullArgumentException if one of the given messages is null.
	 */
	public default void send(final String... messages) {
		
		//Iterates the given messages.
		for (final String m : messages) {
			send(m);
		}
	}
}
