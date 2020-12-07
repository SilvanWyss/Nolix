//package declaration
package ch.nolix.common.endpoint;

//own imports
import ch.nolix.common.closeableelement.CloseController;
import ch.nolix.common.closeableelement.ICloseableElement;
import ch.nolix.common.communicationapi.IReceiver;
import ch.nolix.common.communicationapi.ISender;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.validator.Validator;

//class
/**
 * An end point can send messages to an other end point of the same type.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 110
 */
public abstract class EndPoint implements ICloseableElement, ISender {
	
	//attributes
	private final CloseController closeController = new CloseController(this);
	private final boolean hasRequestedConnection;
	
	//optional attribute
	private IReceiver receiver;
	
	//constructor
	/**
	 * Creates a new end point.
	 * 
	 * @param hasRequestedConnection
	 */
	EndPoint(final boolean hasRequestedConnection) {
		this.hasRequestedConnection = hasRequestedConnection;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	/**
	 * @return true if this end point has a receiver.
	 */
	public final boolean hasReceiver() {
		return (receiver != null);
	}
	
	//method
	/**
	 * @return true if this end point has requested the connection.
	 */
	public final boolean hasRequestedConnection() {
		return hasRequestedConnection;
	}
	
	//method
	/**
	 * Sets the receiver of this end point.
	 * 
	 * @param receiver
	 * @throws ArgumentIsNullException if the given receiver is null.
	 * @throws ClosedArgumentException if this end point is closed.
	 */
	public final void setReceiver(final IReceiver receiver) {
		
		//Asserts that the given receiver is not null.
		Validator.assertThat(receiver).isOfType(IReceiver.class);
		
		//Asserts that this end point is open.
		assertIsOpen();
		
		//Sets the receiver of this end point.
		this.receiver = receiver;
	}
	
	//method
	/**
	 * Lets this end point receive the given message.
	 * 
	 * @param message
	 * @throws ClosedArgumentException if this end point is closed.
	 * @throws ArgumentDoesNotHaveAttributeException if this end point does not have a receiver.
	 */
	protected final void receive(final String message) {
		
		//Asserts that this end point is open.
		assertIsOpen();
		
		getRefReceiver().receive(message);
	}
	
	//method
	/**
	 * @return the receiver of this end point.
	 * @throws ArgumentDoesNotHaveAttributeException if this end point does not have a receiver.
	 */
	private IReceiver getRefReceiver() {
		
		//Asserts that this end point has a receiver.
		if (!hasReceiver()) {
			throw new ArgumentDoesNotHaveAttributeException(this, IReceiver.class);
		}
		
		return receiver;
	}
}
