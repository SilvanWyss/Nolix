//package declaration
package ch.nolix.common.endPoint2;

//Java imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import ch.nolix.common.invalidArgumentException.ClosedArgumentException;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.wrapperException.WrapperException;

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
		
		Validator.assertThat(parentNetEndPoint).thatIsNamed("parent NetEndPoint").isNotNull();
		Validator.assertThat(bufferedReader).thatIsNamed(BufferedReader.class).isNotNull();
		
		this.parentNetEndPoint = parentNetEndPoint;
		this.bufferedReader = bufferedReader;
		try {
			outputStream = parentNetEndPoint.getRefSocket().getOutputStream();
		}
		catch (final IOException IOException) {
			throw new WrapperException(IOException);
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
			throw new WrapperException(IOException);
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
