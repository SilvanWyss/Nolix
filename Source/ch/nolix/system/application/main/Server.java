//package declaration
package ch.nolix.system.application.main;

//own imports
import ch.nolix.core.environment.localcomputer.LocalComputer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.net.endpoint3.EndPoint;
import ch.nolix.core.net.target.ServerTarget;
import ch.nolix.coreapi.programcontrolapi.targetuniversalapi.IServerTarget;

//class
/**
 * A {@link Server} is a {@link BaseServer} that listens to net {@link Client}s on a specific port.
 * 
 * @author Silvan Wyss
 * @date 2017-09-10
 */
public final class Server extends BaseServer {
	
	//constant
	public static final int DEFAULT_PORT = ch.nolix.core.net.endpoint3.Server.DEFAULT_PORT;
	
	//static method
	/**
	 * @return a new {@link Server} that will listen to net {@link Client}s on the default port.
	 */
	public static Server forDefaultPort() {
		return forPort(DEFAULT_PORT);
	}
	
	//static method
	/**
	 * @param port
	 * @return a new {@link Server} that will listen to net {@link Client}s on the given port.
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public static Server forPort(final int port) {
		return new Server(port);
	}
	
	//attribute
	private ch.nolix.core.net.endpoint3.Server internalServer;
	
	//constructor
	/**
	 * Creates a new {@link Server} that will listen to net {@link Client}s on the given port.
	 * 
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	private Server(final int port) {
		
		//Creates the internalServer of the current Server.
		internalServer =
		new ch.nolix.core.net.endpoint3.Server(
			port,
			new ServerHTTPMessage(getIp(), port).toString()
		);
		
		//Creates a close dependency between the current Server and its internalServer.
		createCloseDependencyTo(internalServer);
	}
	
	//method
	/**
	 * @return the current {@link Server} as {@link IServerTarget}.
	 */
	public IServerTarget asTarget() {
		return ServerTarget.forIpOrAddressNameAndPort(getIp(), getPort());
	}
	
	//method
	/**
	 * @return the Ip of the current {@link Server}.
	 */
	public String getIp() {
		return LocalComputer.getLANIP();
	}
	
	//method
	/**
	 * @return the port of the current {@link Server}.
	 */
	public int getPort() {
		return internalServer.getPort();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteAddedApplication(final Application<?, ?> application) {
		internalServer.addEndPointTaker(new ServerEndPointTaker(application.getInstanceName(), this));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteAddedDefaultApplication(final Application<?, ?> defaultApplication) {
		internalServer.addDefaultEndPointTaker(new ServerEndPointTaker(defaultApplication.getInstanceName(), this));
	}
	
	//method
	/**
	 * Lets the current {@link Server} take the given endPoint.
	 * 
	 * @param endPoint
	 */
	void internalTakeEndPoint(final EndPoint endPoint) {
		
		//Handles the case that the given endPoint does not have a target.
		if (!endPoint.hasTarget()) {
			getRefDefaultApplication().takeEndPoint(endPoint);
			
		//Handles the case that the given endPoint has a target.
		} else {
			getRefApplicationByName(endPoint.getTarget()).takeEndPoint(endPoint);
		}
	}
}
