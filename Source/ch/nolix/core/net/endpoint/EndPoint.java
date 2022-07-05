//package declaration
package ch.nolix.core.net.endpoint;

import ch.nolix.core.environment.nolixenvironment.NolixEnvironment;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.coreapi.functionuniversalapi.IElementTaker;
import ch.nolix.coreapi.programcontrolapi.processproperty.ConnectionOrigin;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;

//class
/**
 * A {@link EndPoint} can send messages to an other {@link EndPoint} of the same type.
 * A {@link EndPoint} is closable.
 * 
 * @author Silvan Wyss
 * @date 2017-05-06
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
		
		GlobalValidator.assertThat(connectionOrigin).thatIsNamed(ConnectionOrigin.class).isNotNull();
		
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
		
		GlobalValidator.assertThat(connectionOrigin).thatIsNamed(ConnectionOrigin.class).isNotNull();
		
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
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.TARGET);
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
		GlobalValidator.assertThat(receiver).thatIsNamed(LowerCaseCatalogue.RECEIVER).isNotNull();
		
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
	 * @throws ClosedArgumentException if the current {@link EndPoint} is closed.
	 */
	protected final void assertIsOpen() {
		if (isClosed()) {
			throw ClosedArgumentException.forArgument(this);
		}
	}
	
	//method
	/**
	 * @return the receiver of the current {@link EndPoint}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link EndPoint} does not have a receiver.
	 */
	protected final IElementTaker<String> getRefReceiver() {
		
		if (hasReceiver()) {
			return receiver;
		}
		
		GlobalSequencer
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
		GlobalValidator.assertThat(target).thatIsNamed(LowerCaseCatalogue.TARGET).isNotBlank();
		
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
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.RECEIVER);
		}
	}
}
