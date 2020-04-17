//package declaration
package ch.nolix.common.endPoint2;

//own imports
import ch.nolix.common.closableElement.ClosableElement;
import ch.nolix.common.communicationAPI.IReceiver;
import ch.nolix.common.communicationAPI.ISender;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.nolixEnvironment.NolixEnvironment;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link EndPoint} can send messages to an other {@link EndPoint} of the same type.
 * A {@link EndPoint} is closable.
 * 
 * @author Silvan Wyss
 * @month 2017-04
 * @lines 160
 */
public abstract class EndPoint extends ClosableElement implements ISender {
	
	//attribute
	private final boolean hasRequestedConnection;
	
	//optional attributes
	private String target;
	private IReceiver receiver;
	
	//constructor
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
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link EndPoint} does not have a target.
	 */
	public final String getTarget() {
		
		//Asserts that the current EndPoint has a target.
		//For a better performance, this implementation does not use all comfortable methods.
		if (this.target == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.TARGET);
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
	
	//method declaration
	/**
	 * @return true if the current {@link EndPoint} is a net {@link EndPoint}.
	 */
	public abstract boolean isNetEndPoint();
	
	//method
	/**
	 * Sets the receiver of the current {@link EndPoint}.
	 * 
	 * @param receiver
	 * @throws ArgumentIsNullException if the given receiver is null.
	 * @throws InvalidArgumentException if the current {@link EndPoint} is closed.
	 */
	public final void setReceiver(final IReceiver receiver) {
		
		//Asserts that the current EndPoint is alive.
		supposeIsAlive();
		
		//Asserts that the given receiver is not null.
		Validator.assertThat(receiver).isOfType(IReceiver.class);
		
		//Sets the receiver of the current EndPoint.
		this.receiver = receiver;
	}
	
	//method
	/**
	 * @return the receiver of the current {@link EndPoint}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link EndPoint} does not have a receiver.
	 */
	protected final IReceiver getRefReceiver() {
		
		if (hasReceiver()) {
			return receiver;
		}
		
		Sequencer
		.forMaxMilliseconds(NolixEnvironment.DEFAULT_CONNECT_AND_DISCONNECT_TIMEOUT_IN_MILLISECONDS)
		.waitUntil(() -> hasReceiver());
		
		if (!hasReceiver()) {
			throw new ArgumentDoesNotHaveAttributeException(this, IReceiver.class);
		}
		
		return receiver;
	}

	//method
	/**
	 * Sets the target of the current {@link EndPoint}.
	 * 
	 * @param target
	 * @throws ArgumentIsNullException if the given target is null.
	 * @throws InvalidArgumentException if the given target is blank.
	 * @throws InvalidArgumentException if the current net {@link EndPoint} is closed.
	 */
	protected final void setTarget(final String target) {
		
		//Asserts that the current net EndPoint is alive.
		supposeIsAlive();
				
		//Asserts that the given target is not null or blank.
		Validator.assertThat(target).thatIsNamed(VariableNameCatalogue.TARGET).isNotBlank();
		
		//Sets the target of the current EndPoint.
		this.target = target;
	}
}
