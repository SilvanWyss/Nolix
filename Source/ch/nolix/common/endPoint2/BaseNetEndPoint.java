//package declaration
package ch.nolix.common.endPoint2;

//own imports
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.processProperty.ConnectionOrigin;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link BaseNetEndPoint} can send messages to an other {@link BaseNetEndPoint} that is on:
 * -the same process on the local computer
 * -another process on the local computer
 * -another process on another computer
 * 
 * A {@link BaseNetEndPoint} supports the WebSocket protocol and can communicate with a WebSocket.
 * The WebSocket protocol is complicated. Because:
 * -A WebSocket requires a HTTP handshake.
 * -A WebSocket puts its messages in frames that need to be encoded awkwardly.
 * -A WebSocket sends messages, that belong together, in separate lines.
 * The WebSocket protocol was designed this way because of security reasons.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 170
 */
public abstract class BaseNetEndPoint extends EndPoint {
	
	//default value
	public static final int DEFAULT_TIMEOUT_IN_MILLISECONDS = 10000;
	
	//attribute
	private boolean hasTargetInfo = false;
	
	//TEMP
	BaseNetEndPoint(final ConnectionOrigin connectionOrigin) {
		
		super(connectionOrigin);
		
		confirmReceivedTargetInfo();
	}
	
	//TEMP
	BaseNetEndPoint(final ConnectionOrigin connectionOrigin, final String target) {
		
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
	void receiveRawMessageInBackground(final String rawMessage) {
		Sequencer.runInBackground(() -> receiveRawMessage(rawMessage));
	}
	
	//method
	/**
	 * Confirms that the current {@link BaseNetEndPoint} has a target info.
	 * 
	 * @throws InvalidArgumentException if the current {@link BaseNetEndPoint} has already a target info.
	 */
	private void confirmReceivedTargetInfo() {
		
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
		
		if (!hasTarget()) {
			return String.valueOf(NetEndPointProtocol.MAIN_TARGET_PREFIX);
		}
		
		return (NetEndPointProtocol.TARGET_PREFIX + getTarget());
	}
	
	//method
	private void receiveMessage(final String message) {
		
		//Asserts that the current NetEndPoint is open.
		assertIsOpen();
		
		getRefReceiver().receive(message);
	}
	
	//method
	private void receiveRawMessage(final String rawMessage) {
		
		//Enumerates the first character of the given rawMessage.
		switch (rawMessage.charAt(0)) {
			case NetEndPointProtocol.TARGET_PREFIX:
				setTarget(rawMessage.substring(1));
				confirmReceivedTargetInfo();
				break;
			case NetEndPointProtocol.MAIN_TARGET_PREFIX:
				confirmReceivedTargetInfo();
				break;
			case NetEndPointProtocol.MESSAGE_PREFIX:
				receiveMessage(rawMessage.substring(1));
				break;
			case NetEndPointProtocol.CLOSE_PREFIX:
				Validator.assertThat(rawMessage).thatIsNamed("raw message").hasLength(1);
				close();
				break;
			default:
				throw new InvalidArgumentException("raw message", rawMessage, "is not valid");
		}
	}
}
