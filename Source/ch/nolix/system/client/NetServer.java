//package declaration
package ch.nolix.system.client;

//own imports
import ch.nolix.core.constants.PortCatalogue;
import ch.nolix.core.endPoint5.EndPoint;

//class
/**
 * A {@link NetServer} is a {@link Server} that listens to net {@link Client}s on a specific port.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 130
 */
public final class NetServer extends Server {
	
	//constant
	public static final int DEFAULT_PORT = PortCatalogue.DE_FACTO_HTTP_PORT;
	
	//constant
	private static final String HTTP_MESSAGE =
	"HTTP/1.1 200 OK\r\n"
	+ "Content-Type: text/html; charset=UTF-8\r\n"
	+ "\r\n"
	+ "<!DOCTYPE html>"
	+ "<html>"
	+ "<head>"
	+ "<script src=\"http://www.nolix.ch/Launcher/require.js\"></script>"
	+ "<script src=\"http://www.nolix.ch/Launcher/nolix.js\"></script>"
	+ "<script src=\"http://www.nolix.ch/Launcher/main.js\"></script>"
	+ "<title>Nolix</title>"
	+ "<style>*{font-family: Calibri;}</style>"
	+ "</head>"
	+ "<h1>Nolix</h1>"
	+ "</body>"
	+ "</html>\r\n";
	
	//attribute
	private ch.nolix.core.endPoint5.NetServer internalNetServer;
	
	//constructor
	/**
	 * Creates a new {@link NetServer} that will listen to net {@link Client}s on the default port.
	 * 
	 * @param port
	 * @throws OutOfRangeArgumentException if the given port is not in [0,65535].
	 */
	public NetServer() {
		
		//Calls other constructor.
		this(DEFAULT_PORT);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetServer} that will listen to net {@link Client}s on the default port.
	 * The {@link NetServer} will have the given mainApplication.
	 * 
	 * @param mainApplication.
	 * @throws NullArgumentException if the given mainApplication is null.
	 */
	public NetServer(final Application<?> mainApplication) {
		
		//Calls other constructor.
		this();
		
		addDefaultApplication(mainApplication);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetServer} that will listen to net {@link Client}s on the given port.
	 * 
	 * @param port
	 * @throws OutOfRangeArgumentException if the given port is not in [0,65535].
	 */
	public NetServer(final int port) {
		
		//Creates the internalNetServer of the current NetServer.
		internalNetServer = new ch.nolix.core.endPoint5.NetServer(port, HTTP_MESSAGE);
				
		internalNetServer.addMainEndPointTaker(new NetServerSubDuplexControllerTaker(this));
		
		//Creates a close dependency between the current NetServer and its internalNetServer.
		createCloseDependency(internalNetServer);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetServer} that will listen to net {@link Client}s on the given port.
	 * The {@link NetServer} will have the given mainApplication.
	 * 
	 * @param port
	 * @param mainApplication.
	 * @throws OutOfRangeArgumentException if the given port is not in [0,65535].
	 * @throws NullArgumentException if the given mainApplication is null.
	 */
	public NetServer(final int port, final Application<?> mainApplication) {
		
		//Calls other constructor.
		this(port);
		
		addDefaultApplication(mainApplication);
	}
	
	//method
	/**
	 * @return the port of the current {@link NetServer}.
	 */
	public int getPort() {
		return internalNetServer.getPort();
	}
	
	//method
	/**
	 * Lets the current {@link NetServer} take the given endPoint.
	 * 
	 * @param endPoint
	 */
	void takeDuplexController(final EndPoint endPoint) {
		
		if (!containsDefaultApplication()) {
			getRefApplicationByName(endPoint.getTarget())
			.takeDuplexController(endPoint);
		}
		
		else {
			getRefDefaultApplication().takeDuplexController(endPoint);
		}
	}
}
