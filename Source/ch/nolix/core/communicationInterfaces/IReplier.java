//package declaration
package ch.nolix.core.communicationInterfaces;

//interface
/**
 * A zeta receiver can receive messages and return a reply to them.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
public interface  IReplier {

	//abstract method
	/**
	 * Receives the given message and returns a reply.
	 * 
	 * @param message
	 * @return the reply to the given message.
	 */
	public abstract String getReply(String message);
}
