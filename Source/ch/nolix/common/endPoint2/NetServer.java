//package-declaration
package ch.nolix.common.endPoint2;

//Java import
import java.net.Socket;

//own imports
import ch.nolix.common.constants.PortManager;
import ch.nolix.common.zetaValidator.ZetaValidator;

//class
/**
* A net server is a server that listens to net end points on a certain port.
* 
* @author Silvan Wyss
* @month 2015-12
* @lines 50
*/
public final class NetServer extends Server {
	
	//attribute
	private final int port;

	//constructor
	/**
	 * Creates new net server that will listen to net end points on the given port.
	 * 
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 */
	public NetServer(final int port) {
		
		//Checks if the given port is in [0, 65535]. 
		ZetaValidator.supposeThat(port).isBetween(PortManager.MIN_PORT, PortManager.MAX_PORT);
		
		//Sets the port of this server.
		this.port = port;
		
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
	 * Lets this net server take the given incoming socket.
	 * 
	 * @param incomingSocket
	 */
	protected void takeIncomingSocket(final Socket incomingSocket) {
		takeIncomingEndPoint(new NetEndPoint(incomingSocket));
	}
}
