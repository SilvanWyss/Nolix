//package-declaration
package ch.nolix.common.endPoint2;

//Java imports
import java.io.IOException;
import java.net.ServerSocket;

import ch.nolix.common.constants.PortCatalogue;
import ch.nolix.common.validator.Validator;

//class
/**
* A {@link NetServer} is a {@link Server} that listens to {@link NetEndPoint} on a specific port.
* 
* @author Silvan Wyss
* @month 2015-12
* @lines 160
*/
public final class NetServer extends Server {
	
	//default value
	public static final int DEFAULT_PORT = PortCatalogue.DE_FACTO_HTTP_PORT;
	
	//default value
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
	private final String HTTPMessage;
	private final ServerSocket serverSocket;
	
	//constructor
	/**
	 * Creates a new {@link NetServer} that will listen to {@link NetEndPoint}s on the default port.
	 */
	public NetServer() {
		
		//Calls other constructor.
		this(DEFAULT_PORT, DEFAULT_HTTP_MESSAGE);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetServer} that will listen to {@link NetEndPoint}s on the given port.
	 * 
	 * @param port
	 * @throws OutOfRangeArgumentException if the given port is not in [0, 65535].
	 */
	public NetServer(final int port) {
		
		//Calls other constructor.
		this(port, DEFAULT_HTTP_MESSAGE);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetServer} that will listen to {@link NetEndPoint}s on the given port.
	 * 
	 * When a web browser connects to the {@link NetServer},
	 * the {@link NetServer} will send the given HTTP message and close the connection.
	 * 
	 * @param port
	 * @param HTTPMessage
	 * @throws OutOfRangeArgumentException if the given port is not in [0, 65535].
	 * @throws NullArgumentException if the given HTTP message is null.
	 * @throws EmptyArgumentException if the given HTTP message is blank.
	 */
	public NetServer(final int port, final String HTTPMessage) {
			
		//Checks if the given port is in [0,65535]. 
		Validator.suppose(port).isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		//Checks if the given HTTP message is not null or blank.
		Validator.suppose(HTTPMessage).thatIsNamed("HTTP message").isNotBlank();
		
		//Sets the port of the current NetServer.
		this.port = port;
		
		//Sets the HTTP message of the current NetServer.
		this.HTTPMessage = HTTPMessage;
		
		try {
			
			//Creates the serverSocket of the current NetServer.
			serverSocket = new ServerSocket(getPort());
			
			//This is important that the address of the current NetServer
			//can be reused immediately when the current NetSever is closed.
			serverSocket.setReuseAddress(true);
		}
		catch (final IOException IOException) {
			throw new RuntimeException(IOException);
		}
		
		//Creates a NetServerSubListenerfor the current NetServer.
		new NetServerSubListener(this);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetServer} that will listen to {@link NetEndPoint}s on the default port.
	 * 
	 * When a web browser connects to the {@link NetServer},
	 * the {@link NetServer} will send the given HTTP message and close the connection.
	 * 
	 * @param HTTPMessage
	 * @throws NullArgumentException if the given HTTP message is null.
	 * @throws EmptyArgumentException if the given HTTP message is blank.
	 */
	public NetServer(final String HTTPMessage) {
		
		//Calls other constructor.
		this(DEFAULT_PORT, HTTPMessage);
	}
	
	//method
	/**
	 * @return the port of the current {@link NetServer}.
	 */
	public int getPort() {
		return port;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteClose() {
		try {
			serverSocket.close();
		}
		catch (final IOException IOexception) {
			throw new RuntimeException(IOexception);
		}
	}
	
	//package-visible method
	/**
	 * The HTTP message of a {@link NetServer} is the message a {@link NetServer} sends to web browsers.
	 * 
	 * @return the HTTP message of the current {@link NetServer}.
	 */
	String getHTTPMessage() {
		return HTTPMessage;
	}
	
	//package-visible method
	/**
	 * @return the {@link ServerSocket} of the current {@link NetServer}.
	 */
	ServerSocket getRefServerSocket() {
		return serverSocket;
	}
}
