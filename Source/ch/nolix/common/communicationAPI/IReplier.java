//package declaration
package ch.nolix.common.communicationAPI;

//interface
/**
 * A {@link IReplier} can reply to messages.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
public interface IReplier {
	
	//abstract method
	/**
	 * @param message
	 * @return the reply to the given message from the current {@link IReplier}.
	 */
	public abstract String getReply(String message);
}
