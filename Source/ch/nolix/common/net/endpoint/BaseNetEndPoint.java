//package declaration
package ch.nolix.common.net.endpoint;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.programcontrol.processproperty.ConnectionOrigin;
import ch.nolix.common.programcontrol.processproperty.TargetInfoState;
import ch.nolix.common.programcontrol.sequencer.Sequencer;

//class
/**
 * A {@link BaseNetEndPoint} can send messages to an other {@link BaseNetEndPoint} that is on:
 * -the same process on the local computer
 * -another process on the local computer
 * -another process on another computer
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 210
 */
public abstract class BaseNetEndPoint extends EndPoint {
	
	//constant
	private static final String RAW_MESSAGE_VARIABLE_NAME = "raw message";
	
	//attribute
	private boolean hasTargetInfo;
	
	//constructor
	/**
	 * Creates a new {@link BaseNetEndPoint} with the given connectionOrigin and connectionOrigin.
	 * 
	 * @param connectionOrigin
	 * @param targetInfoState
	 * @throws ArgumentIsNullException if the given connectionOrigin is null.
	 * @throws ArgumentIsNullException if the given targetInfoState is null.
	 */
	BaseNetEndPoint(final ConnectionOrigin connectionOrigin, final TargetInfoState targetInfoState) {
		
		//Calls constructor of the base class.
		super(connectionOrigin);
		
		//Asserts that the given targetInfoState is not null.
		Validator.assertThat(targetInfoState).thatIsNamed(TargetInfoState.class).isNotNull();
		
		if (targetInfoState == TargetInfoState.RECEIVED_TARGET_INFO) {
			confirmReceivedTargetInfo();
		}
	}
	
	//constructor
	/**
	 * Creates a new {@link BaseNetEndPoint} with the given connectionOrigin and target.
	 * 
	 * @param connectionOrigin
	 * @param target
	 * @throws ArgumentIsNullException if the given connectionOrigin is null.
	 * @throws ArgumentIsNullException if the given target is null.
	 * @throws InvalidArgumentException if the given target is blank.
	 */
	BaseNetEndPoint(final ConnectionOrigin connectionOrigin, final String target) {
		
		//Calls constructor of the base class.
		super(connectionOrigin, target);
				
		confirmReceivedTargetInfo();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isNetEndPoint() {
		return true;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void send(final String message) {
		sendRawMessage(NetEndPointProtocol.MESSAGE_PREFIX + message);
	}
	
	//method
	/**
	 * @return true if the current {@link BaseNetEndPoint} has a target info.
	 */
	protected final boolean hasTargetInfo() {
		return hasTargetInfo;
	}
	
	//method
	/**
	 * Lets the current {@link BaseNetEndPoint} send the given rawMessage.
	 * 
	 * @param rawMessage
	 */
	protected final void sendRawMessage(final char rawMessage) {
		
		//Calls other method.
		sendRawMessage(String.valueOf(rawMessage));
	}
	
	//method declaration
	/**
	 * Lets the current {@link BaseNetEndPoint} send the given rawMessage.
	 * 
	 * @param rawMessage
	 */
	protected abstract void sendRawMessage(String rawMessage);
	
	//method
	/**
	 * Sends the target message of the current {@link BaseNetEndPoint}
	 * to the counterpart of the current {@link BaseNetEndPoint}.
	 */
	protected final void sendTargetMessage() {
		sendRawMessage(getTargetMessage());
	}
	
	//method
	/**
	 * Lets the current {@link BaseNetEndPoint} receive the given rawMessage asynchronously.
	 * 
	 * @param rawMessage
	 */
	final void receiveRawMessageInBackground(final String rawMessage) {
		Sequencer.runInBackground(() -> receiveRawMessage(rawMessage));
	}
	
	//method
	/**
	 * Confirms that the current {@link BaseNetEndPoint} has a target info.
	 * 
	 * @throws InvalidArgumentException if the current {@link BaseNetEndPoint} has already a target info.
	 */
	private void confirmReceivedTargetInfo() {
		
		//Asserts that the current BaseNetEndPoint has already a target info.
		if (hasTargetInfo()) {
			throw new InvalidArgumentException(this, "has already a target info");
		}
		
		hasTargetInfo = true;
	}
	
	//method
	/**
	 * @return the target message of the current {@link BaseNetEndPoint}.
	 */
	private String getTargetMessage() {
		
		//Handles the case that the current BaseNetEndPoint has a target.
		if (!hasTarget()) {
			return String.valueOf(NetEndPointProtocol.MAIN_TARGET_PREFIX);
		}
		
		//Handles the case that the current BaseNetEndPoint does not have a target.
		return (NetEndPointProtocol.TARGET_PREFIX + getTarget());
	}
	
	//method
	/**
	 * Lets the current {@link BaseNetEndPoint} receive the given message.
	 * 
	 * @param message
	 * @throws ClosedArgumentException if the current {@link BaseNetEndPoint} is closed.
	 */
	private void receiveMessage(final String message) {
		
		//Asserts that the current NetEndPoint is open.
		assertIsOpen();
		
		getRefReceiver().run(message);
	}
	
	//method
	/**
	 * Lets the current {@link BaseNetEndPoint} receive the given rawMessage.
	 * 
	 * @param rawMessage
	 * @throws InvalidArgumentException if the given rawMessage is not valid.
	 */
	private void receiveRawMessage(final String rawMessage) {
		
		//Enumerates the first character of the given rawMessage.
		switch (rawMessage.charAt(0)) {
			case NetEndPointProtocol.MAIN_TARGET_PREFIX:
				
				if (!rawMessage.equals(String.valueOf(NetEndPointProtocol.MAIN_TARGET_PREFIX))) {
					throw new InvalidArgumentException(RAW_MESSAGE_VARIABLE_NAME, rawMessage, "is not valid");
				}
				
				confirmReceivedTargetInfo();
				
				break;
			case NetEndPointProtocol.TARGET_PREFIX:
				setTarget(rawMessage.substring(1));
				confirmReceivedTargetInfo();
				break;
			case NetEndPointProtocol.MESSAGE_PREFIX:
				receiveMessage(rawMessage.substring(1));
				break;
			case NetEndPointProtocol.CLOSE_PREFIX:
				Validator.assertThat(rawMessage).thatIsNamed(RAW_MESSAGE_VARIABLE_NAME).hasLength(1);
				close();
				break;
			default:
				throw new InvalidArgumentException(RAW_MESSAGE_VARIABLE_NAME, rawMessage, "is not valid");
		}
	}
}
