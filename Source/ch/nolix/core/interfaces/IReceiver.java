//package declaration
package ch.nolix.core.interfaces;

//interface
/**
 * A receiver can receive messages.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
public interface IReceiver {

	//abstract method
	/**
	 * Receives the given message.
	 * 
	 * @param message
	 */
	public abstract void receive(String message);
}
