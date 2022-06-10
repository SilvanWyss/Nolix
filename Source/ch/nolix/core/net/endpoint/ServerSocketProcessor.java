//package declaration
package ch.nolix.core.net.endpoint;

//Java imports
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalInputStreamHelper;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.container.SingleContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.http.HTTPRequest;
import ch.nolix.core.net.websocket.WebSocketHandShakeRequest;
import ch.nolix.core.programcontrol.worker.Worker;

//class
final class ServerSocketProcessor extends Worker {
	
	//attributes
	private final Server parentNetServer;
	private final Socket socket;
	private final InputStream socketInputStream;
	private final OutputStream socketOutputStream;
	
	//constructor
	public ServerSocketProcessor(final Server parentNetServer, final Socket socket) {
		
		GlobalValidator.assertThat(parentNetServer).thatIsNamed("parent NetServer").isNotNull();
		GlobalValidator.assertThat(socket).thatIsNamed(Socket.class).isNotNull();
				
		this.parentNetServer = parentNetServer;
		this.socket = socket;
		try {
			socketInputStream = socket.getInputStream();
			socketOutputStream = socket.getOutputStream();
		} catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
	
	//method
	@Override
	protected void run() {
		try {
			
			final var netEndPoint = createOptionalNetEndPoint();
			
			if (netEndPoint.isEmpty()) {
				closeSocket();
			} else {
				parentNetServer.takeEndPoint(netEndPoint.getRefElement());
			}
		} catch (final Exception exception) {
			
			closeSocket();
			
			throw new WrapperException(exception);
		}
	}
	
	//method
	private void closeSocket() {
		try {
			socket.close();
		} catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
	
	//method
	private SingleContainer<BaseNetEndPoint> createOptionalNetEndPoint() {
		
		final var firstReveivedLine = GlobalInputStreamHelper.readLineFrom(socketInputStream);
		
		switch (getNetEndPointCreationTypeFromFirstReceivedLine(firstReveivedLine)) {
			case REGULAR_SOCKET_WITH_DEFAULT_TARGET:
				return new SingleContainer<>(new NetEndPoint(socket, socketInputStream, socketOutputStream));
			case REGULAR_SOCKET_WITH_CUSTOM_TARGET:
				return
				new SingleContainer<>(
					new NetEndPoint(
						socket,
						socketInputStream,
						socketOutputStream,
						Node.fromString(firstReveivedLine.substring(1)).getHeader()
					)
				);
			case WEB_SOCKET_OR_HTTP:
				
				final var lines = LinkedList.withElements(firstReveivedLine);
				fillUpUntilEmptyLineFollows(lines, socketInputStream);
				
				if (WebSocketHandShakeRequest.canBe(lines)) {
					sendRawMessage(new WebSocketHandShakeRequest(lines).getWebSocketHandShakeResponse().toString());
					return new SingleContainer<>(new WebEndPoint(socket, socketInputStream, socketOutputStream));
				}
				
				if (HTTPRequest.canBe(lines)) {
					sendRawMessage(parentNetServer.getHTTPMessage());
					return new SingleContainer<>();
				}
				
				throw new InvalidArgumentException("first received line", firstReveivedLine, "is not valid");
			default:
				throw new InvalidArgumentException("first received line", firstReveivedLine, "is not valid");
		}
	}
	
	//method
	private void fillUpUntilEmptyLineFollows(final LinkedList<String> lines, final InputStream inputStream) {
		while (true) {
			
			final var line = GlobalInputStreamHelper.readLineFrom(inputStream);
			
			if (line == null) {
				throw new ArgumentIsNullException(LowerCaseCatalogue.LINE);
			}
			
			if (line.isEmpty()) {
				break;
			}
			
			lines.addAtEnd(line);
		}
	}
	
	//method
	private NetEndPointCreationType getNetEndPointCreationTypeFromFirstReceivedLine(final String firstReceivedLine) {
		
		if (firstReceivedLine.equals(String.valueOf(NetEndPointProtocol.MAIN_TARGET_PREFIX))) {
			return NetEndPointCreationType.REGULAR_SOCKET_WITH_DEFAULT_TARGET;
		}
		
		if (firstReceivedLine.startsWith(String.valueOf(NetEndPointProtocol.TARGET_PREFIX))) {
			return NetEndPointCreationType.REGULAR_SOCKET_WITH_CUSTOM_TARGET;
		}
		
		if (firstReceivedLine.startsWith("G")) {
			return NetEndPointCreationType.WEB_SOCKET_OR_HTTP;
		}
		
		throw new InvalidArgumentException("first received line", firstReceivedLine, "is not valid");
	}
	
	//method
	private void sendRawMessage(final String rawMessage) {
		try {
			socketOutputStream.write(rawMessage.getBytes(StandardCharsets.UTF_8));
			socketOutputStream.flush();
		} catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
}
