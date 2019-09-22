//package declaration
package ch.nolix.common.endPoint2;

//Java imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.common.invalidArgumentExceptions.ClosedArgumentException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.webSocket.WebSocketCompleteMessage;
import ch.nolix.common.webSocket.WebSocketFrame;
import ch.nolix.common.webSocket.WebSocketFrameOpcodeMeaning;

//package-visible class
final class NetEndPointProcessorForWebSocketCounterpart implements INetEndPointProcessor {
	
	//attributes
	private final NetEndPoint parentNetEndPoint;
	private final OutputStream outputStream;
	
	//constructor
	public NetEndPointProcessorForWebSocketCounterpart(final NetEndPoint parentNetEndPoint) {
		
		Validator.suppose(parentNetEndPoint).thatIsNamed("parent NetEndPoint").isNotNull();
		
		this.parentNetEndPoint = parentNetEndPoint;
		try {
			outputStream = parentNetEndPoint.getRefSocket().getOutputStream();
		}
		catch (final IOException IOException) {
			throw new RuntimeException(IOException);
		}
		
		Sequencer.runInBackground(() -> listenToMessages());
	}
	
	//method
	@Override
	public NetEndPointCounterpartType getCounterpartType() {
		return NetEndPointCounterpartType.WEBSOCKET_NET_END_POINT;
	}
	
	//method
	@Override
	public void sendRawMessage(final String rawMessage) {
		sendFrame(new WebSocketFrame(true, WebSocketFrameOpcodeMeaning.TEXT_FRAME, false, rawMessage));
	}
	
	//method
	private void listenToMessages() {
		try (
			final var bufferedReader =
			new BufferedReader(new InputStreamReader(parentNetEndPoint.getRefSocket().getInputStream()))
		) {
			final var inputStream = parentNetEndPoint.getRefSocket().getInputStream();
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
		catch (final IOException IOException) {
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
		catch (final IOException IOException) {
			throw new RuntimeException(IOException);
		}
	}
	
	//method
	private void sendPongFrameForPingFrame(final WebSocketFrame pingFrame) {
		sendFrame(pingFrame.createPongFrame());
	}
}
