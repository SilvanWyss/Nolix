//package declaration
package ch.nolix.common.net.endpoint;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.environment.nolixenvironment.NolixEnvironment;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.programcontrol.groupcloseable.CloseController;
import ch.nolix.common.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.common.programcontrol.processproperty.ConnectionOrigin;
import ch.nolix.common.programcontrol.sequencer.Sequencer;

//class
/**
 * A {@link EndPoint} can send messages to an other {@link EndPoint} of the same type.
 * A {@link EndPoint} is closable.
 * 
 * @author Silvan Wyss
 * @date 2017-05-06
 * @lines 220
 */
public abstract class EndPoint implements GroupCloseable {
	
	//attributes
	private final boolean requestedConnection;
	private final CloseController closeController = new CloseController(this);
	
	//optional attributes
	private String target;
	private IElementTaker<String> receiver;
	
	//constructor
	/**
	 * Creates a new {@link EndPoint}.
	 * 
	 * @param connectionOrigin
	 * @throws ArgumentIsNullException if the given connectionOrigin is null.
	 */
	EndPoint(final ConnectionOrigin connectionOrigin) {
		
		Validator.assertThat(connectionOrigin).thatIsNamed(ConnectionOrigin.class).isNotNull();
		
		requestedConnection = connectionOrigin == ConnectionOrigin.REQUESTED_CONNECTION;
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
		
		requestedConnection = connectionOrigin == ConnectionOrigin.REQUESTED_CONNECTION;
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
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * @return the target of the current {@link EndPoint}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link EndPoint} does not have a target.
	 */
	public final String getTarget() {
		
		//Asserts that the current EndPoint has a target.
		if (this.target == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.TARGET);
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
		return requestedConnection;
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
	public final void setReceiver(final IElementTaker<String> receiver) {
		
		//Asserts that the given receiver is not null.
		Validator.assertThat(receiver).thatIsNamed(LowerCaseCatalogue.RECEIVER).isNotNull();
		
		//Asserts that the current EndPoint is open.
		assertIsOpen();
		
		//Sets the receiver of the current EndPoint.
		this.receiver = receiver;
	}
	
	//method declaration
	/**
	 * Lets the current {@link EndPoint} send the given message.
	 * 
	 * @param message
	 */
	public abstract void send(String message);
	
	//method
	/**
	 * @return the receiver of the current {@link EndPoint}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link EndPoint} does not have a receiver.
	 */
	protected final IElementTaker<String> getRefReceiver() {
		
		if (hasReceiver()) {
			return receiver;
		}
		
		Sequencer
		.forMaxMilliseconds(NolixEnvironment.DEFAULT_CONNECT_AND_DISCONNECT_TIMEOUT_IN_MILLISECONDS)
		.waitUntil(this::hasReceiver);
		
		assertHasReceiver();
		
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
		Validator.assertThat(target).thatIsNamed(LowerCaseCatalogue.TARGET).isNotBlank();
		
		//Asserts that the current net EndPoint is open.
		assertIsOpen();
		
		//Sets the target of the current EndPoint.
		this.target = target;
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link EndPoint} does not have a receiver.
	 */
	private void assertHasReceiver() {
		if (!hasReceiver()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.RECEIVER);
		}
	}
}
