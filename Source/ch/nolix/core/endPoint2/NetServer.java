//package-declaration
package ch.nolix.core.endPoint2;

//Java imports
import java.io.IOException;
import java.net.ServerSocket;

//own imports
import ch.nolix.core.constants.PortManager;
import ch.nolix.core.validator2.Validator;

//class
/**
* A net server is a server that listens to net end points on a specific port.
* 
* @author Silvan Wyss
* @month 2015-12
* @lines 80
*/
public class NetServer extends Server {
	
	//attributes
	private final int port;
	private final ServerSocket serverSocket;

	//constructor
	/**
	 * Creates new net server that will listen to net end points on the given port.
	 * 
	 * @param port
	 * @throws OutOfRangeArgumentException if the given port is not in [0,65535].
	 */
	public NetServer(final int port) {
			
		//Checks if the given port is in [0,65535]. 
		Validator.supposeThat(port).isBetween(PortManager.MIN_PORT, PortManager.MAX_PORT);
		
		//Sets the port of this net server.
		this.port = port;
		
		try {
			
			//Creates the server socket of this net server.
			serverSocket = new ServerSocket(getPort());
			
			//This is important that the address can be reused immediately
			//when this net server is closed.
			serverSocket.setReuseAddress(true);
		}
		catch (final IOException exception) {
			throw new RuntimeException(exception);
		}
		
		//Creates new net server sub listener for this net server.
		new NetServerSubListener(this);
	}

	//method
	/**
	 * @return the port of this net server.
	 */
	public int getPort() {
		return port;
	}
	
	//method
	/**
	 * Lets this net server note a closing.
	 */
	protected void noteClosing() {
		try {
			serverSocket.close();
		}
		catch (final IOException exception) {
			throw new RuntimeException(exception);
		}
	}
	
	//package-visible method
	/**
	 * @return the server socket of this net server.
	 */
	ServerSocket getRefServerSocket() {
		return serverSocket;
	}
}
