//package declaration
package ch.nolix.common.endpoint2;

//Java imports
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.common.commontypehelper.InputStreamHelper;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.exception.WrapperException;
import ch.nolix.common.http.HTTPRequest;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.websocket.WebSocketHandShakeRequest;
import ch.nolix.common.worker.Worker;

//class
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
			
			final var netEndPoint = createNetEndPointOrNull();
			
			if (netEndPoint == null) {
				closeSocket();
			} else {
				parentNetServer.takeEndPoint(netEndPoint);
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
	
	//TODO: Beautify this method.
	//method
	private BaseNetEndPoint createNetEndPointOrNull() {
		
		final var firstLine = InputStreamHelper.readLineFrom(socketInputStream);
		
		if (firstLine.equals(String.valueOf(NetEndPointProtocol.MAIN_TARGET_PREFIX))) {
			return new NetEndPoint(socket, socketInputStream, socketOutputStream);
		}
		
		if (firstLine.startsWith(String.valueOf(NetEndPointProtocol.TARGET_PREFIX))) {
			return
			new NetEndPoint(
				socket,
				socketInputStream,
				socketOutputStream,
				Node.fromString(firstLine).getOneAttributeHeader()
			);
		}
		
		if (firstLine.startsWith("G")) {
			
			final var lines = LinkedList.withElements(firstLine);
			fillUpUntilEmptyLineFollows(lines, socketInputStream);
			
			if (WebSocketHandShakeRequest.canBe(lines)) {
				sendRawMessage(new WebSocketHandShakeRequest(lines).getWebSocketHandShakeResponse().toString());
				return new WebEndPoint(socket, socketInputStream, socketOutputStream);
			}
			
			if (HTTPRequest.canBe(lines)) {
				sendRawMessage(parentNetServer.getHTTPMessage());
				return null;
			}
		}
		
		throw new InvalidArgumentException("first line", firstLine, "is not valid");
	}
	
	//method
	private void fillUpUntilEmptyLineFollows(final LinkedList<String> lines, final InputStream inputStream) {
		while (true) {
			
			final var line = InputStreamHelper.readLineFrom(inputStream);
			
			if (line == null) {
				throw new ArgumentIsNullException(VariableNameCatalogue.LINE);
			}
			
			if (line.isEmpty()) {
				break;
			}
			
			lines.addAtEnd(line);
		}
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
