//package declaration
package ch.nolix.common.net.endpoint;

//Java imports
import java.io.IOException;
import java.net.ServerSocket;

//own imports
import ch.nolix.common.constant.PortCatalogue;
import ch.nolix.common.errorcontrol.exception.WrapperException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
/**
* A {@link Server} is a {@link BaseServer} that listens to {@link BaseNetEndPoint} on a specific port.
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
* @lines 170
*/
public final class Server extends BaseServer {
	
	//constant
	public static final int DEFAULT_PORT = PortCatalogue.HTTP_PORT;
	
	//constant
	private static final String DEFAULT_HTTP_MESSAGE =
	"HTTP/1.1 200 OK\r\n"
	+ "Content-Type: text/html; charset=UTF-8\r\n"
	+ "\r\n"
	+ "<!DOCTYPE html>"
	+ "<html>"
	+ "<head><title>Nolix</title><style>*{font-family: Calibri;}</style></head>"
	+ "<body><h1>Nolix</h1><h2>The requested server does not support web browsers.</h2></body>"
	+ "</html>\r\n";
	
	//attributes
	private final int port;
	private final String mHTTPMessage;
	private final ServerSocket serverSocket;
	
	//constructor
	/**
	 * Creates a new {@link Server} that will listen to {@link BaseNetEndPoint}s on the default port.
	 */
	public Server() {
		
		//Calls other constructor.
		this(DEFAULT_PORT, DEFAULT_HTTP_MESSAGE);
	}
	
	//constructor
	/**
	 * Creates a new {@link Server} that will listen to {@link BaseNetEndPoint}s on the given port.
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
	 * Creates a new {@link Server} that will listen to {@link BaseNetEndPoint}s on the given port.
	 * 
	 * When a web browser connects to the {@link Server},
	 * the {@link Server} will send the given HTTP message and close the connection.
	 * 
	 * @param port
	 * @param HTTPMessage
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given HTTP message is null.
	 * @throws EmptyArgumentException if the given HTTP message is blank.
	 */
	public Server(final int port, final String HTTPMessage) {
			
		//Asserts that the given port is in [0,65535]. 
		Validator.assertThat(port).isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		//Asserts that the given HTTP message is not null or blank.
		Validator.assertThat(HTTPMessage).thatIsNamed("HTTP message").isNotBlank();
		
		//Sets the port of the current NetServer.
		this.port = port;
		
		//Sets the HTTP message of the current NetServer.
		this.mHTTPMessage = HTTPMessage;
		
		try {
			
			//Creates the serverSocket of the current NetServer.
			serverSocket = new ServerSocket(getPort());
			
			//This is important that the address of the current NetServer
			//can be reused immediately when the current NetSever is closed.
			serverSocket.setReuseAddress(true);
		} catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
		
		//Creates and starts a NetServerListener for the current NetServer.
		new NetServerListener(this).start();
	}
	
	//constructor
	/**
	 * Creates a new {@link Server} that will listen to {@link BaseNetEndPoint}s on the default port.
	 * 
	 * When a web browser connects to the {@link Server},
	 * the {@link Server} will send the given HTTP message and close the connection.
	 * 
	 * @param HTTPMessage
	 * @throws ArgumentIsNullException if the given HTTP message is null.
	 * @throws EmptyArgumentException if the given HTTP message is blank.
	 */
	public Server(final String HTTPMessage) {
		
		//Calls other constructor.
		this(DEFAULT_PORT, HTTPMessage);
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
			throw new WrapperException(pIOException);
		}
	}
	
	//method
	/**
	 * The HTTP message of a {@link Server} is the message a {@link Server} sends to web browsers.
	 * 
	 * @return the HTTP message of the current {@link Server}.
	 */
	String getHTTPMessage() {
		return mHTTPMessage;
	}
	
	//method
	/**
	 * @return the {@link ServerSocket} of the current {@link Server}.
	 */
	ServerSocket getRefServerSocket() {
		return serverSocket;
	}
}
