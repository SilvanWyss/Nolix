//package declaration
package ch.nolix.common.net.endpoint;

//Java imports
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import ch.nolix.common.commontype.commontypehelper.InputStreamHelper;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.SingleContainer;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.exception.WrapperException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.net.http.HTTPRequest;
import ch.nolix.common.net.websocket.WebSocketHandShakeRequest;
import ch.nolix.common.programcontrol.worker.Worker;

//visibility-reduced class
final class NetServerSocketProcessor extends Worker {
	
	//attributes
	private final NetServer parentNetServer;
	private final Socket socket;
	private final InputStream socketInputStream;
	private final OutputStream socketOutputStream;
	
	//constructor
	public NetServerSocketProcessor(final NetServer parentNetServer, final Socket socket) {
		
		Validator.assertThat(parentNetServer).thatIsNamed("parent NetServer").isNotNull();
		Validator.assertThat(socket).thatIsNamed(Socket.class).isNotNull();
				
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
			
			final var netEndPoint = createNetEndPointOptionally();
			
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
	private SingleContainer<BaseNetEndPoint> createNetEndPointOptionally() {
		
		final var firstReveivedLine = InputStreamHelper.readLineFrom(socketInputStream);
		
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
						Node.fromString(firstReveivedLine).getOneAttributeHeader()
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
			
			final var line = InputStreamHelper.readLineFrom(inputStream);
			
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
