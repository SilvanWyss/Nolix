//package declaration
package ch.nolix.common.endpoint;

//own imports
import java.io.IOException;
import java.net.ServerSocket;

//own imports
import ch.nolix.common.constant.PortCatalogue;
import ch.nolix.common.exception.WrapperException;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.validator.Validator;

//class
/**
 * A net server is a server that listens to net end points on a specific port.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 90
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
	 * @throws ArgumentIsNullException if the given end point taker is null.
	 * @throws ArgumentIsOutOfRangeException if the given argument is not in [0, 65535].
	 */
	public NetServer(final int port, final IElementTaker<EndPoint> endPointTaker) {
		
		//Calls constructor of the base class.
		super(endPointTaker);
		
		//Asserts that the given port is in [0, 65535]. 
		Validator.assertThat(port).isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		//Sets the port of this net server.
		this.port = port;
		
		setPreCloseAction(this::runPreClose);
		
		try {
			
			//Creates the server socket of this net server sub listener.
			serverSocket = new ServerSocket(getPort());
			
			//This is important that the address can be reused
			//immediately after this net server sub listener is aborted.
			serverSocket.setReuseAddress(true);
		}
		catch (final IOException exception) {
			throw new WrapperException(exception);
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
	 * @return the server socket of this net server.
	 */
	ServerSocket getRefServerSocket() {
		return serverSocket;
	}
	
	//method
	/**
	 * Lets the current {@link NetServer} run a pre-close.
	 */
	private void runPreClose() {
		try {
			serverSocket.close();
		}
		catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
}
