//package declaration
package ch.nolix.core.endPoint2;

//own imports
import ch.nolix.core.basic.AbortableElement;
import ch.nolix.core.interfaces.IReceiver;
import ch.nolix.core.interfaces.ISender;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * An end point can send messages to an other end point of the same type.
 * An end point is abortable.
 * 
 * @author Silvan Wyss
 * @month 2017-04
 * @lines 90
 */
public abstract class EndPoint
extends AbortableElement
implements ISender {

	//attribute
	private final boolean hasRequestedConnection;
	
	//optional attribute
	private IReceiver receiver;
	
	//constructor
	/**
	 * Creates new end point.
	 * 
	 * @param hasRequestedConnection
	 */
	public EndPoint(final boolean hasRequestedConnection) {
		this.hasRequestedConnection = hasRequestedConnection;
	}
	
	//abstract method
	/**
	 * @return the target of this end point.
	 */
	public abstract String getTarget();
	
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
	 * @throws InvalidArgumentException if this end point is aborted.
	 */
	public final void setReceiver(final IReceiver receiver) {
		
		//Checks if this end point is aborted.
		throwExceptionIfAborted();
		
		//Checks if the given receiver is not null.
		Validator.supposeThat(receiver).thatIsInstanceOf(IReceiver.class).isNotNull();
	}
	
	//method
	/**
	 * @return the receiver of this end point.
	 * @throws UnexistingAttributeException if this end point has no receiver.
	 */
	protected final IReceiver getRefReceiver() {
		
		//Checks if this end point has a receiver.
		if (!hasReceiver()) {
			throw new UnexistingAttributeException(this, "receiver");
		}
		
		return receiver;
	}
}
