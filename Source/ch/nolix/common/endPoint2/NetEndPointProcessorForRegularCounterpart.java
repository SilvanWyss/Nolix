//package declaration
package ch.nolix.common.endPoint2;

//Java imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.common.invalidArgumentExceptions.ClosedArgumentException;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.validator.Validator;

//class
final class NetEndPointProcessorForRegularCounterpart implements INetEndPointProcessor {
	
	//attributes
	private final NetEndPoint parentNetEndPoint;
	private final OutputStream outputStream;
	private final BufferedReader bufferedReader;
	
	//constructor
	public NetEndPointProcessorForRegularCounterpart(
		final NetEndPoint parentNetEndPoint,
		final BufferedReader bufferedReader
	) {
		
		Validator.suppose(parentNetEndPoint).thatIsNamed("parent NetEndPoint").isNotNull();
		Validator.suppose(bufferedReader).thatIsNamed(BufferedReader.class).isNotNull();
		
		this.parentNetEndPoint = parentNetEndPoint;
		this.bufferedReader = bufferedReader;
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
		return NetEndPointCounterpartType.REGULAR_NET_END_POINT;
	}
	
	//method
	@Override
	public void sendRawMessage(final String rawMessage) {
		
		if (parentNetEndPoint.isClosed()) {
			throw new ClosedArgumentException(parentNetEndPoint);
		}
		
		try {
			outputStream.write((rawMessage + "\r\n").getBytes(StandardCharsets.UTF_8));
			outputStream.flush();
		}
		catch (final IOException IOException) {
			throw new RuntimeException(IOException);
		}
	}
	
	//method
	private void listenToMessages() {
		try {
			while (parentNetEndPoint.isOpen()) {
				
				final var line = bufferedReader.readLine();
				
				if (line == null) {
					parentNetEndPoint.close();
					break;
				}
				
				parentNetEndPoint.receiveRawMessageInBackground(line);
			}
		}
		catch (final IOException IOException) {
			parentNetEndPoint.close();
		}
	}
}
