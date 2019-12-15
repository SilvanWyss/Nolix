//package declaration
package ch.nolix.common.communicationAPI;

//interface
/**
 * A {@link IReceiver} can receive messages.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
public interface IReceiver {
	
	//method declaration
	/**
	 * Lets the current {@link IReceiver} receive the given message.
	 * 
	 * @param message
	 */
	public abstract void receive(String message);
}
