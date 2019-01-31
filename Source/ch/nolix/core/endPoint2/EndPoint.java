//package declaration
package ch.nolix.core.endPoint2;

//own imports
import ch.nolix.core.bases.ClosableElement;
import ch.nolix.core.communicationAPI.IReceiver;
import ch.nolix.core.communicationAPI.ISender;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidStateException.ArgumentMissesAttributeException;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * An end point can send messages to an other end point of the same type.
 * An end point is abortable.
 * 
 * @author Silvan Wyss
 * @month 2017-04
 * @lines 140
 */
public abstract class EndPoint
extends ClosableElement
implements ISender {

	//attribute
	private final boolean hasRequestedConnection;
	
	//optional attributes
	private String target;
	private IReceiver receiver;
	
	//package-visible constructor
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
	 * @return the target of this end point.
	 * @throws ArgumentMissesAttributeException if this end point does not have a target.
	 */
	public final String getTarget() {
		
		//Checks if this end point has a target.
		if (!hasTarget()) {
			throw new ArgumentMissesAttributeException(this, "target");
		}
		
		return target;
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
	 * @return true if this end point has a target.
	 */
	public final boolean hasTarget() {
		return (target != null);
	}
	
	//method
	/**
	 * @return true if this end point is a local end point.
	 */
	public final boolean isLocalEndPoint() {
		return !isNetEndPoint();
	}
	
	//method
	/**
	 * @return true if this end point is a net end point.
	 */
	public abstract boolean isNetEndPoint();
	
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
		supposeIsAlive();
		
		//Checks if the given receiver is not null.
		Validator.suppose(receiver).isOfType(IReceiver.class);
		
		//Sets the receiver of this end point.
		this.receiver = receiver;
	}
	
	protected void receive(final String message) {
		getRefReceiver().receive(message);
	}
	
	//method
	/**
	 * Sets the target of this net end point.
	 * 
	 * @param target
	 * @throws NullArgumentException if the given target is null.
	 * @throws EmptyArgumentException if the given target is empty.
	 * @throws InvalidArgumentException if this net end point is aborted.
	 */
	protected void setTarget(final String target) {
		
		//Checks if the given target is not empty.
		Validator.suppose(target).thatIsNamed("target").isNotEmpty();
		
		//Checks if this net end point is not stopped.
		supposeIsAlive();
		
		//Sets the target of this end point.
		this.target = target;
	}
	
	private IReceiver getRefReceiver() {
		
		//Checks if this end point has a receiver.
		if (!hasReceiver()) {
			throw new ArgumentMissesAttributeException(this, IReceiver.class);
		}
		
		return receiver;
	}
}
