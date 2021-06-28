//package declaration
package ch.nolix.system.client.base;

//own imports
import ch.nolix.common.environment.localcomputer.LocalComputer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.common.net.endpoint3.EndPoint;

//class
/**
 * A {@link Server} is a {@link BaseServer} that listens to net {@link Client}s on a specific port.
 * 
 * @author Silvan Wyss
 * @date 2017-09-10
 * @lines 90
 */
public final class Server extends BaseServer {
	
	//constant
	public static final int DEFAULT_PORT = ch.nolix.common.net.endpoint3.Server.DEFAULT_PORT;
	
	//attribute
	private ch.nolix.common.net.endpoint3.Server internalNetServer;
	
	//constructor
	/**
	 * Creates a new {@link Server} that will listen to net {@link Client}s on the default port.
	 */
	public Server() {
		
		//Calls other constructor.
		this(DEFAULT_PORT);
	}
	
	//constructor
	/**
	 * Creates a new {@link Server} that will listen to net {@link Client}s on the given port.
	 * 
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public Server(final int port) {
		
		//Creates the internalNetServer of the current NetServer.
		internalNetServer =
		new ch.nolix.common.net.endpoint3.Server(
			port,
			new ServerHTTPMessage(LocalComputer.getLANIP(), port).toString()
		);
		
		//Creates a close dependency between the current NetServer and its internalNetServer.
		createCloseDependencyTo(internalNetServer);
	}
	
	//method
	/**
	 * @return the port of the current {@link Server}.
	 */
	public int getPort() {
		return internalNetServer.getPort();
	}
	
	//method
	/**
	 * Lets the current {@link Server} take the given endPoint.
	 * 
	 * @param endPoint
	 */
	void takeEndPoint(final EndPoint endPoint) {
		
		//Handles the case that the given endPoint does not have a target.
		if (!endPoint.hasTarget()) {
			getRefDefaultApplication().takeEndPoint(endPoint);
			
		//Handles the case that the given endPoint has a target.
		} else {
			getRefApplicationByName(endPoint.getTarget()).takeEndPoint(endPoint);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteAddedApplication(final Application<?> application) {
		internalNetServer.addEndPointTaker(new ServerClientTaker(application.getName(), this));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteAddedDefaultApplication(Application<?> defaultApplication) {
		internalNetServer.addDefaultEndPointTaker(new ServerClientTaker(defaultApplication.getName(), this));
	}
}
