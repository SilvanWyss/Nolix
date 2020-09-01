//package declaration
package ch.nolix.common.endPoint2;

//own imports
import ch.nolix.common.closeableElement.CloseController;
import ch.nolix.common.closeableElement.ICloseableElement;
import ch.nolix.common.communicationAPI.IReceiver;
import ch.nolix.common.communicationAPI.ISender;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentException.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.nolixEnvironment.NolixEnvironment;
import ch.nolix.common.processProperty.ConnectionOrigin;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link EndPoint} can send messages to an other {@link EndPoint} of the same type.
 * A {@link EndPoint} is closable.
 * 
 * @author Silvan Wyss
 * @month 2017-04
 * @lines 200
 */
public abstract class EndPoint implements ICloseableElement, ISender {
	
	//attributes
	private final boolean hasRequestedConnection;
	private final CloseController closeController = new CloseController(this);
	
	//optional attributes
	private String target;
	private IReceiver receiver;
	
	//constructor
	/**
	 * Creates a new {@link EndPoint}.
	 * 
	 * @param connectionOrigin
	 * @throws ArgumentIsNullException if the given connectionOrigin is null.
	 */
	EndPoint(final ConnectionOrigin connectionOrigin) {
		
		Validator.assertThat(connectionOrigin).thatIsNamed(ConnectionOrigin.class).isNotNull();
		
		hasRequestedConnection = connectionOrigin == ConnectionOrigin.REQUESTED_CONNECTION;
	}
	
	//constructor
	/**
	 * Creates a new {@link EndPoint} with the given target.
	 * 
	 * @param connectionOrigin
	 * @param target
	 * @throws ArgumentIsNullException if the given connectionOrigin is null.
	 * @throws ArgumentIsNullException if the given target is null.
	 * @throws InvalidArgumentException if the given target is blank.
	 */
	EndPoint(final ConnectionOrigin connectionOrigin, final String target) {
		
		Validator.assertThat(connectionOrigin).thatIsNamed(ConnectionOrigin.class).isNotNull();
		
		hasRequestedConnection = connectionOrigin == ConnectionOrigin.REQUESTED_CONNECTION;
		
		setTarget(target);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
	}
	
	//method declaration
	/**
	 * @return the type of the current {@link EndPoint}.
	 */
	public abstract EndPointType getType();
	
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
	
	//method declaration
	/**
	 * @return true if the current {@link EndPoint} is a web {@link EndPoint}.
	 */
	public abstract boolean isWebEndPoint();
	
	//method
	/**
	 * Sets the receiver of the current {@link EndPoint}.
	 * 
	 * @param receiver
	 * @throws ArgumentIsNullException if the given receiver is null.
	 * @throws ClosedArgumentException if the current {@link EndPoint} is closed.
	 */
	public final void setReceiver(final IReceiver receiver) {
		
		//Asserts that the given receiver is not null.
		Validator.assertThat(receiver).isOfType(IReceiver.class);
		
		//Asserts that the current EndPoint is open.
		assertIsOpen();
		
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
		.waitUntil(this::hasReceiver);
		
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
	 * @throws ClosedArgumentException if the current {@link EndPoint} is closed.
	 */
	protected final void setTarget(final String target) {
		
		//Asserts that the given target is not null or blank.
		Validator.assertThat(target).thatIsNamed(VariableNameCatalogue.TARGET).isNotBlank();
		
		//Asserts that the current net EndPoint is open.
		assertIsOpen();
		
		//Sets the target of the current EndPoint.
		this.target = target;
	}
}
