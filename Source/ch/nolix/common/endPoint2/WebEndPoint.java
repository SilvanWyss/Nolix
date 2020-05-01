//package declaration
package ch.nolix.common.endPoint2;

//Java imports
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

//own imports
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.nolixEnvironment.NolixEnvironment;
import ch.nolix.common.processProperty.ConnectionOrigin;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.webSocket.WebSocketFrame;
import ch.nolix.common.webSocket.WebSocketFrameOpcodeMeaning;
import ch.nolix.common.wrapperException.WrapperException;

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
		
		super(ConnectionOrigin.ACCEPTED_CONNECTION);
		
		Validator.assertThat(socket).thatIsNamed(Socket.class).isNotNull();
		Validator.assertThat(socketInputStream).thatIsNamed("socket input stream").isNotNull();
		Validator.assertThat(socketOutputStream).thatIsNamed("socket output stream").isNotNull();
		
		this.socket = socket;
		this.socketInputStream = socketInputStream;
		this.socketOutputStream = socketOutputStream;
		
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
	protected void noteClose() {
		if (canWork()) {
			try {
				sendRawMessage(NetEndPointProtocol.CLOSE_PREFIX);
				socket.close();
			}
			catch (final IOException pIOException) {
				throw new WrapperException(pIOException);
			}
		}
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
	
	void receiveControlFrame(final WebSocketFrame controlFrame) {
		switch (controlFrame.getOpcodeMeaning()) {
			case PING:
				sendPongFrameForPingFrame(controlFrame);
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
	private void sendBytes(final byte[] bytes) {

		assertIsOpen();
		
		try {
			socketOutputStream.write(bytes);
			socketOutputStream.flush();
		}
		catch (final IOException pIOException) {
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
