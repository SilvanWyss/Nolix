//package declaration
package ch.nolix.common.net.endpoint;

//Java imports
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import ch.nolix.common.environment.nolixenvironment.NolixEnvironment;
import ch.nolix.common.errorcontrol.exception.WrapperException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.net.websocket.WebSocketFrame;
import ch.nolix.common.net.websocket.WebSocketFrameOpcodeMeaning;
import ch.nolix.common.programcontrol.processproperty.ConnectionOrigin;
import ch.nolix.common.programcontrol.processproperty.TargetInfoState;
import ch.nolix.common.programcontrol.sequencer.Sequencer;

//class
final class WebEndPoint extends BaseNetEndPoint {
	
	//attributes
	private final Socket socket;
	private final InputStream socketInputStream;
	private final OutputStream socketOutputStream;
	
	//constructor
	public WebEndPoint(
		final Socket socket,
		final InputStream socketInputStream,
		final OutputStream socketOutputStream
	) {
		
		super(ConnectionOrigin.ACCEPTED_CONNECTION, TargetInfoState.WAITS_TO_TARGET_INFO);
		
		Validator.assertThat(socket).thatIsNamed(Socket.class).isNotNull();
		Validator.assertThat(socketInputStream).thatIsNamed("socket input stream").isNotNull();
		Validator.assertThat(socketOutputStream).thatIsNamed("socket output stream").isNotNull();
		
		this.socket = socket;
		this.socketInputStream = socketInputStream;
		this.socketOutputStream = socketOutputStream;
		
		setPreCloseAction(this::runPreClose);
		
		new WebEndPointMessageListener(this).start();
		
		waitToTargetInfo();
	}
	
	//method
	@Override
	public EndPointType getType() {
		return EndPointType.WEB_SOCKET;
	}
	
	//method
	@Override
	public boolean isWebEndPoint() {
		return true;
	}
	
	//method
	@Override
	protected void sendRawMessage(final String rawMessage) {
		sendFrame(new WebSocketFrame(true, WebSocketFrameOpcodeMeaning.TEXT_FRAME, false, rawMessage));
	}
	
	//method
	InputStream getRefInputStream() {
		return socketInputStream;
	}
	
	//method
	void receiveControlFrame(final WebSocketFrame controlFrame) {
		switch (controlFrame.getOpcodeMeaning()) {
			case PING:
				sendPongFrameForPingFrame(controlFrame);
				break;
			case CONNECTION_CLOSE:
				close();
				break;
			default:
				throw new InvalidArgumentException("control frame", controlFrame, "is not valid");
		}
	}
	
	//method
	private boolean canWork() {
		return !socket.isClosed();
	}
	
	//method
	private void runPreClose() {
		
		if (canWork()) {
			sendRawMessage(NetEndPointProtocol.CLOSE_PREFIX);
		}
		
		try {
			socket.close();
		} catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
	
	//method
	private void sendBytes(final byte[] bytes) {
		
		/*
		 * TODO: Call assertIsOpen() here
		 * TODO: IMPORTANT: Make sure that all GUI clients and console clients can still transfer their close messages.
		 */
		
		try {
			socketOutputStream.write(bytes);
			socketOutputStream.flush();
		} catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
	
	//method
	private void sendFrame(final WebSocketFrame frame) {
		sendBytes(frame.toBytes());
	}
	
	//method
	private void sendPongFrameForPingFrame(final WebSocketFrame pingFrame) {
		sendFrame(pingFrame.createPongFrame());
	}
	
	//method
	private void waitToTargetInfo() {
		
		Sequencer
		.forMaxMilliseconds(NolixEnvironment.DEFAULT_CONNECT_AND_DISCONNECT_TIMEOUT_IN_MILLISECONDS)
		.waitUntil(this::hasTargetInfo);
		
		if (!hasTargetInfo()) {
			throw new InvalidArgumentException(this, "reached timeout while waiting to target.");
		}
	}
}
