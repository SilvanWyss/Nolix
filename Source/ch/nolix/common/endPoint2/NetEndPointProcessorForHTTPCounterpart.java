//package declaration
package ch.nolix.common.endPoint2;

//Java imports
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import ch.nolix.common.invalidArgumentException.ClosedArgumentException;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.wrapperException.WrapperException;

//class
final class NetEndPointProcessorForHTTPCounterpart implements INetEndPointProcessor {
	
	//attributes
	private final BaseNetEndPoint parentNetEndPoint;
	private final OutputStream outputStream;
	
	//constructor
	public NetEndPointProcessorForHTTPCounterpart(final BaseNetEndPoint parentNetEndPoint) {
		
		Validator.assertThat(parentNetEndPoint).thatIsNamed("parent NetEndPoint").isNotNull();
		
		this.parentNetEndPoint = parentNetEndPoint;
		try {
			outputStream = parentNetEndPoint.getRefSocket().getOutputStream();
		}
		catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
	
	//method
	@Override
	public NetEndPointCounterpartType getCounterpartType() {
		return NetEndPointCounterpartType.HTTP_NET_END_POINT;
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
		catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
}
