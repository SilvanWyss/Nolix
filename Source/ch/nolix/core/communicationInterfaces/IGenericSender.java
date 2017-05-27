//package declaration
package ch.nolix.core.communicationInterfaces;

//interface
/**
 * A generic sender can send messages.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 30
 * @param <M> - The type of the messages a generic sender sends.
 */
public interface IGenericSender<M> {

	//abstract method
	/**
	 * Lets this generic sender send the given message.
	 * 
	 * @param message
	 */
	public abstract void send(M message);
	
	//default method
	/**
	 * Lets this generic sender send the given messages.
	 * 
	 * @param messages
	 */
	@SuppressWarnings("unchecked")
	public default void send(final M... messages) {
		
		//Iterates the given messages.
		for (final M m : messages) {
			send(m);
		}
	}
}
