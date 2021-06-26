//package declaration
package ch.nolix.common.net.endpoint2;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;

//class
/**
 * A {@link Server} is a {@link BaseServer} that listens to {@link NetEndPoint} on a specific port.
 * 
 * @author Silvan Wyss
 * @date 2016-06-01
 * @lines 70
 */
public final class Server extends BaseServer {
	
	//attribute
	private final ch.nolix.common.net.endpoint.Server internalNetServer;
	
	//constructor
	/**
	 * Creates a new {@link Server} that will listen to {@link NetEndPoint}s on the given port.
	 * 
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public Server(final int port) {
		
		//Creates the internal net server of the current net server.
		internalNetServer =	new ch.nolix.common.net.endpoint.Server(port);
		
		//Creates a close dependency to the internal net server of the current net server.
		createCloseDependencyTo(internalNetServer);
		
		internalNetServer.addMainEndPointTaker(new NetServerEndPointTaker(this));
	}
	
	//constructor
	/**
	 * Creates a new {@link Server} that will listen to {@link NetEndPoint}s on the given port.
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
		
		//Creates the internal net server of the current net server.
		internalNetServer =	new ch.nolix.common.net.endpoint.Server(port, HTTPMessage);
		
		//Creates a close dependency to the internal net server of the current net server.
		createCloseDependencyTo(internalNetServer);
		
		internalNetServer.addMainEndPointTaker(new NetServerEndPointTaker(this));
	}
	
	//method
	/**
	 * @return the port of the current {@link Server}.
	 */
	public int getPort() {
		return internalNetServer.getPort();
	}
}
