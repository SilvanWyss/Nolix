//package declaration
package ch.nolix.core.communicationAPI;

//interface
/**
 * A {@link IReceiver} can receive messages.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
public interface IReceiver {

	//abstract method
	/**
	 * Lets the current {@link IReceiver} receive the given message.
	 * 
	 * @param message
	 */
	public abstract void receive(String message);
}
