//package declaration
package ch.nolix.core.endPoint;

//own imports
import ch.nolix.core.basic.AbortableElement;
import ch.nolix.core.communicationInterfaces.IReceiver;
import ch.nolix.core.communicationInterfaces.ISender;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * An end point can send messages to an other end point of the same type.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 100
 */
public abstract class EndPoint
extends AbortableElement
implements ISender {
	
	//attribute
	private final boolean hasRequestedConnection;
	
	//optional attribute
	private IReceiver receiver;
	
	//package-visible constructor
	/**
	 * Creates new end point.
	 * 
	 * @param hasRequestedConnection
	 */
	EndPoint(final boolean hasRequestedConnection) {
		this.hasRequestedConnection = hasRequestedConnection;
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
	 * @throws NullArgumentException if the given receiver is null.
	 * @throws InvalidStateException if this end point is aborted.
	 */
	public final void setReceiver(final IReceiver receiver) {
		
		//Checks if the given receiver is not null.
		Validator.supposeThat(receiver).thatIsInstanceOf(IReceiver.class).isNotNull();
		
		//Checks if this end point is not aborted.
		throwExceptionIfAborted();
		
		//Sets the receiver of this end point.
		this.receiver = receiver;
	}
	
	//method
	/**
	 * Lets this end point receive the given message.
	 * 
	 * @param message
	 * @throws InvalidStateException if this end point is aborted.
	 * @throws UnexistingAttributeException if this end point has no receiver.
	 */
	protected final void receive(final String message) {
		
		//Checks if this end point is not aborted.
		throwExceptionIfAborted();
		
		getRefReceiver().receive(message);
	}
	
	//method
	/**
	 * @return the receiver of this end point.
	 * @throws UnexistingAttributeException if this end point has no receiver.
	 */
	private IReceiver getRefReceiver() {
		
		//Checks if this end point has a receiver.
		if (!hasReceiver()) {
			throw new UnexistingAttributeException(this, IReceiver.class);
		}
		
		return receiver;
	}
}
