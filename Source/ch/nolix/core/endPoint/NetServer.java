//package declaration
package ch.nolix.core.endPoint;

//own imports
import java.io.IOException;
import java.net.ServerSocket;


//own imports
import ch.nolix.core.constants.PortCatalogue;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A net server is a server that listens to net end points on a specific port.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 80
 */
public final class NetServer extends Server {
	
	//attributes
	private final int port;
	private final ServerSocket serverSocket;
	
	//constructor
	/**
	 * Creates a new net server with the given port and end point taker.
	 * 
	 * @param port
	 * @param endPointTaker
	 * @throws NullArgumentException if the given end point taker is not an instance.
	 * @throws OutOfRangeArgumentException if the given argument is not in [0, 65535].
	 */
	public NetServer(final int port, final IEndPointTaker endPointTaker) {
		
		//Calls constructor of the base class.
		super(endPointTaker);
		
		//Checks if the given port is in [0, 65535]. 
		Validator.suppose(port).isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		//Sets the port of this net server.
		this.port = port;
		
		try {
			
			//Creates the server socket of this net server sub listener.
			serverSocket = new ServerSocket(getPort());
			
			//This is important that the address can be reused
			//immediately after this net server sub listener is aborted.
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
	
	//package-visible method
	/**
	 * @return the server socket of this net server.
	 */
	ServerSocket getRefServerSocket() {
		return serverSocket;
	}
	
	//method
	/**
	 * Lets this net server note an abort.
	 */
	protected void noteClose() {
		try {
			serverSocket.close();
		}
		catch (IOException exception) {}
	}
}
