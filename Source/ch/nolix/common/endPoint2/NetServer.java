//package declaration
package ch.nolix.common.endPoint2;

//Java imports
import java.io.IOException;
import java.net.ServerSocket;

//own imports
import ch.nolix.common.constant.PortCatalogue;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.wrapperException.WrapperException;

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
	private final String mHTTPMessage;
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
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
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
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given HTTP message is null.
	 * @throws EmptyArgumentException if the given HTTP message is blank.
	 */
	public NetServer(final int port, final String HTTPMessage) {
			
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
		}
		catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
		
		//Creates a NetServerSubListenerfor the current NetServer.
		new NetServerListener(this);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetServer} that will listen to {@link NetEndPoint}s on the default port.
	 * 
	 * When a web browser connects to the {@link NetServer},
	 * the {@link NetServer} will send the given HTTP message and close the connection.
	 * 
	 * @param HTTPMessage
	 * @throws ArgumentIsNullException if the given HTTP message is null.
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
		catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
	
	//method
	/**
	 * The HTTP message of a {@link NetServer} is the message a {@link NetServer} sends to web browsers.
	 * 
	 * @return the HTTP message of the current {@link NetServer}.
	 */
	String getHTTPMessage() {
		return mHTTPMessage;
	}
	
	//method
	/**
	 * @return the {@link ServerSocket} of the current {@link NetServer}.
	 */
	ServerSocket getRefServerSocket() {
		return serverSocket;
	}
}
