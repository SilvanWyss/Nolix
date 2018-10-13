//package declaration
package ch.nolix.core.communicationAPI;

//interface
/**
 * A {@link IGenericSender} can send messages of a certain type.
 * 
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 30
 * @param <M> The type of the messages a {@link IGenericSender} can send.
 */
public interface IGenericSender<M> {
	
	//abstract method
	/**
	 * Lets the current {@link IGenericSender} send the given message.
	 * 
	 * @param message
	 */
	public abstract void send(M message);
	
	//default method
	/**
	 * Lets the current {@link IGenericSender} send the given messages.
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
