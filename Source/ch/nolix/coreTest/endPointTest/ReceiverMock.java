//package declaration
package ch.nolix.coreTest.endPointTest;

//own imports
import ch.nolix.core.communicationInterfaces.IReceiver;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 50
 */
public final class ReceiverMock implements IReceiver {

	//optional attribute
	private String lastReceivedMessage;
	
	//method
	/**
	 * @return the last received message of this receiver mock.
	 * @throws  InvalidStateException if this receiver mock received no message.
	 */
	public String getLastReceivedMessage() {
		
		//Checks if this receiver mock has received a message.
		if (!hasReceivedMessage()) {
			throw new InvalidStateException(this, "received no message");
		}
		
		return lastReceivedMessage;
	}
	
	//method
	/**
	 * @return true if this receiver mock has received a message.
	 */
	public boolean hasReceivedMessage() {
		return (lastReceivedMessage != null);
	}
	
	//method
	/**
	 * Lets this receiver mock receive the given message.
	 * 
	 * @param message
	 * @throws NullArgumentException if the given message is null.
	 * @throws EmtpyArgumentException if the given message is null.
	 */
	public void receive(final String message) {
		
		//Checks if the given message is not null or empty.
		Validator.suppose(message).thatIsNamed("message").isNotEmpty();
		
		//Sets the last received message of this receiver mock.
		lastReceivedMessage = message;
	}
}
