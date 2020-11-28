//package declaration
package ch.nolix.common.communicationapi;

//interface
/**
 * A {@link IReplier} can reply to messages.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
public interface IReplier {
	
	//method declaration
	/**
	 * @param message
	 * @return the reply to the given message from the current {@link IReplier}.
	 */
	String getReply(String message);
}
