//package declaration
package ch.nolix.core.endPoint2;

import ch.nolix.core.communicationAPI.IReceiver;
import ch.nolix.core.communicationAPI.ISender;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.optionalClosableElement.OptionalClosableElement;
import ch.nolix.core.validator.Validator;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;

//abstract class
/**
 * A {@link EndPoint} can send messages to an other {@link EndPoint} of the same type.
 * A {@link EndPoint} is closable.
 * 
 * @author Silvan Wyss
 * @month 2017-04
 * @lines 160
 */
public abstract class EndPoint extends OptionalClosableElement implements ISender {
	
	//attribute
	private final boolean hasRequestedConnection;
	
	//optional attributes
	private String target;
	private IReceiver receiver;
	
	//package-visible constructor
	/**
	 * Creates a new {@link EndPoint}.
	 * 
	 * @param hasRequestedConnection
	 */
	EndPoint(final boolean hasRequestedConnection) {
		this.hasRequestedConnection = hasRequestedConnection;
	}

	//method
	/**
	 * @return the target of the current {@link EndPoint}.
	 * @throws ArgumentMissesAttributeException if the current {@link EndPoint} does not have a target.
	 */
	public final String getTarget() {
		
		//Checks if the current EndPoint has a target.
		//For a better performance, this implementation does not use all comfortable methods.
		if (this.target == null) {
			throw new ArgumentMissesAttributeException(this, VariableNameCatalogue.TARGET);
		}
		
		return target;
	}
	
	//method
	/**
	 * @return true if the current {@link EndPoint} has a receiver.
	 */
	public final boolean hasReceiver() {
		return (receiver != null);
	}
	
	//method
	/**
	 * @return true if the current {@link EndPoint} has requested the connection.
	 */
	public final boolean hasRequestedConnection() {
		return hasRequestedConnection;
	}
	
	//method
	/**
	 * @return true if the current {@link EndPoint} has a target.
	 */
	public final boolean hasTarget() {
		return (target != null);
	}
	
	//method
	/**
	 * @return true if the current {@link EndPoint} is a local {@link EndPoint}.
	 */
	public final boolean isLocalEndPoint() {
		return !isNetEndPoint();
	}
	
	//abstract method
	/**
	 * @return true if the current {@link EndPoint} is a net {@link EndPoint}.
	 */
	public abstract boolean isNetEndPoint();
	
	//method
	/**
	 * Sets the receiver of the current {@link EndPoint}.
	 * 
	 * @param receiver
	 * @throws NullArgumentException if the given receiver is null.
	 * @throws InvalidArgumentException if the current {@link EndPoint} is closed.
	 */
	public final void setReceiver(final IReceiver receiver) {
		
		//Checks if the current EndPoint is alive.
		supposeIsAlive();
		
		//Checks if the given receiver is not null.
		Validator.suppose(receiver).isOfType(IReceiver.class);
		
		//Sets the receiver of the current EndPoint.
		this.receiver = receiver;
	}
	
	//method
	/**
	 * Lets the current {@link EndPoint} receive the given message.
	 * 
	 * @param message
	 */
	protected void receive(final String message) {
		getRefReceiver().receive(message);
	}
	
	//method
	/**
	 * Sets the target of the current {@link EndPoint}.
	 * 
	 * @param target
	 * @throws NullArgumentException if the given target is null.
	 * @throws InvalidArgumentException if the given target is blank.
	 * @throws InvalidArgumentException if the current net {@link EndPoint} is closed.
	 */
	protected final void setTarget(final String target) {
		
		//Checks if the current net EndPoint is alive.
		supposeIsAlive();
				
		//Checks if the given target is not null or blank.
		Validator.suppose(target).thatIsNamed(VariableNameCatalogue.TARGET).isNotBlank();
		
		//Sets the target of the current EndPoint.
		this.target = target;
	}
	
	//method
	/**
	 * @return the receiver of the current {@link EndPoint}.
	 * @throws ArgumentMissesAttributeException if the current {@link EndPoint} does not have a receiver.
	 */
	private IReceiver getRefReceiver() {
		
		//Checks if the current EndPoint has a receiver.
		//For a better performance, this implementation does not use all comfortable methods.
		if (receiver == null) {
			throw new ArgumentMissesAttributeException(this, IReceiver.class);
		}
		
		return receiver;
	}
}
