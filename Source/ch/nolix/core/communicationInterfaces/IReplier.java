//package declaration
package ch.nolix.core.communicationInterfaces;

//interface
/**
 * A replier can receive messages and return replies to them.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
public interface  IReplier {

	//abstract method
	/**
	 * @param message
	 * @return the reply to the given message.
	 */
	public abstract String getReply(String message);
}
