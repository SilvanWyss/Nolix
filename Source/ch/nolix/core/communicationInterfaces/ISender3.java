//package declaration
package ch.nolix.core.communicationInterfaces;

//interface
/**
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 20
 * @param <M> - The type of the messages a sender 3 sends.
 * @param <R> - The type of the replies a sender 3 receives.
 */
public interface ISender3<M, R> {

	//abstract method
	/**
	 * Lets this sender send the given message.
	 * 
	 * @param message
	 * @return the reply to the given message.
	 */
	public abstract R sendAndGetReply(M message);
}
