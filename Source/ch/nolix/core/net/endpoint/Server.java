//package declaration
package ch.nolix.core.net.endpoint;

//Java imports
import java.io.IOException;
import java.net.ServerSocket;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.constant.PortCatalogue;

//class
/**
 * A {@link Server} is a {@link BaseServer} that listens to {@link NetEndPoint} on a specific port.
 * 
 * A {@link Server} supports the WebSocket protocol and can communicate with a WebSocket.
 * The WebSocket protocol is complicated. Because:
 * -A WebSocket requires a HTTP handshake.
 * -A WebSocket puts its messages in frames that need to be encoded awkwardly.
 * -A WebSocket sends messages, that belong together, in separate lines.
 * The WebSocket protocol was designed this way because of security reasons.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class Server extends BaseServer {
	
	//constant
	public static final int DEFAULT_PORT = PortCatalogue.HTTP;
	
	//constant
	private static final String DEFAULT_HTTP_MESSAGE =
	"""
	HTTP/1.1 200 OK
	Content-Type: text/html; charset=UTF-8
	
	<!DOCTYPE html>
	<html>
	<head>
	<title>Nolix</title>
	<style>*{font-family: Calibri;}</style>
	</head>
	<body>
	<h1>Nolix</h1>
	<p>The requested server does not support web clients.</p>
	</body>
	</html>
	""";
	
	//attributes
	private final int port;
	private final String httpMessage;
	private final ServerSocket serverSocket;
	
	//constructor
	/**
	 * Creates a new {@link Server} that will listen to {@link NetEndPoint}s on the default port.
	 */
	public Server() {
		
		//Calls other constructor.
		this(DEFAULT_PORT, DEFAULT_HTTP_MESSAGE);
	}
	
	//constructor
	/**
	 * Creates a new {@link Server} that will listen to {@link NetEndPoint}s on the given port.
	 * 
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public Server(final int port) {
		
		//Calls other constructor.
		this(port, DEFAULT_HTTP_MESSAGE);
	}
	
	//constructor
	/**
	 * Creates a new {@link Server} that will listen to {@link NetEndPoint}s on the given port.
	 * 
	 * When a web browser connects to the {@link Server},
	 * the {@link Server} will send the given httpMessage and close the connection.
	 * 
	 * @param port
	 * @param httpMessage
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given httpMessage is null.
	 * @throws EmptyArgumentException if the given httpMessage is blank.
	 */
	public Server(final int port, final String httpMessage) {
		
		//Asserts that the given port is in [0,65535]. 
		GlobalValidator.assertThat(port).isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		//Asserts that the given httpMessage is not null or blank.
		GlobalValidator.assertThat(httpMessage).thatIsNamed("HTTP message").isNotBlank();
		
		//Sets the port of the current Server.
		this.port = port;
		
		//Sets the httpMessage of the current Server.
		this.httpMessage = httpMessage;
		
		try {
			
			//Creates the serverSocket of the current Server.
			serverSocket = new ServerSocket(getPort());
			
			//This is important that the address of the current Server
			//can be reused immediately when the current NetSever is closed.
			serverSocket.setReuseAddress(true);
		} catch (final IOException pIOException) {
			throw WrapperException.forError(pIOException);
		}
		
		//Creates and starts a ServerListener for the current Server.
		new ServerListener(this);
	}
	
	//constructor
	/**
	 * Creates a new {@link Server} that will listen to {@link NetEndPoint}s on the default port.
	 * 
	 * When a web browser connects to the {@link Server},
	 * the {@link Server} will send the given httpMessage and close the connection.
	 * 
	 * @param httpMessage
	 * @throws ArgumentIsNullException if the given httpMessage is null.
	 * @throws EmptyArgumentException if the given httpMessage is blank.
	 */
	public Server(final String httpMessage) {
		
		//Calls other constructor.
		this(DEFAULT_PORT, httpMessage);
	}
	
	//method
	/**
	 * @return the port of the current {@link Server}.
	 */
	public int getPort() {
		return port;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteClose() {
		try {
			serverSocket.close();
		} catch (final IOException pIOException) {
			throw WrapperException.forError(pIOException);
		}
	}
	
	//method
	/**
	 * The HTTP message of a {@link Server} is the message a {@link Server} sends to web browsers.
	 * 
	 * @return the HTTP message of the current {@link Server}.
	 */
	String getHttpMessage() {
		return httpMessage;
	}
	
	//method
	/**
	 * @return the {@link ServerSocket} of the current {@link Server}.
	 */
	ServerSocket getOriServerSocket() {
		return serverSocket;
	}
}
