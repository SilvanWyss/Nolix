//package declaration
package ch.nolix.core.endPoint;

import ch.nolix.core.constants.PortManager;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A net server is a server that listens to net end points on a specific port.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 10
 */
public final class NetServer extends Server {
	
	//attribute
	final int port;

	//constructor
	/**
	 * Creates new net server with the given port and end point taker.
	 * 
	 * @param port
	 * @param endPointTaker
	 * @throws NullArgumentException if the given end point taker is null.
	 * @throws OutOfRangeArgumentException if the given argument is not in [0, 65535].
	 */
	public NetServer(final int port, final IEndPointTaker endPointTaker) {
		
		//Calls constructor of the base class.
		super(endPointTaker);
		
		//Checks if the given port is in [0, 65535]. 
		Validator.supposeThat(port).isBetween(PortManager.MIN_PORT, PortManager.MAX_PORT);
		
		this.port = port;
	}

	//method
	/**
	 * @return the port of this net server.
	 */
	public int getPort() {
		return port;
	}
}
