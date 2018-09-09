//package declaration
package ch.nolix.core.communicationAPI;

//interface
/**
 * A receiver can receive messages of a certain type.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 * @param <M> - The type of the messages a generic receiver can receive.
 */
public interface IGenericReceiver<M> {

	//abstract method
	/**
	 * Lets this generic receiver receive the given message.
	 * 
	 * @param message
	 */
	public abstract void receive(M message);
}
