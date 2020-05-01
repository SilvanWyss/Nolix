//package declaration
package ch.nolix.common.endPoint2;

//Java imports
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.common.commonTypeHelper.InputStreamHelper;
import ch.nolix.common.invalidArgumentException.ClosedArgumentException;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.wrapperException.WrapperException;

//class
final class NetEndPointProcessorForRegularCounterpart implements INetEndPointProcessor {
	
	//attributes
	private final BaseNetEndPoint parentNetEndPoint;
	private final OutputStream outputStream;
	private final InputStream inputStream;
	
	//constructor
	public NetEndPointProcessorForRegularCounterpart(
		final BaseNetEndPoint parentNetEndPoint,
		final InputStream inputStream
	) {
		
		Validator.assertThat(parentNetEndPoint).thatIsNamed("parent NetEndPoint").isNotNull();
		Validator.assertThat(inputStream).thatIsNamed(InputStream.class).isNotNull();
		
		this.parentNetEndPoint = parentNetEndPoint;
		this.inputStream = inputStream;
		try {
			outputStream = parentNetEndPoint.getRefSocket().getOutputStream();
		}
		catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
		
		Sequencer.runInBackground(() -> listenToMessages());
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
		catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
	
	//method
	private void listenToMessages() {
		while (parentNetEndPoint.isOpen()) {
			
			final var line = InputStreamHelper.readLineFrom(inputStream);
			
			if (line.isEmpty()) {
				parentNetEndPoint.close();
				break;
			}
			
			parentNetEndPoint.receiveRawMessageInBackground(line);
		}
	}
}
