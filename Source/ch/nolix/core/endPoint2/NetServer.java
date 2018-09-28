//package-declaration
package ch.nolix.core.endPoint2;

//Java imports
import java.io.IOException;
import java.net.ServerSocket;

//own imports
import ch.nolix.core.constants.PortCatalogue;
import ch.nolix.primitive.validator2.Validator;

//class
/**
* A {@link NetServer} is a {@link Server}
* that listens to {@link NetEndPoint} on a specific port.
* 
* @author Silvan Wyss
* @month 2015-12
* @lines 130
*/
public final class NetServer extends Server {
	
	//attributes
	private final int port;
	private final String HTTPMessage;
	private final ServerSocket serverSocket;
	
	//default value
	private static final String DEFAULT_HTTP_MESSAGE =
	"HTTP/1.1 200 OK\r\nContent-Type: text/html; charset=UTF-8\r\n\r\n"
	+ "<html>"
	+ "<head><title>Nolix</title><style>h1{font-family: Calibry;}</style></head>"
	+ "<body><h1>The requested server does not support web browsers.<h1></body>"
	+ "</html>\r\n";
	
	//constructor
	/**
	 * Creates a new {@link NetServer}
	 * that will listen to {@link NetEndPoint} on the given port.
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
	 * Creates a new {@link NetServer}
	 * that will listen to {@link NetEndPoint} on the given port.
	 * 
	 * When a web browser connects to the {@link NetServer},
	 * the {@link NetServer} will send the given HTTP message and close the connection.
	 * 
	 * @param port
	 * @param HTTPMessage
	 * @throws OutOfRangeArgumentException if the given port is not in [0, 65535].
	 * @throws NullArgumentException if the given HTTP message is not an instance.
	 * @throws EmptyArgumentException if the given HTTP message is empty.
	 */
	public NetServer(final int port, final String HTTPMessage) {
			
		//Checks if the given port is in [0,65535]. 
		Validator.suppose(port).isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		//Checks if the given HTTP message is an instance and not empty.
		Validator.suppose(HTTPMessage).thatIsNamed("HTTP message").isNotEmpty();
		
		//Sets the port of the current net server.
		this.port = port;
		
		//Sets the HTTP message of tte current net server.
		this.HTTPMessage = HTTPMessage;
		
		try {
			
			//Creates the server socket of the current net server.
			serverSocket = new ServerSocket(getPort());
			
			//This is important that the address of the current net server
			//can be reused immediately when the current net server is closed.
			serverSocket.setReuseAddress(true);
		}
		catch (final IOException IOException) {
			throw new RuntimeException(IOException);
		}
		
		//Creates a net server sub listener for the current net server.
		new NetServerSubListener(this);
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
	 * Let the current {@link NetServer} note a closing.
	 */
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
	 * The HTTP message of a {@link NetServer} is the message
	 * the {@link NetServer} sends to web browsers.
	 * 
	 * @return the HTTP message of the current {@link NetServer}.
	 */
	String getHTTPMessage() {
		return HTTPMessage;
	}
	
	//package-visible method
	/**
	 * @return the server socket of the current {@link NetServer}.
	 */
	ServerSocket getRefServerSocket() {
		return serverSocket;
	}
}
