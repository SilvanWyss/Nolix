//package declaration
package ch.nolix.system.client.base;

//own imports
import ch.nolix.common.constant.PortCatalogue;
import ch.nolix.common.environment.localcomputer.LocalComputer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.common.net.endpoint3.EndPoint;

//class
/**
 * A {@link NetServer} is a {@link Server} that listens to net {@link Client}s on a specific port.
 * 
 * @author Silvan Wyss
 * @date 2017-09-10
 * @lines 100
 */
public final class NetServer extends Server {
	
	//constant
	public static final int DEFAULT_PORT = PortCatalogue.HTTP_PORT;
	
	//attribute
	private ch.nolix.common.net.endpoint3.NetServer internalNetServer;
	
	//constructor
	/**
	 * Creates a new {@link NetServer} that will listen to net {@link Client}s on the default port.
	 * 
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public NetServer() {
		
		//Calls other constructor.
		this(DEFAULT_PORT);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetServer} that will listen to net {@link Client}s on the given port.
	 * 
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public NetServer(final int port) {
		
		//Creates the internalNetServer of the current NetServer.
		internalNetServer =
		new ch.nolix.common.net.endpoint3.NetServer(
			port, 
			new NetServerHTTPMessage(LocalComputer.getLANIP(), port).toString()
		);
		
		internalNetServer.addMainEndPointTaker(new NetServerSubDuplexControllerTaker(this));
		
		//Creates a close dependency between the current NetServer and its internalNetServer.
		createCloseDependencyTo(internalNetServer);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetServer} that will listen to net {@link Client}s on the given port.
	 * The {@link NetServer} will have the given defaultApplication.
	 * 
	 * @param port
	 * @param defaultApplication
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given defaultApplication is null.
	 */
	public NetServer(final int port, final Application<?> defaultApplication) {
		
		//Calls other constructor.
		this(port);
		
		addDefaultApplication(defaultApplication);
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
	void takeEndPoint(final EndPoint endPoint) {
		
		//Handles the case that the given endPoint does not have a target.
		if (!endPoint.hasTarget()) {
			getRefDefaultApplication().takeEndPoint(endPoint);
			
		//Handles the case that the given endPoint has a target.
		} else {
			getRefApplication(endPoint.getTarget()).takeEndPoint(endPoint);
		}
	}
}
