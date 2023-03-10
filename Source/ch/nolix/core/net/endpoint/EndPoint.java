//package declaration
package ch.nolix.core.net.endpoint;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.netapi.netproperty.ConnectionType;
import ch.nolix.coreapi.netapi.netproperty.PeerType;
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
	
	//constant
	private static final int CONNECT_TIMEOUT_IN_MILLISECONDS = 500;
	
	//attribute
	private final CloseController closeController = CloseController.forElement(this);
	
	//optional attribute
	private String target;
	
	//optional attribute
	private IElementTaker<String> receiver;
	
	//constructor
	public EndPoint() {}
	
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
	EndPoint(final String target) {
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
	
	//method
	public abstract PeerType getPeerType();
	
	//method declaration
	/**
	 * @return the type of the current {@link EndPoint}.
	 */
	public abstract ConnectionType getType();
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * @return the target of the current {@link EndPoint}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link EndPoint} does not have a target.
	 */
	public final String getCustomTargetSlot() {
		
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
	 * @return true if the current {@link EndPoint} has a target.
	 */
	public final boolean hasCustomTargetSlot() {
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
	public abstract void sendMessage(String message);
	
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
		
		GlobalSequencer.forMaxMilliseconds(CONNECT_TIMEOUT_IN_MILLISECONDS).waitUntil(this::hasReceiver);
		
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
