//package declaration
package ch.nolix.common.genericCommunicationAPI;

//interface
/**
 * A {@link IReceiver} can receive messages of a certain type.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 * @param <M> The type of the messages a {@link IReceiver} can receive.
 */
public interface IReceiver<M> {
	
	//abstract method
	/**
	 * Lets the current {@link IReceiver} receive the given message.
	 * 
	 * @param message
	 */
	public abstract void receive(M message);
}
