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
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.validator.Validator;

//package-visible class
final class NetEndPointProcessorForRegularCounterpart implements INetEndPointProcessor {
	
	//attributes
	private final NetEndPoint parentNetEndPoint;
	private final OutputStream outputStream;
	
	//constructor
	public NetEndPointProcessorForRegularCounterpart(final NetEndPoint parentNetEndPoint) {
		
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
		return NetEndPointCounterpartType.REGULAR_NET_END_POINT;
	}
	
	//method
	@Override
	public void sendRawMessage(final String rawMessage) {
		
		if (parentNetEndPoint.isClosed()) {
			throw new ClosedArgumentException(parentNetEndPoint);
		}
		
		try {
			outputStream.write(rawMessage.getBytes(StandardCharsets.UTF_8));
			outputStream.flush();
		}
		catch (final IOException IOException) {
			throw new RuntimeException(IOException);
		}
	}
	
	//method
	private void listenToMessages() {
		try (
			final var bufferedReader =
			new BufferedReader(new InputStreamReader(parentNetEndPoint.getRefSocket().getInputStream()))
		) {
			while (parentNetEndPoint.isOpen()) {
				parentNetEndPoint.receiveRawMessageInBackground(bufferedReader.readLine());
			}
		}
		catch (final IOException IOException) {
			parentNetEndPoint.close();
		}
	}
}
