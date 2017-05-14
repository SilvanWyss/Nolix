//package declaration
package ch.nolix.core.interfaces;

//interface
/**
 * A sender can send messages.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 40
 */
public interface ISender {

	//abstract method
	/**
	 * Lets this sender send the given message.
	 * 
	 * @param message
	 */
	public abstract void send(String message);
	
	//default method
	/**
	 * Lets this sender send the given messages.
	 * 
	 * @param messages
	 * @throws NullArgumentException if the given message container is null.
	 * @throws NullArgumentException if one of the given messages is null.
	 * 
	 * @param messages
	 */
	public default void send(final String... messages) {
		
		//TODO: Add required function to zeta validator.
		//Validator.supposeThat(messages).thatIsNamed("message container").isNotNullContainer();
		
		//Iterates the given messages.
		for (final String m : messages) {
			send(m);
		}
	}
}
