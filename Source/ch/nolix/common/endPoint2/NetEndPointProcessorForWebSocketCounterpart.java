//package declaration
package ch.nolix.common.endPoint2;

//Java imports
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import ch.nolix.common.invalidArgumentException.ClosedArgumentException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.webSocket.WebSocketCompleteMessage;
import ch.nolix.common.webSocket.WebSocketFrame;
import ch.nolix.common.webSocket.WebSocketFrameOpcodeMeaning;
import ch.nolix.common.wrapperException.WrapperException;

//class
final class NetEndPointProcessorForWebSocketCounterpart implements INetEndPointProcessor {
	
	//attributes
	private final BaseNetEndPoint parentNetEndPoint;
	private final OutputStream outputStream;
	private final InputStream inputStream;
	
	//constructor
	public NetEndPointProcessorForWebSocketCounterpart(
		final BaseNetEndPoint parentNetEndPoint,
		final InputStream inputStream,
		String x
	) {
		
		Validator.assertThat(parentNetEndPoint).thatIsNamed("parent NetEndPoint").isNotNull();
		Validator.assertThat(inputStream).thatIsNamed(InputStream.class).isNotNull();
		
		this.parentNetEndPoint = parentNetEndPoint;
		try {
			outputStream = parentNetEndPoint.getRefSocket().getOutputStream();
		}
		catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
		this.inputStream = inputStream;
		sendRawMessageUnframed(x);
		Sequencer.runInBackground(() -> listenToMessages());
	}
	
	//method
	@Override
	public void sendRawMessage(final String rawMessage) {
		sendFrame(new WebSocketFrame(true, WebSocketFrameOpcodeMeaning.TEXT_FRAME, false, rawMessage));
	}

	//method
	public void sendRawMessageUnframed(final String message) {
		
		if (parentNetEndPoint.isClosed()) {
			throw new ClosedArgumentException(parentNetEndPoint);
		}
		
		try {
			outputStream.write(message.getBytes(StandardCharsets.UTF_8));
			outputStream.flush();
		}
		catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
	
	//method
	private void listenToMessages() {
		try {
			while (parentNetEndPoint.isOpen()) {
				parentNetEndPoint.receiveRawMessageInBackground(
					new String(
						new WebSocketCompleteMessage(
							inputStream,
							cf -> receiveControlFrame(cf)
						)
						.getMessageAsByteArray(),
						StandardCharsets.UTF_8
					)
				);
			}
		}
		catch (final Exception IOException) {
			parentNetEndPoint.close();
		}
	}
	
	// method
	private void receiveControlFrame(final WebSocketFrame controlFrame) {
		switch (controlFrame.getOpcodeMeaning()) {
			case PING:
				sendPongFrameForPingFrame(controlFrame);
				break;
			default:
				throw new InvalidArgumentException("control frame", controlFrame, "is not valid");
		}
	}
	
	//method
	private void sendFrame(final WebSocketFrame frame) {
		if (parentNetEndPoint.isClosed()) {
			throw new ClosedArgumentException(parentNetEndPoint);
		}
		
		try {
			outputStream.write(frame.toBytes());
			outputStream.flush();
		}
		catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
	
	//method
	private void sendPongFrameForPingFrame(final WebSocketFrame pingFrame) {
		sendFrame(pingFrame.createPongFrame());
	}
}
