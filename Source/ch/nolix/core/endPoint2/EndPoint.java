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
 * @lines 140
 */
public abstract class EndPoint
extends AbortableElement
implements ISender {
	
	static final String DEFAULT_TARGET = "DefaultTarget";

	//attributes
	private final boolean hasRequestedConnection;
	private String target;
	
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
	 * @return the target of this local end point.
	 */
	public final String getTarget() {
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
		throwExceptionIfAborted();
		
		//Checks if the given receiver is not null.
		Validator.supposeThat(receiver).thatIsInstanceOf(IReceiver.class).isNotNull();
		
		//Sets the receiver of this end point.
		this.receiver = receiver;
	}
	
	private IReceiver getRefReceiver() {
		
		//Checks if this end point has a receiver.
		if (!hasReceiver()) {
			throw new UnexistingAttributeException(this, IReceiver.class);
		}
		
		return receiver;
	}
	
	//method
	/**
	 * @return true if this end point has a target.
	 */
	protected boolean hasTarget() {
		return (target != null);
	}
	
	//method
	/**
	 * Lets this net end point receive the given message.
	 * 
	 * @param message
	 * @throws InvalidArgumentException if this net end point is aborted.
	 */
	protected void receive(final String message) {
		
		//Checks if this net end point is not stopped.
		throwExceptionIfAborted();
		
		if (!hasTarget()) {
			setTarget(message);
		}
		else {
			getRefReceiver().receive(message);
		}
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
		Validator.supposeThat(target).thatIsNamed("target").isNotEmpty();
		
		//Checks if this net end point is not stopped.
		throwExceptionIfAborted();
		
		//Sets the target of this end point.
		this.target = target;
	}
}
